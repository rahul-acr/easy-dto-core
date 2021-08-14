package com.easydto.domain;

import com.easydto.enums.PropertyType;
import com.easydto.exception.PropertyReadException;
import com.easydto.exception.PropertyWriteException;

import java.lang.reflect.Field;

public class FieldProperty implements ReadProperty, WriteProperty {

    private final Field field;

    public FieldProperty(Field field) {
        this.field = field;
    }

    public FieldProperty(Field field, boolean setAccessible) {
        this.field = field;
        this.field.setAccessible(setAccessible);
    }

    @Override
    public Object read(Object targetObject) throws PropertyReadException {
        try {
            return field.get(targetObject);
        } catch (IllegalAccessException e) {
            throw new PropertyReadException("Could not get value from field", e);
        }
    }

    @Override
    public void write(Object targetObject, Object value) throws PropertyWriteException {
        try {
            field.set(targetObject, value);
        } catch (IllegalAccessException e) {
            throw new PropertyWriteException("Could not set value in field", e);
        }
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public PropertyType propertyType() {
        return PropertyType.FIELD;
    }

}
