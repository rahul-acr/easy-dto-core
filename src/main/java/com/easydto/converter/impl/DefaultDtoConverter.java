package com.easydto.converter.impl;

import com.easydto.converter.DtoConverter;
import com.easydto.converter.FieldConfiguration;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.List;

public class DefaultDtoConverter extends ReflectionBasedConverter implements DtoConverter {

    public <T> Dto<T> convert(T obj) throws DtoConversionException {
        var proxyMaker = new ProxyMaker();
        @SuppressWarnings("unchecked")
        Dto<T> proxy = (Dto<T>) proxyMaker.createProxy(obj.getClass());

        List<FieldConfiguration> fields = getDtoFields(obj.getClass());
        fields.forEach(e -> {
            e.field.setAccessible(true);
            proxy.putProperty(e.targetFieldName, getField(e.field, obj));
        });

        return proxy;
    }

}
