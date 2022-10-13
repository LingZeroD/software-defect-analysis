package com.example.demo.controller;

import com.example.demo.entity.Model;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    ResultCode resultCode = new ResultCode();

    @PostMapping("/login")
    // querystring: username=zhangsan&password=123   User user,String username,String password
    // json: {username:zhangsan,password:123}
    // 如果前端传递的数据是json格式，必须使用对象接收，同时需要添加@RequestBody
    public Result login(@RequestBody User user){
        Result res = new Result();
        Map<String, Object> map = new HashMap<>();
        System.out.println("username"+user.getUsername()+"password"+user.getPassword());
        try {
            User info = userService.LoginIn(user.getUsername(),user.getPassword());
            if(info!=null){
                String token = JwtUtils.generateToken(user.getUsername());
                map.put("token",token);
                res.setMessage("登录成功");
                res.setData(map);
            }else {
                res.setCode(ResultCode.LOGINERROR);
                res.setMessage(resultCode.getMsg(ResultCode.LOGINERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(ResultCode.ERROR);
            res.setMessage(resultCode.getMsg(ResultCode.ERROR));
        }

        return res;
    }

    @GetMapping("/info")  // "token:xxx"
    public Result info(String token){
        Result res = new Result();
        Map<String, Object> map = new HashMap<>();
        try {
            String username = JwtUtils.getClaimsByToken(token).getSubject();
            User user = userService.getInfo(username);
            if(user!=null){
                String url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.gmz88.com%2Fuploadimg%2Fimage%2F20190116%2F15476240655c3ede81c64116.77854307.jpeg&refer=http%3A%2F%2Fimg.gmz88.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1667038105&t=0374e9cdf9ecb5833b85cbe9bae9a4c8";
                map.put("id",user.getId());
                map.put("username",username);
                map.put("password",user.getPassword());
                map.put("email",user.getEmail());
                map.put("avatar",url);
                res.setMessage("返回成功");
                res.setData(map);
            }else {
                res.setCode(ResultCode.INFOERROR);
                res.setMessage(resultCode.getMsg(ResultCode.INFOERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(ResultCode.ERROR);
            res.setMessage(resultCode.getMsg(ResultCode.ERROR));
        }
        return res;
    }



    @PostMapping("/logout")  // "token:xxx"
    public Result logout(){
        Result res = new Result();
        res.setMessage("登出成功");
        return res;
    }

    //  用户注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println("user"+user);
        Result res = new Result();
        try {
            int r = userService.register(user);
            if(r > 0){
                res.setMessage("注册成功");
            }else{
                res.setCode(ResultCode.REGERROR);
                res.setMessage(resultCode.getMsg(ResultCode.REGERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(ResultCode.ERROR);
            res.setMessage(resultCode.getMsg(ResultCode.ERROR));
        }

        return res;
    }

    // 更新用户信息
    //  插入数据
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        Result res = new Result();
        try {
            int r = userService.update(user);
            if(r > 0){
                res.setMessage("用户信息更新成功");
            }else{
                res.setCode(ResultCode.UPDATEERROR);
                res.setMessage(resultCode.getMsg(ResultCode.UPDATEERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setCode(ResultCode.ERROR);
            res.setMessage(resultCode.getMsg(ResultCode.ERROR));
        }

        return res;
    }
//
////  条件查询
//    @GetMapping("/user/find")
//    public List<User> findByCond(){
//        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("username","zhangsan");
//        return userMapper.selectList(queryWrapper);
//    }
//
////  分页查询
//    @GetMapping("/user/findByPage")
//    public IPage findByPage(){
//        //设置起始值及每页条数
//        Page<User> page = new Page<>(0,2);
//        IPage iPage = userMapper.selectPage(page,null);
//        return iPage;
//    }
//


}
