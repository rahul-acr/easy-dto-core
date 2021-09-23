package org.easydto.conversion.converter;

import org.easydto.proxy.Dto;
import org.easydto.exception.DtoConversionException;

public interface DtoDeConverter {

    <T> T convert(Dto<T> dto, T obj, String profile) throws DtoConversionException;

}
