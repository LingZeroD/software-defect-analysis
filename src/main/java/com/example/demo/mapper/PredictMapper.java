package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Model;
import com.example.demo.entity.Predict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PredictMapper extends BaseMapper<Predict> {

    @Select("select * from predict where username= #{username}")
    List<Predict> getList(@Param("username")String username);

}
