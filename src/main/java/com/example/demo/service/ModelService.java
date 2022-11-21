package com.example.demo.service;

import com.example.demo.entity.Model;
import com.example.demo.mapper.ModelMapper;
import com.example.demo.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ModelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AsyncService async;

    public List<Model> getModel(Integer algorithm, String creator) {
        System.out.println(algorithm+"creator:"+creator);
        List<Model> models =  modelMapper.getModelList(algorithm, creator);
        try{
            for (Model m:models) {
                String path = System.getProperty("user.dir")+m.getData();
                byte[] b = Files.readAllBytes(Paths.get(path));
                String data = Base64.getEncoder().encodeToString(b);
                if(m.getAlgorithm()==1)m.setParam1(m.getParam3());
                m.setData(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return models;
    }

    public Integer train(Integer algorithm, String des, MultipartFile data, String creator, int param1, int param2) {
        if (data.isEmpty()) {
            return ResultCode.FILEEMPTY;
        }
        Model model = new Model();
        model.setAlgorithm(algorithm);
        model.setDes(des);
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
        // 获取模型id
        System.out.println("ID"+model.getId());
        int number = model.getId();
        try {
            // 获取文件名
            String fileName = data.getOriginalFilename();
            String path = "\\train\\" + number + fileName;
            int size = (int) data.getSize();
            File f = getFile(data,path);
            path = "\\download" + path;
            model.setData(path);
            modelMapper.updateById(model);
            data.transferTo(f);
            System.out.println("path----->"+path + "----->" + size);

            if(verify(path)==ResultCode.FILEFORMATEERROR){
                modelMapper.deleteById(number);
                return ResultCode.FILEFORMATEERROR;
            };
            async.tarin(modelMapper, model,path,algorithm, param1,number);
            return ResultCode.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            modelMapper.deleteById(number);
            return ResultCode.FILEERROR;
        }
    }

    private Integer verify(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+path));
            reader.readLine();
            String[] attInfo = reader.readLine().split(","); // attributes info
            int numAttributes = attInfo.length - 1;
            boolean[] isCategory = new boolean[numAttributes + 1];
            for (int i = 0; i < isCategory.length; i++) {
                isCategory[i] = Integer.parseInt(attInfo[i]) == 1 ? true : false;
            }
            int numInstnaces = 0;
            while (reader.readLine() != null) {
                numInstnaces++;
            }
        } catch (Exception e) {
            System.out.println("exception"+e);
            return ResultCode.FILEFORMATEERROR;
        }
        return ResultCode.SUCCESS;
    }

    //获取存储的文件
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
