package com.easydto.converter.impl;

import com.easydto.Dto;
import com.easydto.annotation.DtoField;
import com.easydto.converter.DtoConverter;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.ProxyMaker;

import java.lang.reflect.Field;

public class DefaultDtoConverter implements DtoConverter {

    public <T> Dto<T> convert(T obj) throws DtoConversionException {
        var proxyMaker = new ProxyMaker();
        @SuppressWarnings("unchecked")
        Dto<T> proxy = (Dto<T>) proxyMaker.createProxy(obj.getClass());

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DtoField.class)) {
                field.setAccessible(true);
                try {
                    proxy.setField(field.getName(), field.get(obj));
                } catch (IllegalAccessException e) {
                    throw new DtoConversionException(e);
                }
            }
        }

        return proxy;
    }

}
