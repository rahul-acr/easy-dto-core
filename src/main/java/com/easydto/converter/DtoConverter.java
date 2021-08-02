package com.easydto.converter;

import com.easydto.proxy.Dto;
import com.easydto.exception.DtoConversionException;

public interface DtoConverter {

    <T> Dto<T> convert(T obj) throws DtoConversionException;

}
