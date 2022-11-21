package com.example.demo.controller;
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

import static org.junit.Assert.assertNotNull;

@SpringBootTest()
@RunWith(SpringRunner.class)
class ModelControllerTest{

    @Autowired
    ModelController modelController = new ModelController();

    @Test
    void model() {
        Integer algorithm = 1;
        String creator = "aki";
        assertNotNull(modelController.model(algorithm,creator));
    }

    @Test
    void testModel() {
        assertNotNull(modelController.model());
    }

    @Test
    void train() throws IOException {
        Integer algorithm = 1;
        String des = "test";
        File file = new File("testdata\\train\\Lucene.csv");
        MultipartFile data = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
        String creator = "aki";
        Integer param1 = 9;
        Integer param2 = 0;
        modelController.train(algorithm, des, data, creator, param1, param2);
        modelController.train(2,"test2",null,creator,param1,param2);
    }
}