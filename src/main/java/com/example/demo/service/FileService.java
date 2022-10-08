package com.example.demo.service;

import com.example.demo.entity.Model;
import com.example.demo.entity.Predict;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.mapper.PredictMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FileService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private PredictMapper predictMapper;

    public String getFile(Integer id,String type) {
        String path = "";
        String file = "";
        if(type=="train"){
            Model m = modelMapper.selectById(id);
            path = System.getProperty("user.dir") + m.getData();
        }else {
            Predict p = predictMapper.selectById(id);
            if(type=="predict"){
                path = System.getProperty("user.dir") + p.getData();
            }else {
                path = System.getProperty("user.dir") + p.getResult();
            }
        }
        try {
            byte[] d = Files.readAllBytes(Paths.get(path));
            file = Base64.getEncoder().encodeToString(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
