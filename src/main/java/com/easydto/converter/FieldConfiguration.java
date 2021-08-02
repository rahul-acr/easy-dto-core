package com.easydto.converter;

import com.easydto.annotation.DtoField;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldConfiguration {

    public final Field field;
    public final String targetFieldName;

    private FieldConfiguration(Field field){
        DtoField annotation = field.getAnnotation(DtoField.class);
        this.field = field;
        this.targetFieldName = annotation.value().equals(DtoField.DEFAULT) ? field.getName() : annotation.value();
    }

    public static Optional<FieldConfiguration> load(Field field){
        if(field.isAnnotationPresent(DtoField.class)){
            return Optional.of(new FieldConfiguration(field));
        }
        return Optional.empty();
    }

}
