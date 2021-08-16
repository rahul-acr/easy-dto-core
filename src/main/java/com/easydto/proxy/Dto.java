package com.easydto.proxy;


import com.easydto.conversion.converter.DtoConverter;
import com.easydto.conversion.converter.DtoDeConverter;
import com.easydto.conversion.converter.impl.DefaultDtoConverter;
import com.easydto.conversion.converter.impl.DefaultDtoDeConverter;

import java.util.Map;


public interface Dto<T> {

    DtoDeConverter DEFAULT_DTO_DE_CONVERTER = new DefaultDtoDeConverter();
    DtoConverter DEFAULT_DTO_CONVERTER = new DefaultDtoConverter();

    Class<T> getTargetClass();

    String profile();

    void setProfile(String profile);

    void putProperty(String fieldName, Object obj);

    Object getProperty(String fieldName);

    Map<String, Object> getValues();

    default void map(T target) {
        map(target, null);
    }

    default void map(T target, String profile) {
        map(target, DEFAULT_DTO_DE_CONVERTER, profile);
    }

    default void map(T target, DtoDeConverter converter, String profile) {
        converter.convert(this, target, profile);
    }

    static <X> Dto<X> from(X target) {
        return from(target, null);
    }

    static <X> Dto<X> from(X target, String profile) {
        return from(target, profile, DEFAULT_DTO_CONVERTER);
    }

    static <X> Dto<X> from(X target, String profile, DtoConverter converter) {
        return converter.convert(target, profile);
    }

}
