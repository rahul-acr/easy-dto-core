package com.easydto.domain;

import com.easydto.annotation.DtoProperty;

import static com.easydto.domain.DtoProfileConstants.BATCH;
import static com.easydto.domain.DtoProfileConstants.REST;

public class Student {

    @DtoProperty(profile = {BATCH})
    public String name;

    @DtoProperty(value = "dept", profile = {REST})
    public Department department;

    public Student() {
        // No arg constructor
    }

    public Student(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @DtoProperty("isEnrolled")
    public boolean hasDepartment(){
        return department != null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
