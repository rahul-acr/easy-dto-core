package com.easydto;

import com.easydto.annotation.DtoField;

public class Department {

    @DtoField
    public Long id;

    @DtoField
    public String name;

    public Department(){

    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
