package com.easydto.conversion.converter.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.converter.DtoConverter;
import com.easydto.domain.ReadProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.DtoFactory;

@SuppressWarnings("unchecked")
public class DefaultDtoConverter implements DtoConverter {

    public <T> Dto<T> convert(T obj) throws DtoConversionException {
        return convert(obj, null);
    }

    @Override
    public <T> Dto<T> convert(T obj, String profile) throws DtoConversionException {
        DtoFactory dtoFactory = DtoFactory.INSTANCE;
        Dto<T> proxy = (Dto<T>) dtoFactory.createDtoFor(obj.getClass(), profile);

        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations().forEach(e -> {
            if (e.property.canRead() && e.isAllowedInProfile(profile)) {
                Object readValue = ((ReadProperty) e.property).read(obj);
                proxy.putProperty(e.targetName, readValue);
            }
        });

        return proxy;
    }

}
