package com.easydto.proxy;

import com.easydto.serialization.jackson.DtoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

@JsonSerialize(using = DtoSerializer.class)
public interface Dto<T> {

    Class<T> getTargetClass();

    void putProperty(String fieldName, Object obj);

    Object getProperty(String fieldName);

    Map<String, Object> getValues();

}
