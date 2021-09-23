package com.easydto.proxy;

import java.util.HashMap;
import java.util.Map;

public final class ValueMapDto<T> implements Dto<T>{

    private final Map<String, Object> valueMap = new HashMap<>();
    private final Class<T> targetClass;
    private String profile;

    public ValueMapDto(Class<T> targetClass, String profile){
        this.targetClass = targetClass;
        this.profile = profile;
    }

    @Override
    public Class<T> getTargetClass() {
        return targetClass;
    }

    @Override
    public String profile() {
        return profile;
    }

    @Override
    public void setProfile(String profile) {
        this.profile = profile;
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
        return "ValueMapDto{" +
                "valueMap=" + valueMap +
                ", targetClass=" + targetClass +
                ", profile='" + profile + '\'' +
                '}';
    }
}
