package com.example.demo.Entity;
//author:孙宝臻
//date:2020-10-26
//function:推荐教程
//
//change:
public class Course {
//  教程id
//    格式c00000001,c00001011(c后面8位，要补全)
    private String id;
//  链接
    private String link;
//  标题
    private String title;
//  内容简介
    private String content;
//  大标题
    private String outType;
//  小标题
    private String inType;
//  来源网站
    private String source;
//  浏览人数、点击量
    private String clickRate;
//  封面图地址
    private String picture;

    public Course() {
    }

    public Course(String id, String link, String title, String content, String outType, String inType, String source, String clickRate, String picture) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.content = content;
        this.outType = outType;
        this.inType = inType;
        this.source = source;
        this.clickRate = clickRate;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getInType() {
        return inType;
    }

    public void setInType(String inType) {
        this.inType = inType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClickRate() {
        return clickRate;
    }

    public void setClickRate(String clickRate) {
        this.clickRate = clickRate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + id + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", outType='" + outType + '\'' +
                ", inType='" + inType + '\'' +
                ", source='" + source + '\'' +
                ", clickRate='" + clickRate + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
