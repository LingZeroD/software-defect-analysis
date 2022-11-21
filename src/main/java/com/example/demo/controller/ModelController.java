package com.example.demo.controller;

import com.example.demo.entity.Model;
import com.example.demo.service.ModelService;
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
public class ModelController {

    @Autowired
    ModelService modelService;
    ResultCode resultCode = new ResultCode();

    @GetMapping("/modellist")  // "token:xxx"
    public Result model(@RequestParam(required = false,name = "algorithm") Integer algorithm,
                        @RequestParam(required = false,name = "creator") String creator){
        Result res = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Model> list = modelService.getModel(algorithm, creator);
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

    @GetMapping("/model")  // "token:xxx"
    public Result model(){
        Result res = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Integer> list = modelService.getModelId();
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

    @PostMapping("/train")  // "token:xxx"
    public Result train(Integer algorithm, String des, MultipartFile data, String creator, int param1, int param2){
        Result res = new Result();
        try {
            Integer code = modelService.train(algorithm, des, data, creator, param1, param2);
            res.setCode(code);
            res.setMessage(resultCode.getMsg(code));
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(resultCode.ERROR);
            res.setMessage(resultCode.getMsg(resultCode.ERROR));
        }
        return res;
    }
}
