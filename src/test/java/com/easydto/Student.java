package com.easydto;

public class Student {

    @DtoField
    public String name;

    @DtoField
    public String department;

    public Student() {
        // No arg constructor
    }

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
