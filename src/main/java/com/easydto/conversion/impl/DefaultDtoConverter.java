package com.easydto.conversion.impl;

import com.easydto.conversion.DtoConverter;
import com.easydto.conversion.FieldConfiguration;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.List;

public class DefaultDtoConverter extends ReflectionBasedConverter implements DtoConverter {

    public <T> Dto<T> convert(T obj) throws DtoConversionException {
        return convert(obj, null);
    }

    @Override
    public <T> Dto<T> convert(T obj, String profile) throws DtoConversionException {
        ProxyMaker proxyMaker = new ProxyMaker();
        @SuppressWarnings("unchecked")
        Dto<T> proxy = (Dto<T>) proxyMaker.createProxy(obj.getClass());

        List<FieldConfiguration> fields = getDtoFields(obj.getClass());
        fields.forEach(e -> {
            if (e.isAllowedInProfile(profile)) {
                e.field.setAccessible(true);
                proxy.putProperty(e.targetFieldName, getField(e.field, obj));
            }
        });

        return proxy;
    }

}
