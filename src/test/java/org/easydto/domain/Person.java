package org.easydto.domain;

import org.easydto.annotation.DtoProperty;

public class Person {

    @DtoProperty
    private String name;

    public String name() {
        return name;
    }

    public void name(String name){
        this.name = name;
    }

    @DtoProperty
    public Long age;

    @DtoProperty("gender")
    public String sex;

    public boolean isNri;
    public String country;

    @DtoProperty("country")
    public void setCountry(String country) {
        isNri = !"India".equalsIgnoreCase(country);
        this.country = country;
    }

    @DtoProperty(profile = {"SECURE"})
    public String address;

    @DtoProperty
    public Contact contact;

    public static class Contact {
        @DtoProperty
        public String phone;
        @DtoProperty
        public String email;
    }
}