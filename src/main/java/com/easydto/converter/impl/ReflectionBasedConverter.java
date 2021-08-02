package com.easydto.converter.impl;

import com.easydto.converter.FieldConfiguration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionBasedConverter {

    protected final List<FieldConfiguration> getDtoFields(Class<?> targetClass){
        Field[] fields = targetClass.getDeclaredFields();
        List<FieldConfiguration> fieldConfigurations = new ArrayList<>();
        for (Field field : fields) {
            FieldConfiguration.load(field).ifPresent(fieldConfigurations::add);
        }
        return fieldConfigurations;
    }

}
