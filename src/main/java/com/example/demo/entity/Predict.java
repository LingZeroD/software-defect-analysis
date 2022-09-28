package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Predict {
    @TableId(type = IdType.AUTO)
    private int id;
    private int model_id;
    private String username;
    private String pre_data;
    private String result;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPre_data() {
        return pre_data;
    }

    public void setPre_data(String pre_data) {
        this.pre_data = pre_data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Predict{" +
                "id=" + id +
                ", model_id=" + model_id +
                ", username='" + username + '\'' +
                ", pre_data='" + pre_data + '\'' +
                ", result='" + result + '\'' +
                ", time=" + time +
                '}';
    }
}
