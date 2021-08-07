package com.easydto.proxy;


import java.util.Map;


public interface Dto<T> {

    Class<T> getTargetClass();

    void putProperty(String fieldName, Object obj);

    Object getProperty(String fieldName);

    Map<String, Object> getValues();

}
