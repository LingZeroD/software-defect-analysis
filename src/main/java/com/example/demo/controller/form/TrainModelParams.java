package com.example.demo.controller.form;

public class TrainModelParams {
    /**
     * 软件项目名称（小写）
     */
    private String dataset = "hbase";

    /**
     * 批处理大小
     */
    private int batch_size = 8;

    /**
     * 训练轮数
     */
    private int num_epochs = 50;

    /**
     * 词嵌入维度
     */
    private int embed_dim = 50;

    /**
     * 词级别GRU层隐藏层维度
     */
    private int word_gru_hidden_dim = 64;

    /**
     * 句子级别GRU层隐藏层维度
     */
    private int sent_gru_hidden_dim = 64;

    /**
     * 词级别GRU层数
     */
    private int word_gru_num_layers = 1;

    /**
     * 句子级别GRU层数
     */
    private int sent_gru_num_layers = 1;

    /**
     * dropout概率
     */
    private double dropout = 0.2;

    /**
     * 学习率
     */
    private double lr = 0.001;

    /**
     * 实验名称
     */
    private String exp_name = "";

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public int getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(int batch_size) {
        this.batch_size = batch_size;
    }

    public int getNum_epochs() {
        return num_epochs;
    }

    public void setNum_epochs(int num_epochs) {
        this.num_epochs = num_epochs;
    }

    public int getEmbed_dim() {
        return embed_dim;
    }

    public void setEmbed_dim(int embed_dim) {
        this.embed_dim = embed_dim;
    }

    public int getWord_gru_hidden_dim() {
        return word_gru_hidden_dim;
    }

    public void setWord_gru_hidden_dim(int word_gru_hidden_dim) {
        this.word_gru_hidden_dim = word_gru_hidden_dim;
    }

    public int getSent_gru_hidden_dim() {
        return sent_gru_hidden_dim;
    }

    public void setSent_gru_hidden_dim(int sent_gru_hidden_dim) {
        this.sent_gru_hidden_dim = sent_gru_hidden_dim;
    }

    public int getWord_gru_num_layers() {
        return word_gru_num_layers;
    }

    public void setWord_gru_num_layers(int word_gru_num_layers) {
        this.word_gru_num_layers = word_gru_num_layers;
    }

    public int getSent_gru_num_layers() {
        return sent_gru_num_layers;
    }

    public void setSent_gru_num_layers(int sent_gru_num_layers) {
        this.sent_gru_num_layers = sent_gru_num_layers;
    }

    public double getDropout() {
        return dropout;
    }

    public void setDropout(double dropout) {
        this.dropout = dropout;
    }

    public double getLr() {
        return lr;
    }

    public void setLr(double lr) {
        this.lr = lr;
    }

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    @Override
    public String toString() {
        return "TrainModelParams{" +
                "dataset='" + dataset + '\'' +
                ", batch_size=" + batch_size +
                ", num_epochs=" + num_epochs +
                ", embed_dim=" + embed_dim +
                ", word_gru_hidden_dim=" + word_gru_hidden_dim +
                ", sent_gru_hidden_dim=" + sent_gru_hidden_dim +
                ", word_gru_num_layers=" + word_gru_num_layers +
                ", sent_gru_num_layers=" + sent_gru_num_layers +
                ", dropout=" + dropout +
                ", lr=" + lr +
                ", exp_name='" + exp_name + '\'' +
                '}';
    }
}
