package com.easydto.conversion.converter.impl;

import com.easydto.domain.WriteProperty;
import com.easydto.proxy.Dto;
import com.easydto.proxy.DtoFactory;

import java.util.Map;

public class DefaultDtoDeConverter extends StdDeConverter {

    @Override
    void convertSimple(WriteProperty property, String profile, Object target, Object value) {
        property.write(target, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    void convertNested(WriteProperty property, String profile, Object target, Object value) {
        Object child = ReflectionUtils.createNoArgInstance(property.getType());
        DtoFactory dtoFactory = DtoFactory.INSTANCE;
        Dto<?> childDto = dtoFactory.createDtoFor(property.getType(), profile);
        ((Map<String, Object>) value).forEach(childDto::putProperty);
        Object vv = convert((Dto<Object>) childDto, child, profile);
        property.write(target, vv);
    }
}
