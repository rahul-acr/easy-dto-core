package com.easydto;

import com.easydto.annotation.DtoField;

import static com.easydto.DtoProfileConstants.BATCH;
import static com.easydto.DtoProfileConstants.REST;

public class Student {

    @DtoField(profile = {BATCH})
    public String name;

    @DtoField(value = "dept", profile = {REST})
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
