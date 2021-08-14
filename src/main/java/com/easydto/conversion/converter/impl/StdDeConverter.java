package com.easydto.conversion.converter.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.converter.DtoDeConverter;
import com.easydto.domain.PropertyConfiguration;
import com.easydto.domain.WriteProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;

public abstract class StdDeConverter implements DtoDeConverter {

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
                if (isSimpleProperty(property)) {
                    convertSimple(property, profile, obj, dtoValue);
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

    abstract void convertSimple(WriteProperty property, String profile, Object target, Object value);

    abstract void convertNested(WriteProperty property, String profile, Object target, Object value);

    protected boolean isSimpleProperty(WriteProperty property) {
        return property.getType().isPrimitive() || property.getType() == String.class;
    }
}
