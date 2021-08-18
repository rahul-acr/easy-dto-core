package com.easydto.conversion.converter.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.converter.DtoDeConverter;
import com.easydto.domain.PropertyConfiguration;
import com.easydto.domain.WriteProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;

import java.util.HashSet;
import java.util.Set;

public abstract class StdDeConverter implements DtoDeConverter {

    protected static final Set<Class<?>> WRAPPER_TYPES;

    static {
        WRAPPER_TYPES = new HashSet<>(16);
        WRAPPER_TYPES.add(Integer.class);
        WRAPPER_TYPES.add(Byte.class);
        WRAPPER_TYPES.add(Character.class);
        WRAPPER_TYPES.add(Boolean.class);
        WRAPPER_TYPES.add(Double.class);
        WRAPPER_TYPES.add(Float.class);
        WRAPPER_TYPES.add(Long.class);
        WRAPPER_TYPES.add(Short.class);
        WRAPPER_TYPES.add(Void.class);
    }

    @Override
    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        return convert(dto, obj, null);
    }

    @Override
    public <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException {
        dto.setProfile(profile);
        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations().forEach(e -> {
            if (isCandidateProperty(e, profile)) {
                WriteProperty property = (WriteProperty) e.property;
                Object dtoValue = dto.getProperty(e.targetName);

                if(dtoValue == null)
                    return;

                if (isSimpleProperty(property)) {
                    convertPrimitive(property, profile, obj, dtoValue);
                } else if (isBoxedProperty(property)) {
                    convertBoxing(property, profile, obj, dtoValue);
                } else {
                    convertNested(property, profile, obj, dtoValue);
                }
            }
        });
        return obj;
    }

    private boolean isCandidateProperty(PropertyConfiguration configuration, String profile) {
        return configuration.property.canWrite() && configuration.isAllowedInProfile(profile);
    }

    abstract void convertPrimitive(WriteProperty property, String profile, Object target, Object value);

    abstract void convertBoxing(WriteProperty property, String profile, Object target, Object value);

    abstract void convertNested(WriteProperty property, String profile, Object target, Object value);

    protected boolean isSimpleProperty(WriteProperty property) {
        return property.getType().isPrimitive()
                || property.getType() == String.class;
    }

    protected boolean isBoxedProperty(WriteProperty property) {
        return WRAPPER_TYPES.contains(property.getType());
    }
}
