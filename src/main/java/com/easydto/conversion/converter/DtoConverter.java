package com.easydto.conversion.converter;

import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;

public interface DtoConverter {

    <T> Dto<T> convert(T obj) throws DtoConversionException;

    <T> Dto<T> convert(T obj, String profile) throws DtoConversionException;

}
