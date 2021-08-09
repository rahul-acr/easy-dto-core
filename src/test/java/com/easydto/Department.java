package com.easydto;

import com.easydto.annotation.DtoProperty;

public class Department {

    @DtoProperty
    public long id;

    @DtoProperty
    public String name;

    public Department() {

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
