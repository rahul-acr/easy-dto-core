package com.easydto.conversion;

import com.easydto.proxy.Dto;
import com.easydto.exception.DtoConversionException;

public interface DtoConverter {

    <T> Dto<T> convert(T obj) throws DtoConversionException;

    <T> Dto<T> convert(T obj, String profile) throws DtoConversionException;
}
