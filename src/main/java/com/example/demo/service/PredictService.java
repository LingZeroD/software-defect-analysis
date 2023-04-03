package com.example.demo.service;

import com.example.demo.controller.form.PredictWithinParams;
import com.example.demo.entity.Predict;
import com.example.demo.mapper.PredictMapper;
import com.example.demo.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PredictService {

    @Autowired
    private PredictMapper predictMapper;
    @Autowired
    private AsyncService async;


    public Integer predict(Integer model, String username, MultipartFile pre_data, PredictWithinParams predictWithinParams) {
        if (pre_data.isEmpty()) {
            return ResultCode.FILEEMPTY;
        }
        Predict predict = new Predict();
        predict.setModel(model);
        predict.setUsername(username);
        Date time=new Date();
//            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        predict.setTime(time);
        predictMapper.insert(predict);

        //获取预测记录id
        System.out.println("ID"+predict.getId());
        int number = predict.getId();
        try {
            // 获取文件名
            String fileName = pre_data.getOriginalFilename();
            String path = "\\predict\\" + number + fileName;
            int size = (int) pre_data.getSize();
            System.out.println(fileName + "----->" + size);
            File f = getFile(pre_data, path);
            path = "\\download"+path;
            predict.setData(path);
            predictMapper.updateById(predict);
            pre_data.transferTo(f);
            System.out.println("path----->"+path);
            async.predict(predictMapper, predict, path, model);
//            async.predictModelWithin(predictWithinMapper, predictWithin, username,predictWithinParams);
            return ResultCode.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultCode.FILEERROR;
        }
    }

    private File getFile(MultipartFile data, String path){
        Path rootPath = Paths.get("download");
        File rootPathDir = new File(rootPath.toString());
        File dest = new File(rootPathDir.getAbsolutePath()+path);
        return dest;
    }


    public List<Predict> getResult(String username) {
        System.out.println("username:"+username);
        List<Predict> predicts = predictMapper.getList(username);
        try{
            for (Predict p:predicts) {
                String data_path = System.getProperty("user.dir")+p.getData();
                byte[] d = Files.readAllBytes(Paths.get(data_path));
                String pre_data = Base64.getEncoder().encodeToString(d);
                String res_path = System.getProperty("user.dir")+p.getResult();
                byte[] r = Files.readAllBytes(Paths.get(res_path));
                String result = Base64.getEncoder().encodeToString(r);
                p.setData(pre_data);
                p.setResult(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return predicts;
    }


}