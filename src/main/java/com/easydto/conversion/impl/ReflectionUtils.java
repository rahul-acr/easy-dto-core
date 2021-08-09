package com.easydto.conversion.impl;

import com.easydto.conversion.PropertyConfiguration;
import com.easydto.exception.DtoException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ReflectionUtils {

    public static List<PropertyConfiguration> getDtoProperties(Class<?> targetClass) {
        List<PropertyConfiguration> propertyConfigurations = new ArrayList<>();

        for (Field field : targetClass.getDeclaredFields())
            PropertyConfiguration.load(field).ifPresent(propertyConfigurations::add);

        for (Method method : targetClass.getMethods())
            PropertyConfiguration.load(method).ifPresent(propertyConfigurations::add);

        return propertyConfigurations;
    }

    public static <T> T createNoArgInstance(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new DtoException("Failed to invoke no-arg construtor for " + clazz, e);
        } catch (NoSuchMethodException e) {
            throw new DtoException("Please declare a no-arg constructor for" + clazz, e);
        }
    }

}
