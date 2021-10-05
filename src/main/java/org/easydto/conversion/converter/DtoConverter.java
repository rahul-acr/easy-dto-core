package org.easydto.conversion.converter;

import org.easydto.domain.ConversionContext;
import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;

public interface DtoConverter {

    <T> Dto<T> convert(ConversionContext<T> cc) throws DtoConversionException;

}
