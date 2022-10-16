package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModelMapper extends BaseMapper<Model> {

    @Select("<script>"
            +"select * from model where 1=1"
            +"<if test='algorithm!= null'>"
            +"and algorithm= #{algorithm}"
            +"</if>"
            +"<if test='creator!=\"\" and creator!= null'>"
            +"and creator like CONCAT(CONCAT('%',#{creator}),'%')"
            +"</if>"
            +"</script>")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "des",property = "des"),
            @Result(column = "algorithm",property = "algorithm"),
            @Result(column = "param1",property = "param1"),
            @Result(column = "param2",property = "param2"),
            @Result(column = "param3",property = "param3"),
            @Result(column = "param4",property = "param4"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "create_time",property = "create_time"),
            @Result(column = "data",property = "data"),
            @Result(column = "accuracy",property = "accuracy"),
            @Result(column = "prec",property = "prec"),
            @Result(column = "f1",property = "f1"),
            @Result(column = "auc",property = "auc")
    })
    List<Model> getModelList(@Param("algorithm") Integer algorithm, @Param("creator") String creator);

    @Select("select id from Model order by id")
    List<Integer> getModelId();
}
