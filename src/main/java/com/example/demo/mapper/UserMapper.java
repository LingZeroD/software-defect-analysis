package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

//用于操作用户表,MyBaits会根据Mapper注解，动态实现UserMapper接口（实现类），动态代理技术
//Spring会自动创建UserMapper接口实现类对应的实例
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //根据token查询信息   select * from user where username =
    @Select("select * from User where username = #{username}")
    User selectByName(String username);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User getInfo(@Param("username") String username, @Param("password") String password);



////   查询用户及其所有的订单
//    @Select("select * from t_user")
//    @Results(
//            {
//                    @Result(column = "id",property = "id"),
//                    @Result(column = "username",property = "username"),
//                    @Result(column = "password",property = "password"),
//                    @Result(column = "birthday",property = "birthday"),
//                    @Result(column = "id",property = "orders",javaType = List.class,
//                            many=@Many(select = "com.example.mpdemo.mapper.OrderMapper.selectByUid")
//                    )
//            }
//    )
//    List<User> selectAllUserAndOrders();
}
