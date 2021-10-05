package org.easydto.domain;

import org.easydto.enums.ConversionType;
import org.easydto.proxy.Dto;

public interface ConversionContext<T> {

    <C> ConversionContext<C> createChildContext(C childObject);

    <C> ConversionContext<C> createChildContext(Dto<C> cDto, C childObject);

    ConversionType getConversionType();

    String getProfile();

    ConversionContext<?> getParentContext();

    Dto<T> getDto();

    T getDomainObject();

    PropertyConfiguration getCurrentPropertyConfiguration();

    boolean nextProperty();

}
