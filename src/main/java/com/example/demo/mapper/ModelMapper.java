package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Model;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelMapper extends BaseMapper<Model> {
}
