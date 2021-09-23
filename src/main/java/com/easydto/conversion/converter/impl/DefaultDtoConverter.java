package com.easydto.conversion.converter.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.converter.DtoConverter;
import com.easydto.domain.Property;
import com.easydto.domain.ReadProperty;
import com.easydto.enums.PropertyType;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.DtoFactory;


public class DefaultDtoConverter implements DtoConverter {

    @Override
    @SuppressWarnings("unchecked")
    public <T> Dto<T> convert(T obj, String profile) throws DtoConversionException {
        if (obj == null)
            return null;

        DtoFactory dtoFactory = DtoFactory.INSTANCE;
        Dto<T> dto = (Dto<T>) dtoFactory.createDtoFor(obj.getClass(), profile);

        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations().forEach(e -> {
            Property property = e.property;

            if (property.canRead() && e.isAllowedInProfile(profile)) {
                Object readValue = ((ReadProperty) property).read(obj);

                if (property.propertyType() == PropertyType.COMPLEX) {
                    dto.putProperty(e.targetName, convert(readValue, profile));
                } else {
                    dto.putProperty(e.targetName, readValue);
                }
            }
        });

        return dto;
    }

}
