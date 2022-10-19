package com.example.demo.controller;

import com.example.demo.entity.Predict;
import com.example.demo.service.PredictService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PredictController {
    @Autowired
    PredictService predictService;
    ResultCode resultCode = new ResultCode();

    @PostMapping("/predict")  // "token:xxx"
    public Result predict(Integer model, String username, MultipartFile pre_data){
        Result res = new Result();
        try {
            Integer code = predictService.predict(model,username,pre_data);
            res.setCode(code);
            res.setMessage(resultCode.getMsg(code));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(resultCode.ERROR);
            res.setMessage(resultCode.getMsg(resultCode.ERROR));
        }
        return res;
    }

    @GetMapping("/result")  // "token:xxx"
    public Result res(String username){
        Result res = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Predict> list = predictService.getResult(username);
            map.put("modellist",list);
            res.setMessage("返回成功");
            res.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(resultCode.ERROR);
            res.setMessage(resultCode.getMsg(resultCode.ERROR));
        }
        return res;
    }
}
