package com.example.demo.controller.form;

public class PredictWithinParams {
    /**
     * 软件项目名称（小写）
     */
    private String dataset = "activemq";
    /**
     * 词嵌入大小
     */
    private int embed_dim = 50;
    /**
     * 词级别GRU隐藏层大小
     */
    private int word_gru_hidden_dim = 64;
    /**
     * 句子级别GRU隐藏层大小
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
     * 实验名称
     */
    private String exp_name = "";
    /**
     * 要加载模型的时期
     */
    private int target_epochs = 160;
    /**
     * dropout概率
     */
    private double dropout = 0.2f;

    // 添加默认构造函数
    public PredictWithinParams() {
    }

    // 添加带参构造函数
    public PredictWithinParams(String dataset, int embed_dim, int word_gru_hidden_dim, int sent_gru_hidden_dim,
                               int word_gru_num_layers, int sent_gru_num_layers, String exp_name, int target_epochs,
                               double dropout) {
        this.dataset = dataset;
        this.embed_dim = embed_dim;
        this.word_gru_hidden_dim = word_gru_hidden_dim;
        this.sent_gru_hidden_dim = sent_gru_hidden_dim;
        this.word_gru_num_layers = word_gru_num_layers;
        this.sent_gru_num_layers = sent_gru_num_layers;
        this.exp_name = exp_name;
        this.target_epochs = target_epochs;
        this.dropout = dropout;
    }

    // 添加 getter 和 setter 方法

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
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

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public int getTarget_epochs() {
        return target_epochs;
    }

    public void setTarget_epochs(int target_epochs) {
        this.target_epochs = target_epochs;
    }

    public double getDropout() {
        return dropout;
    }

    public void setDropout(double dropout) {
        this.dropout = dropout;
    }

    @Override
    public String toString() {
        return "PredictWithinParams{" +
                "dataset='" + dataset + '\'' +
                ", embed_dim=" + embed_dim +
                ", word_gru_hidden_dim=" + word_gru_hidden_dim +
                ", sent_gru_hidden_dim=" + sent_gru_hidden_dim +
                ", word_gru_num_layers=" + word_gru_num_layers +
                ", sent_gru_num_layers=" + sent_gru_num_layers +
                ", exp_name='" + exp_name + '\'' +
                ", target_epochs=" + target_epochs +
                ", dropout=" + dropout +
                '}';
    }
}
