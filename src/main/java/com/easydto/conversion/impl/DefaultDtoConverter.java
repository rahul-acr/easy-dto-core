package com.easydto.conversion.impl;

import com.easydto.caching.Caching;
import com.easydto.conversion.DtoConverter;
import com.easydto.domain.ReadProperty;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ProxyMaker;

public class DefaultDtoConverter implements DtoConverter {

    public <T> Dto<T> convert(T obj) throws DtoConversionException {
        return convert(obj, null);
    }

    @Override
    public <T> Dto<T> convert(T obj, String profile) throws DtoConversionException {
        ProxyMaker proxyMaker = new ProxyMaker();
        @SuppressWarnings("unchecked")
        Dto<T> proxy = (Dto<T>) proxyMaker.createProxy(obj.getClass());

        Caching.getConfiguration(obj.getClass()).getPropertyConfigurations().forEach(e -> {
            if (e.isReadable() && e.isAllowedInProfile(profile)) {
                proxy.putProperty(e.targetName, ((ReadProperty) e.property).read(obj));
            }
        });

        return proxy;
    }

}
