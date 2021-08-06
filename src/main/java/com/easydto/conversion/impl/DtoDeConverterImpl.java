package com.easydto.conversion.impl;

import com.easydto.conversion.DtoDeConverter;
import com.easydto.conversion.FieldConfiguration;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

import java.util.List;
import java.util.Map;

public class DtoDeConverterImpl extends ReflectionBasedConverter implements DtoDeConverter {

    @Override
    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        return convert(dto, obj, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException {
        List<FieldConfiguration> fieldConfigurations = getDtoFields(obj.getClass());
        fieldConfigurations.forEach(e -> {
            if (!e.isAllowedInProfile(profile))
                return;

            e.field.setAccessible(true);
            Object val = dto.getProperty(e.targetFieldName);
            if (e.field.getType().isPrimitive() || e.field.getType() == String.class) {
                setField(e.field, obj, val);
            } else {
                Object child = createNoArgInstance(e.field.getType());
                ProxyMaker proxyMaker = new ProxyMaker();
                Dto<?> childDto = proxyMaker.createProxy(e.field.getType());
                ((Map<String, Object>) val).forEach(childDto::putProperty);
                Object vv = convert((Dto<Object>) childDto, child);
                setField(e.field, obj, vv);
            }
        });

        return obj;
    }

}
