package com.easydto.conversion.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.DtoDeConverter;
import com.easydto.domain.WriteProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.Map;

public class DefaultDtoDeConverter implements DtoDeConverter {

    @Override
    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        return convert(dto, obj, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException {
        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations()
                .forEach(e -> {
                    if (!e.isWritable() || !e.isAllowedInProfile(profile))
                        return;

                    Object val = dto.getProperty(e.targetName);
                    if (e.property.getType().isPrimitive() || e.property.getType() == String.class) {
                        ((WriteProperty) e.property).write(obj, val);
                    } else {
                        Object child = ReflectionUtils.createNoArgInstance(e.property.getType());
                        ProxyMaker proxyMaker = new ProxyMaker();
                        Dto<?> childDto = proxyMaker.createProxy(e.property.getType());
                        ((Map<String, Object>) val).forEach(childDto::putProperty);
                        Object vv = convert((Dto<Object>) childDto, child);
                        ((WriteProperty) e.property).write(obj, vv);
                    }
                });

        return obj;
    }

}
