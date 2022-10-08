package com.example.demo.controller;

import com.example.demo.service.FileService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    FileService fileService;
    ResultCode resultCode = new ResultCode();

    @GetMapping("/download")  // "token:xxx"
    public Result download(Integer id, String type){
        Result res = new Result();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String file = fileService.getFile(id, type);
            map.put("file",file);
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
