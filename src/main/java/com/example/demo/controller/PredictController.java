package com.example.demo.controller;

import com.example.demo.controller.form.PredictWithinParams;
import com.example.demo.entity.Predict;
import com.example.demo.entity.PredictWithin;
import com.example.demo.service.PredictService;
import com.example.demo.service.PredictWithinService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    PredictWithinService predictWithinService;
    ResultCode resultCode = new ResultCode();

    @PostMapping("/predict")  // "token:xxx"
    public Result predict(@RequestParam("model")Integer model,  @RequestParam("username")String username,@RequestParam("pre_data") MultipartFile pre_data,@RequestParam("params")  String params){
        Result res = new Result();
        try {
            PredictWithinParams pythonScriptParams = new ObjectMapper().readValue(params, PredictWithinParams.class);
            Integer code = predictService.predict(model,username,pre_data,pythonScriptParams);

            res.setCode(code);
            res.setMessage(resultCode.getMsg(code));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(resultCode.ERROR);
            res.setMessage(resultCode.getMsg(resultCode.ERROR));
        }
        return res;
    }
    @PostMapping("/predictWithin")  // "token:xxx"
    public Result predictWithin(@RequestParam("username")String username,@RequestParam("params")  String params){
        Result res = new Result();
        try {
            PredictWithinParams predictWithinParams = new ObjectMapper().readValue(params, PredictWithinParams.class);
            Integer code = predictWithinService.predictWithin(username, predictWithinParams);
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
    @GetMapping("/resultWithin")  // "token:xxx"
    public Result resWithin(String username){
        Result res = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PredictWithin> list = predictWithinService.getResults(username);
            map.put("result_list",list);
            map.put("total",list.size());
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
