package com.easydto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;


@JsonSerialize(using = DtoSerializer.class)
public interface Dto<T> {

    Class<T> getTargetClass();

    void setField(String fieldName, Object obj);

    Object getField(String fieldName);

    Map<String, Object> getValues();

}
