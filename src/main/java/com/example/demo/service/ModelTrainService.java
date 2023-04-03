package com.example.demo.service;

import com.example.demo.controller.form.TrainModelParams;
import com.example.demo.entity.ModelTrain;
import com.example.demo.mapper.ModelTrainMapper;
import com.example.demo.utils.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ModelTrainService {
    @Autowired
    private AsyncService async;
    @Autowired
    private ModelTrainMapper modelTrainMapper;

    public Integer trainModel(String modelName, String username, String dataset, TrainModelParams params) {
        List<ModelTrain> modelTrainList = modelTrainMapper.getModelTrainList(modelName, username, dataset, params.getExp_name());
        ModelTrain modelTrain;
        boolean flag = false;
        if (modelTrainList.size() != 0) {
            modelTrain = modelTrainList.get(0);
            flag = true;

        } else {
            modelTrain = new ModelTrain();
            modelTrain.setDataset(dataset);
            modelTrain.setUsername(username);
            modelTrain.setModelName(modelName);
            modelTrain.setCreateTime(new Date());
            modelTrain.setExpName(params.getExp_name());

        }
        try {
            modelTrain.setPythonArgs(new ObjectMapper().writeValueAsString(params));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultCode.FILEERROR;
        }
        if (flag) {
            modelTrainMapper.updateById(modelTrain);
        } else {
            modelTrainMapper.insert(modelTrain);
        }
        if(modelName.toLowerCase(Locale.ROOT)//转化为小写
                .equals("deeplinedp")){//如果是DeepLineDP模型，使用异步方法
        async.trainDPModel(modelTrainMapper, modelTrain, params);
        }
        return ResultCode.SUCCESS;
    }
    public List<ModelTrain> getModelTrainList(String modelName, String username, String dataset,String expName) {
        System.out.println(modelName+" "+username+" "+dataset);
        return modelTrainMapper.getModelTrainList(modelName, username, dataset,expName);
    }

}
