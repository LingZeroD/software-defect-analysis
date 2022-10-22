package com.example.demo.service;

import com.example.demo.algorithm.auxiliary.Classifier;
import com.example.demo.algorithm.auxiliary.DataSet;
import com.example.demo.algorithm.auxiliary.Evaluation;
import com.example.demo.algorithm.predict.PredictDataSet;
import com.example.demo.algorithm.tool.SaveAndLoad;
import com.example.demo.entity.Model;
import com.example.demo.entity.Predict;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.mapper.PredictMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;

@Component
public class AsyncService {


    @Resource
    private ModelMapper modelMapper;


    @Async
    public void tarin(ModelMapper modelMapper, Model model, String data, int type, int param, int id) {
        String algorithm =(type==1)?"RandomForest":"AdaBoost";
        System.out.println(algorithm);
        int classifier = param;
        DataSet dataset = new DataSet(data);
        Evaluation eva = new Evaluation(modelMapper, dataset, algorithm, classifier,"model" +  model.getId() + ".dat");
        eva.calculateMetrics(model);
    }

    public  void predict(PredictMapper predictMapper, Predict predict, String path, int model) {

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
            prediction = (c.predict(features[i])==1.0)?"buggy":"clean";
            try {
                FileWriter out2 = new FileWriter(result, true);
                out2.append(prediction + "\r" );
                out2.close();
                predict.setResult(res);
                predict.setState(1);
                Date time=new Date();
                predict.setFinish(time);
                predictMapper.updateById(predict);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

    }

}
