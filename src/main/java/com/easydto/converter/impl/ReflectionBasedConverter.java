package com.easydto.converter.impl;

import com.easydto.converter.FieldConfiguration;
import com.easydto.exception.DtoConversionException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionBasedConverter {

    protected final List<FieldConfiguration> getDtoFields(Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        List<FieldConfiguration> fieldConfigurations = new ArrayList<>();
        for (Field field : fields) {
            FieldConfiguration.load(field).ifPresent(fieldConfigurations::add);
        }
        return fieldConfigurations;
    }

    protected static void setField(Field field, Object obj, Object value) {
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new DtoConversionException(e);
        }
    }

    protected static <T> T createNoArgInstance(Class<T> clazz){
        try {
           return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new DtoConversionException(e);
        }
    }

}
