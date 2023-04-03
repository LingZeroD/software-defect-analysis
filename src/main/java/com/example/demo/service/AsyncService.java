package com.example.demo.service;

import cn.hutool.json.JSONObject;
import com.example.demo.algorithm.auxiliary.Classifier;
import com.example.demo.algorithm.auxiliary.DataSet;
import com.example.demo.algorithm.auxiliary.Evaluation;
import com.example.demo.algorithm.predict.PredictDataSet;
import com.example.demo.algorithm.tool.SaveAndLoad;
import com.example.demo.controller.form.PredictWithinParams;
import com.example.demo.controller.form.TrainModelParams;
import com.example.demo.entity.Model;
import com.example.demo.entity.ModelTrain;
import com.example.demo.entity.Predict;
import com.example.demo.entity.PredictWithin;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.mapper.ModelTrainMapper;
import com.example.demo.mapper.PredictMapper;
import com.example.demo.mapper.PredictWithinMapper;
import com.example.demo.utils.ArgsUtils;
import com.example.demo.websocket.MyPredictWebSocketHandler;
import com.example.demo.websocket.MyTrainWebSocketHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Component
public class AsyncService {


    @Resource
    private ModelMapper modelMapper;


    @Async
    public void tarin(ModelMapper modelMapper, Model model, String data, int type, int param, int id) {
        String algorithm = (type == 1) ? "RandomForest" : "AdaBoost";
        System.out.println(algorithm);
        int classifier = param;
        DataSet dataset = new DataSet(data);
        Evaluation eva = new Evaluation(modelMapper, dataset, algorithm, classifier, "model" + model.getId() + ".dat");
        eva.calculateMetrics(model);
    }

    @Async
    public void trainDPModel(ModelTrainMapper modelTrainMapper, ModelTrain modelTrain, TrainModelParams params) {
        String pythonScriptPath = "E:\\file\\code\\bishe\\DeepLineDP\\script\\train_model.py"; // Python脚本的路径
        String workDirPath = "E:\\file\\code\\bishe\\DeepLineDP\\script"; // 工作目录的路径
        String[] pythonArgs = new ArgsUtils<TrainModelParams>().convertToPythonArgs(params);
        try {
            // 创建ProcessBuilder对象
            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);
            pb.redirectErrorStream(true);
            // 设置工作目录，如果需要的话
            pb.directory(new File(workDirPath));
            pb.command().addAll(Arrays.asList(pythonArgs));

            // 启动进程
            Process process = pb.start();

            // 获取进程输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            // 读取进程输出
            String line = "";
            String result = "";
            int flag = 0;
            while ((line = reader.readLine()) != null) {
                MyTrainWebSocketHandler.sendMessage(modelTrain.getUsername(), line);
                if (line.contains("Word2Vec")) {
                    flag = 1;
                }
                if (flag == 1) {
                    result += line + "\n";
                }
                System.out.println(line);
            }
            reader.close();
            process.waitFor();
            modelTrain.setResult(getTrainResultJson(result));
            modelTrain.setResultPath(getCsvFilePath(result));
            modelTrain.setState(1);
            modelTrain.setFinishTime(new Date());
            modelTrainMapper.updateById(modelTrain);

        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void predictModelWithin(PredictWithinMapper predictWithinMapper, PredictWithin predictWithin, String username, PredictWithinParams predictWithinParams) {
        String pythonScriptPath = "E:\\file\\code\\bishe\\DeepLineDP\\script\\generate_prediction.py"; // Python脚本的路径
        String workDirPath = "E:\\file\\code\\bishe\\DeepLineDP\\script"; // 工作目录的路径
//      String[] pythonArgs = {
//                "-dataset", "activemq",
//                "-embed_dim", "50",
//                "-word_gru_hidden_dim", "64",
//                "-sent_gru_hidden_dim", "64",
//                "-word_gru_num_layers", "1",
//                "-sent_gru_num_layers", "1",
//                "-exp_name", "",
//                "-target_epochs", "180",
//                "-dropout", "0.2"
//        }; // Python脚本的参数
        String[] pythonArgs = new ArgsUtils<PredictWithinParams>().convertToPythonArgs(predictWithinParams);
        System.out.println(Arrays.toString(pythonArgs));
        try {
            // 创建ProcessBuilder对象
            ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);
            pb.redirectErrorStream(true);
            // 设置工作目录，如果需要的话
            pb.directory(new File(workDirPath));
            pb.command().addAll(Arrays.asList(pythonArgs));

            // 启动进程
            Process process = pb.start();

            // 获取进程输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));

            // 读取进程输出
            String line = "";
            String result = "";
            int flag = 0;
            while ((line = reader.readLine()) != null) {
               MyTrainWebSocketHandler.sendMessage(username, line);
                if (line.contains("Word2Vec")) {
                    flag = 1;
                }
                if (flag == 1) {
                    result += line + "\n";
                }
            }
            reader.close();

            process.waitFor();

            predictWithin.setResult(result);
            predictWithin.setResultPath(getWithinResultJsonPath(result));
            predictWithin.setState(1);
            Date time = new Date();
            predictWithin.setFinishTime(time);
            predictWithinMapper.updateById(predictWithin);



        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void predict(PredictMapper predictMapper, Predict predict, String path, int model) {

        String prediction = null;
        String res = "/download/result/predict_result" + predict.getId() + ".csv";
        File result = new File(System.getProperty("user.dir") + res);

        //加载模型
        SaveAndLoad s = new SaveAndLoad();
        Classifier c = (Classifier) s.readObjectFromFile("model" + model + ".dat");
        //读取预测数据
        PredictDataSet set = new PredictDataSet(path);
        double[][] features = set.getFeatures();
        //预测
        for (int i = 0; i < features.length; i++) {
            System.out.println(c.predict(features[i]));
            prediction = (c.predict(features[i]) == 1.0) ? "buggy" : "clean";
            try {
                FileWriter out2 = new FileWriter(result, true);
                out2.append(prediction + "\r");
                out2.close();
                predict.setResult(res);
                predict.setState(1);
                Date time = new Date();
                predict.setFinish(time);
                predictMapper.updateById(predict);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

    }
    public String getWithinResultJsonPath(String result){
        Pattern pattern = Pattern.compile("predict-within-result:(.*?):(.*?)\n");
        Matcher matcher = pattern.matcher(result);
        List<JSONObject> predictions = new ArrayList<>();
        while (matcher.find()) {
            JSONObject jsonObject = new JSONObject();
            System.out.println(matcher.group(1));
            jsonObject.append("release", matcher.group(1));
            jsonObject.append("path", matcher.group(2));
            predictions.add(jsonObject);
        }
        return predictions.toString();
    }
    public String getTrainResultJson(String result){

        String csvFilePath = getCsvFilePath(result);
        // 创建CsvMapper对象
        CsvMapper csvMapper = new CsvMapper();

        // 定义CSV文件格式
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("epoch")
                .addColumn("train_loss")
                .addColumn("valid_loss")
                .build()
                .withHeader();

        // 读取CSV文件，返回List
        List<Object> list = null;
        try {
            list = csvMapper.readerFor(Object.class)
                    .with(csvSchema)
                    .readValues(new File(csvFilePath))
                    .readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 创建ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将List转换为JSON格式字符串
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 输出JSON格式字符串
        System.out.println(jsonString);
        return jsonString;
    }
    public String getCsvFilePath(String result) {
        System.out.println(result);
        Pattern pattern = Pattern.compile("train-result:(.*\\.csv)");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            String csvPath = matcher.group(1);
            System.out.println(csvPath);
            return csvPath;
        }
        return null;

    }
}
