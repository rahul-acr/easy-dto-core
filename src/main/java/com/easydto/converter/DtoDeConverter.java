package com.easydto.converter;

import com.easydto.Dto;
import com.easydto.exception.DtoConversionException;

public interface DtoDeConverter {
    <T> T convert(Dto<T> dto, T obj) throws DtoConversionException;
}
