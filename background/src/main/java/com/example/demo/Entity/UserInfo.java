package com.example.demo.Entity;
//author:孙宝臻
//date:2020-10-26
//function:用户详细信息类
public class UserInfo {
//    用户id
    private String id;
//    用户年龄
    private String age;
//    生日
    private String birth;
//    性别
    private String sex;
//  介绍
    private String introduction;
//  教育经历
    private String education;
//  职业经历
    private String experience;

    public UserInfo() {
    }

    public UserInfo(String id, String age, String birth, String sex, String introduction, String education, String experience) {
        this.id = id;
        this.age = age;
        this.birth = birth;
        this.sex = sex;
        this.introduction = introduction;
        this.education = education;
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", age='" + age + '\'' +
                ", birth='" + birth + '\'' +
                ", sex='" + sex + '\'' +
                ", introduction='" + introduction + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
