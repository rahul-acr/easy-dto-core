package com.easydto.conversion.impl;

import com.easydto.conversion.DtoDeConverter;
import com.easydto.conversion.PropertyConfiguration;
import com.easydto.domain.WriteProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.List;
import java.util.Map;

public class DefaultDtoDeConverter implements DtoDeConverter {

    @Override
    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        return convert(dto, obj, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException {
        List<PropertyConfiguration> propertyConfigurations = ReflectionUtils.getDtoProperties(obj.getClass());
        propertyConfigurations.forEach(e -> {
            if (!e.isAllowedInProfile(profile) || !e.isWritable())
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
