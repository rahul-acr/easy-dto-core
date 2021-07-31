package com.easydto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ProxyObject {

    private final Map<String, Object> valueMap = new HashMap<>();
    private final Class<?> targetClass;

    public ProxyObject(Class<?> targetClass){
        this.targetClass = targetClass;
    }

    public Object getValue(String fieldName) {
        return valueMap.get(fieldName);
    }

    public void setValue(String fieldName, Object value) {
        valueMap.put(fieldName, value);
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    @Override
    public String toString() {
        return "ProxyObject{" +
                "valueMap=" + valueMap +
                '}';
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
}
