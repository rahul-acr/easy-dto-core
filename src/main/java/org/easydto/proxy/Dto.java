package org.easydto.proxy;


import org.easydto.conversion.converter.DtoConverter;
import org.easydto.conversion.converter.DtoDeConverter;
import org.easydto.conversion.converter.impl.DefaultDtoConverter;
import org.easydto.conversion.converter.impl.DefaultDtoDeConverter;
import org.easydto.domain.ConversionContext;
import org.easydto.domain.PropertyConfiguration;
import org.easydto.domain.StdConversionContext;

import java.util.Map;


public interface Dto<T> {

    DtoDeConverter DEFAULT_DTO_DE_CONVERTER = new DefaultDtoDeConverter();
    DtoConverter DEFAULT_DTO_CONVERTER = new DefaultDtoConverter();

    Class<T> getTargetClass();

    String profile();

    void setProfile(String profile);

    void putProperty(String fieldName, Object obj);

    Object getProperty(String fieldName);

    default Object getProperty(PropertyConfiguration propertyConfiguration) {
        return getProperty(propertyConfiguration.targetName);
    }

    Map<String, Object> getValues();

    default void map(T target) {
        map(target, null);
    }

    default void map(T target, String profile) {
        map(target, DEFAULT_DTO_DE_CONVERTER, profile);
    }

    default void map(T target, DtoDeConverter converter, String profile) {
        ConversionContext<T> cc = StdConversionContext.forDeConversion(this, target, profile);
        converter.convert(cc);
    }

    static <X> Dto<X> from(X target) {
        return from(target, null);
    }

    static <X> Dto<X> from(X target, String profile) {
        return from(target, profile, DEFAULT_DTO_CONVERTER);
    }

    static <X> Dto<X> from(X target, String profile, DtoConverter converter) {
        ConversionContext<X> cc = StdConversionContext.forConversion(target, profile);
        return converter.convert(cc);
    }

}
