package com.example.demo.service;

import com.example.demo.utils.ResultCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
class PredictServiceTest extends PredictService {
//    PredictService predictService = new PredictService();
    @Test
    void predict() throws IOException {
        Integer model = 30;
        File file1 = new File("testdata\\predict\\predict1.csv");
        MultipartFile pre_data1 = new MockMultipartFile("file", file1.getName(), null, new FileInputStream(file1));
        File file0 = new File("testdata\\predict\\predict0.csv");
        MultipartFile pre_data0 = new MockMultipartFile("file", file0.getName(), null, new FileInputStream(file0));
        String username = "aki";
        //通过继承和this调用方法，否则mapper报空
        assertEquals(this.predict(model, username, pre_data1), ResultCode.SUCCESS);
        assertEquals(this.predict(model, username, pre_data0), ResultCode.FILEFORMATEERROR);
    }

    @Test
    public void getResult1() {
        assertNotNull(this.getResult("aki"));
    }

    @Test
    public void getResult2() {
        assertTrue(this.getResult("123").size()==0);
    }
}