package com.knoldus.models;


public class User {
    private String name;
    private int age;
    private String course;

    public User(){}

    public User(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public String getCourse(){
        return this.course;
    }
}