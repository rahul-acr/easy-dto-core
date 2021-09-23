package org.easydto.conversion.converter.impl;

import org.easydto.caching.Caching;
import org.easydto.conversion.converter.DtoDeConverter;
import org.easydto.domain.PropertyConfiguration;
import org.easydto.domain.WriteProperty;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;

public abstract class StdDeConverter implements DtoDeConverter {

    @Override
    public <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException {
        dto.setProfile(profile);
        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations().forEach(e -> {
            if (isCandidateProperty(e, profile)) {
                WriteProperty property = (WriteProperty) e.property;
                Object dtoValue = dto.getProperty(e.targetName);

                if (dtoValue == null)
                    return;

                switch (property.propertyType()) {
                    case SIMPLE:
                        convertPrimitive(property, profile, obj, dtoValue);
                        break;
                    case BOXED:
                        convertBoxing(property, profile, obj, dtoValue);
                        break;
                    case COMPLEX:
                        convertNested(property, profile, obj, dtoValue);
                        break;
                    default:
                        throw new UnsupportedOperationException(property.propertyType() + " is not supported");
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

}
