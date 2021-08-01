package com.easydto.converter.impl;

import com.easydto.Dto;
import com.easydto.annotation.DtoField;
import com.easydto.converter.DtoDeConverter;
import com.easydto.exception.DtoConversionException;

import java.lang.reflect.Field;

public class DtoDeConverterImpl implements DtoDeConverter {

    public <T> T convert(Dto<T> dto, T obj) throws DtoConversionException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DtoField.class)) {
                field.setAccessible(true);
                Object val = dto.getField(field.getName());
                try {
                    field.set(obj, val);
                } catch (IllegalAccessException e) {
                    throw new DtoConversionException(e);
                }
            }
        }

        return obj;
    }

}
