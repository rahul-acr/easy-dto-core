package com.easydto;

import com.easydto.annotation.DtoField;

public class Department {

    @DtoField
    public long id;

    @DtoField
    public String name;

    public Department(){

    }

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
