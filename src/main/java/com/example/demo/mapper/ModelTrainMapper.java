package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ModelTrain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ModelTrainMapper extends BaseMapper<ModelTrain> {

    @Select("<script>"
            + "select * from model_train where 1=1 "
            + "<if test='model_name!= null and model_name!=\"\"'>"
            + " and model_name= #{model_name}"
            + "</if>"
            + "<if test='username!=\"\" and username!= null'>"
            + " and username = #{username}"
            + "</if>"
            + "<if test='dataset!=\"\" and dataset!= null'>"
            + " and dataset = #{dataset}"
            + "</if>"
            + "<if test='exp_name!=\"\" and exp_name!= null'>"
            + " and exp_name = #{exp_name}"
            + "</if>"
            + "</script>")
    List<ModelTrain> getModelTrainList(@Param("model_name") String modelName,@Param("username") String username, @Param("dataset") String dataset, @Param("exp_name") String expName);

    @Select("SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='mydb' AND TABLE_NAME='model_train'")
    Integer getId();

    @Select("select id from model_train order by id")
    List<Integer> getModelTrainId();
}
