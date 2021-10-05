package org.easydto.conversion.converter;

import org.easydto.domain.ConversionContext;
import org.easydto.exception.DtoConversionException;

public interface DtoDeConverter {

    <T> T convert(ConversionContext<T> cc) throws DtoConversionException;

}
