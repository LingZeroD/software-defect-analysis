package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Predict;
import com.example.demo.entity.PredictWithin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PredictWithinMapper extends BaseMapper<PredictWithin> {
    @Select("select * from predict_within where username= #{username}")
    List<PredictWithin> getList(@Param("username")String username);
}
