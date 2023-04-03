package com.example.demo.service;

import com.example.demo.controller.form.PredictWithinParams;
import com.example.demo.entity.PredictWithin;
import com.example.demo.mapper.PredictWithinMapper;
import com.example.demo.utils.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)//开启新事务
public class PredictWithinService {
    @Autowired
    private AsyncService async;

    @Autowired
    private PredictWithinMapper predictWithinMapper;

    public Integer predictWithin(String username, PredictWithinParams predictParams) {
        //使用jackson将predictParams转化为json
        PredictWithin predictWithin = new PredictWithin();
        try {
            String  predictParamsJson =new ObjectMapper().writeValueAsString(predictParams);
            predictWithin.setDataset(predictParams.getDataset());
            predictWithin.setUsername(username);
            predictWithin.setCreateTime(new Date());
            predictWithin.setPythonArgs(predictParamsJson);
            predictWithinMapper.insert(predictWithin);
            int number = predictWithin.getId();
            async.predictModelWithin(predictWithinMapper,predictWithin,username, predictParams);
            return ResultCode.SUCCESS;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResultCode.FILEERROR;
        }

    }
    public List<PredictWithin> getResults(String username) throws JsonProcessingException {
        List<PredictWithin> list = predictWithinMapper.getList(username);
        return list;
    }
}
