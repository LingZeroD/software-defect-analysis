package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Model {
    @TableId(type = IdType.AUTO)
    private int id;
    private String creator;
    private String algorithm;
    private Date create_time;
    private String data;
    private double accuracy;
    private double precision;
    private double f1;
    private double auc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public double getAuc() {
        return auc;
    }

    public void setAuc(double auc) {
        this.auc = auc;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", creator='" + creator + '\'' +
                ", algorithm='" + algorithm + '\'' +
                ", create_time=" + create_time +
                ", data='" + data + '\'' +
                ", accuracy=" + accuracy +
                ", precision=" + precision +
                ", f1=" + f1 +
                ", auc=" + auc +
                '}';
    }
}
