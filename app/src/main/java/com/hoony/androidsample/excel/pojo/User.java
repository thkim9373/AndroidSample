package com.hoony.androidsample.excel.pojo;

public class User {
    private String name = "";
    private String inputTime;

    public User() {
    }

    public User(String name, String inputTime) {
        this.name = name;
        this.inputTime = inputTime;
    }

    public String getName() {
        return name;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }
}
