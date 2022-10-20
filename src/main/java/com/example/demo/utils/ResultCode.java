package com.example.demo.utils;

public class ResultCode {

    public static Integer SUCCESS = 200; //成功
    public static Integer ERROR = 100; //服务器错误
    public static Integer LOGINERROR = 101; //用户名或密码错误
    public static Integer INFOERROR = 102; //用户信息不匹配
    public static Integer REGERROR = 103; //用户名已存在
    public static Integer UPDATEERROR = 104; //更新失败
    public static Integer FILEEMPTY=105;//文件为空
    public static Integer FILEERROR =106;//文件上传失败
    public static Integer FILEFORMATEERROR =107;//重复操作

    public String getMsg(Integer code){
        String res = "";
        if(code.equals(SUCCESS)){
            res = "成功";
        }else if(code.equals(ERROR)){
            res = "服务器错误";
        }else if(code.equals(LOGINERROR)){
            res = "用户名或密码错误";
        }else if(code.equals(INFOERROR)){
            res = "用户信息不匹配";
        }else if(code.equals(REGERROR)){
            res = "用户名已存在";
        }else if(code.equals(UPDATEERROR)){
            res = "error";
        }else if(code.equals(FILEEMPTY)){
            res = "上传文件为空";
        }else if(code.equals(FILEERROR)){
            res = "文件上传失败";
        }else if(code.equals(FILEFORMATEERROR)){
            res = "文件格式错误";
        }
        return res;
    }

}




