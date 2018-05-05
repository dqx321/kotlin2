package com.example.administrator.myapplication.bean;

// Created by CIDI daiqinxue on 2018/5/3.
public class ClassBean {
    private String name;
    private String sex;
    private String nickname;

    @Override
    public String toString() {
        return "ClassBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public ClassBean() {
    }

    public ClassBean(String name, String sex, String nickname) {
        this.name = name;
        this.sex = sex;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
