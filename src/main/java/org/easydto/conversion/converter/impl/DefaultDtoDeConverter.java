package org.easydto.conversion.converter.impl;

import org.easydto.domain.WriteProperty;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;
import org.easydto.proxy.DtoFactory;

import java.util.Map;

public class DefaultDtoDeConverter extends StdDeConverter {

    @Override
    void convertPrimitive(WriteProperty property, String profile, Object target, Object value) {
        property.write(target, value);
    }

    @Override
    void convertBoxing(WriteProperty property, String profile, Object target, Object value) {
        if (property.getType() == Long.class) {
            property.write(target, ((Number) value).longValue());
        } else if (property.getType() == Integer.class) {
            property.write(target, ((Number) value).intValue());
        } else if (property.getType() == Double.class) {
            property.write(target, ((Number) value).doubleValue());
        } else if (property.getType() == Float.class) {
            property.write(target, ((Number) value).floatValue());
        } else {
            throw new UnsupportedOperationException("Boxing not fully supported yet");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    void convertNested(WriteProperty property, String profile, Object target, Object value) {
        Object child = ReflectionUtils.createNoArgInstance(property.getType());

        Dto<?> childDto;
        if (value instanceof Map) {
            childDto = DtoFactory.INSTANCE.createDtoFor(property.getType(), profile);
            ((Map<String, Object>) value).forEach(childDto::putProperty);
        } else if (value instanceof Dto) {
            childDto = (Dto<?>) value;
        } else {
            throw new DtoConversionException("Unsupported nested type : " + value.getClass());
        }

        Object vv = convert((Dto<Object>) childDto, child, profile);
        property.write(target, vv);
    }
}
