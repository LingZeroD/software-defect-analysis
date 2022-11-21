package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
class PredictControllerTest extends PredictController{

    @Test
    void predict() throws IOException {
        Integer model = 30;
        File file = new File("testdata\\predict\\predict.csv");
        MultipartFile pre_data = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
        String username = "aki";
        //通过继承和this调用方法，否则mapper报空
        this.predict(model, username, pre_data);
        this.predict(100,"(●'◡'●)",pre_data);
    }

    @Test
    void res() {
        this.res("aki");
        System.out.println(this.res("(●'◡'●)"));
    }
}