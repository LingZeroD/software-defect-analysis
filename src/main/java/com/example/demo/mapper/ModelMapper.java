package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Model;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    List<Model> getModelList(@Param("algorithm") Integer algorithm, @Param("creator") String creator);

}
