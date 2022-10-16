package com.example.demo.service;

import com.example.demo.entity.Model;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.utils.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
public class ModelService {

    @Resource
    private ModelMapper modelMapper;

    public List<Model> getModel(Integer algorithm, String creator) {
        System.out.println(algorithm+"creator:"+creator);
        List<Model> models =  modelMapper.getModelList(algorithm, creator);
        try{
            for (Model m:models) {
                String path = System.getProperty("user.dir")+m.getData();
                byte[] b = Files.readAllBytes(Paths.get(path));
                String data = Base64.getEncoder().encodeToString(b);
                m.setData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return models;
    }

    public Integer train(Integer algorithm, String des, MultipartFile data, String creator, double param1, double param2) {
        if (data.isEmpty()) {
            return ResultCode.FILEEMPTY;
        }
        // 获取文件名
        String fileName = data.getOriginalFilename();
        String path = "\\train\\" + fileName;
        int size = (int) data.getSize();
        System.out.println(fileName + "----->" + size);
        File f = getFile(data,path);
        path = "\\download"+path;
        System.out.println("path----->"+path);
        try {
            data.transferTo(f);
            Model model = new Model();
            model.setAlgorithm(algorithm);
            model.setDes(des);
            model.setData(path);
            model.setCreator(creator);
            if(algorithm==0){
                model.setParam1(param1);
                model.setParam2(param2);
            }else {
                model.setParam3(param1);
                model.setParam4(param2);
            }
            Date time=new Date();
//            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            model.setCreate_time(time);
            modelMapper.insert(model);
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

    public List<Integer> getModelId() {
        return modelMapper.getModelId();
    }
}
