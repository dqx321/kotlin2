package com.example.administrator.myapplication.bean;

import java.io.Serializable;

public class MainBean implements Serializable{
    private String  a;

    public MainBean() {
    }

    public MainBean(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
