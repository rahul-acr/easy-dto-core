package org.easydto.conversion.converter.impl;

import org.easydto.caching.Caching;
import org.easydto.conversion.converter.DtoConverter;
import org.easydto.domain.Property;
import org.easydto.domain.ReadProperty;
import org.easydto.enums.PropertyType;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;
import org.easydto.proxy.DtoFactory;


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
