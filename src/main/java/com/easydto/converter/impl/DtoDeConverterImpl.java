package com.easydto.converter.impl;

import com.easydto.converter.DtoDeConverter;
import com.easydto.converter.FieldConfiguration;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.List;
import java.util.Map;

public class DtoDeConverterImpl extends ReflectionBasedConverter implements DtoDeConverter {

    @SuppressWarnings("unchecked")
    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        List<FieldConfiguration> fieldConfigurations = getDtoFields(obj.getClass());
        fieldConfigurations.forEach(e -> {
            e.field.setAccessible(true);
            Object val = dto.getField(e.targetFieldName);
            if (e.field.getType().isPrimitive() || e.field.getType() == String.class) {
                setField(e.field, obj, val);
            } else {
                var child = createNoArgInstance(e.field.getType());
                ProxyMaker proxyMaker = new ProxyMaker();
                Dto<?> childDto = proxyMaker.createProxy(e.field.getType());
                ((Map<String, Object>) val).forEach(childDto::setField);
                Object vv = convert((Dto<Object>) childDto, child);
                setField(e.field, obj, vv);
            }
        });

        return obj;
    }

}
