package com.example.demo.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@RunWith(SpringRunner.class)
class ModelMapperTest {

    @Autowired
    ModelMapper modelMapper;
    @Test
    void getModelList() {
        modelMapper.getModelList(1,"aki");
        modelMapper.getModelList(0,"123");
    }

    @Test
    void getId() {
        modelMapper.getId();
    }

    @Test
    void getModelId() {
        modelMapper.getModelId();
    }
}


