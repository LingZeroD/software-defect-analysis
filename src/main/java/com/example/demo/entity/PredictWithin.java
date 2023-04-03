package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
@TableName("predict_within")
public class PredictWithin {
  @TableId(type = IdType.AUTO)
  private int id;
  private String username;
  private String dataset;

  private String resultPath;
  private String result;
  private String pythonArgs;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date createTime;
  private int state;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date finishTime;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDataset() {
    return dataset;
  }

  public void setDataset(String dataset) {
    this.dataset = dataset;
  }

  public String getResultPath() {
    return resultPath;
  }

  public void setResultPath(String resultPath) {
    this.resultPath = resultPath;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getPythonArgs() {
    return pythonArgs;
  }

  public void setPythonArgs(String pythonArgs) {
    this.pythonArgs = pythonArgs;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Date getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(Date finishTime) {
    this.finishTime = finishTime;
  }

  @Override
  public String toString() {
    return "PredictWithin{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", dataset='" + dataset + '\'' +
            ", result='" + result + '\'' +
            ", pythonArgs='" + pythonArgs + '\'' +
            ", createTime=" + createTime +
            ", state=" + state +
            ", finishTime=" + finishTime +
            '}';
  }
}
