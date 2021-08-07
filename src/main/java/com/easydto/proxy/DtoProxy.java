package com.easydto.proxy;

import java.util.HashMap;
import java.util.Map;

public class DtoProxy<T> implements Dto<T>{

    private final Map<String, Object> valueMap = new HashMap<>();
    private final Class<T> targetClass;

    public DtoProxy(Class<T> targetClass){
        this.targetClass = targetClass;
    }

    @Override
    public Class<T> getTargetClass() {
        return targetClass;
    }

    @Override
    public void putProperty(String fieldName, Object obj) {
        valueMap.put(fieldName, obj);
    }

    @Override
    public Object getProperty(String fieldName) {
        return valueMap.get(fieldName);
    }

    @Override
    public Map<String, Object> getValues() {
        return valueMap;
    }

    @Override
    public String toString() {
        return "ProxyObject{" +
                "valueMap=" + valueMap +
                '}';
    }
}
