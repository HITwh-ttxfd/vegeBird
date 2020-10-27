package com.example.demo.Entity;
//author:孙宝臻
//date:2020-10-26
//function:推荐兼职类
public class RecommendTask {
    //    兼职id
    //    格式t00000001,t00001011(t后面8位，要补全)
    private String id;
    //    标题
    private String title;
    //    时限
    private String term;
    //    薪酬
    private String pay;
    //    已投标人数
    private String bid;
    //  简介
    private String content;
    //  详情链接
    private String link;
    //  大类如开发与设计之分，存的是英文
    private String outType;
    //  大类如开发与设计之分，存的是中文
    private String outTypeName;
    //  小类如小程序与网站之分。
    private String inType;
    //  小类名字。存的是中文
    private String inTypeName;
    //  来源网站
    private String source;

    public RecommendTask() {
    }

    public RecommendTask(String id, String title, String term, String pay, String bid, String content, String link, String outType, String outTypeName, String inType, String inTypeName, String source) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.pay = pay;
        this.bid = bid;
        this.content = content;
        this.link = link;
        this.outType = outType;
        this.outTypeName = outTypeName;
        this.inType = inType;
        this.inTypeName = inTypeName;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getOutTypeName() {
        return outTypeName;
    }

    public void setOutTypeName(String outTypeName) {
        this.outTypeName = outTypeName;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getInTypeName() {
        return inTypeName;
    }

    public void setInTypeName(String inTypeName) {
        this.inTypeName = inTypeName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "RecommendTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", term='" + term + '\'' +
                ", pay='" + pay + '\'' +
                ", bid='" + bid + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", outType='" + outType + '\'' +
                ", outTypeName='" + outTypeName + '\'' +
                ", inType='" + inType + '\'' +
                ", inTypeName='" + inTypeName + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
