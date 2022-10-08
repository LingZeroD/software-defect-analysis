package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class Model {
    @TableId(type = IdType.AUTO)
    private int id;
    private String des;
    private int algorithm;
    private double param1;
    private double param2;
    private double param3;
    private double param4;
    private String creator;
    private Date create_time;
    private String data;
    private double accuracy;
    private double prec;
    private double f1;
    private double auc;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    public double getParam1() {
        return param1;
    }

    public void setParam1(double param1) {
        this.param1 = param1;
    }

    public double getParam2() {
        return param2;
    }

    public void setParam2(double param2) {
        this.param2 = param2;
    }

    public double getParam3() {
        return param3;
    }

    public void setParam3(double param3) {
        this.param3 = param3;
    }

    public double getParam4() {
        return param4;
    }

    public void setParam4(double param4) {this.param4 = param4;}

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

    public double getPrec() {
        return prec;
    }

    public void setPrec(double prec) {
        this.prec = prec;
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
                ", des='" + des + '\'' +
                ", algorithm='" + algorithm + '\'' +
                ", create_time=" + create_time +
                ", data='" + data + '\'' +
                ", accuracy=" + accuracy +
                ", precision=" + prec +
                ", f1=" + f1 +
                ", auc=" + auc +
                '}';
    }
}
