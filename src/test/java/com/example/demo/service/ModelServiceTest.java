package com.example.demo.service;

import com.example.demo.entity.Model;
import com.example.demo.utils.ResultCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest()
@RunWith(SpringRunner.class)
class ModelServiceTest {

    @Autowired
    private ModelService modelService;

    @Test
    public void getModel1() {
        assertNotNull(modelService.getModel(null, ""));
    }

    @Test
    public void getModel2() {
        List<Model> models = modelService.getModel(0,"aki");
        for(Model m:models){
            assertEquals(m.getAlgorithm(),0);
            assertEquals(m.getCreator(),"aki");
        }
    }

    @Test
    public void getModel3() {
        assertTrue(modelService.getModel(null,"sana").size()==0);
    }

    @Test
    public void getModelId() {
        assertNotNull(modelService.getModelId());
    }

    @Test
    void train() throws IOException {
        String des = "test";
        File file1 = new File("testdata\\train\\test1.csv");
        MultipartFile data1 = new MockMultipartFile("file", file1.getName(), null, new FileInputStream(file1));
        File file0 = new File("testdata\\train\\test0.csv");
        MultipartFile data0 = new MockMultipartFile("file", file0.getName(), null, new FileInputStream(file0));
        String creator = "aki";
        Integer param1 = 9;
        Integer param2 = 0;
        assertEquals(modelService.train(1, des, data1, creator, param1, param2), ResultCode.SUCCESS);
        assertEquals(modelService.train(0, des, data0, creator, param1, param2),ResultCode.FILEFORMATEERROR);
    }
}