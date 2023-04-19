package com.example.listvewdemo2;

public class Student {
    public Student(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    private String name;
    private String dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
