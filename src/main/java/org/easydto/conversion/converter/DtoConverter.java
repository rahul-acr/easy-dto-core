package org.easydto.conversion.converter;

import org.easydto.exception.DtoConversionException;
import org.easydto.proxy.Dto;

public interface DtoConverter {

    <T> Dto<T> convert(T obj, String profile) throws DtoConversionException;

}
