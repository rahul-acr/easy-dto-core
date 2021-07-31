package com.easydto;

import java.lang.reflect.Field;

public class DtoDeConverter {

    public <T> T convert(Dto<T> dto, T obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DtoField.class)) {
                field.setAccessible(true);
                Object val = dto.getField(field.getName());
                field.set(obj, val);
            }
        }

        return obj;
    }

}
