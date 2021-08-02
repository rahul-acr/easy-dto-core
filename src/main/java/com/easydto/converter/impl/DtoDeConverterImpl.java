package com.easydto.converter.impl;

import com.easydto.converter.DtoDeConverter;
import com.easydto.exception.DtoConversionException;
import com.easydto.proxy.Dto;

public class DtoDeConverterImpl extends ReflectionBasedConverter implements DtoDeConverter {

    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        var fieldConfigurations = getDtoFields(obj.getClass());
        fieldConfigurations.forEach(e -> {
            e.field.setAccessible(true);
            Object val = dto.getField(e.targetFieldName);
            try {
                e.field.set(obj, val);
            } catch (IllegalAccessException ex) {
                throw new DtoConversionException(ex);
            }
        });

        return obj;
    }

}
