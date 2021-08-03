package com.easydto;

import com.easydto.annotation.DtoField;

public class Student {

    @DtoField
    public String name;

    @DtoField("dept")
    public Department department;

    public Student() {
        // No arg constructor
    }

    public Student(String name, Department department) {
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
