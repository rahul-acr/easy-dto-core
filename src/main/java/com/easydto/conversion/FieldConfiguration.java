package com.easydto.conversion;

import com.easydto.annotation.DtoField;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldConfiguration {

    public final Field field;
    public final String targetFieldName;
    public final String[] profiles;

    private FieldConfiguration(Field field) {
        DtoField annotation = field.getAnnotation(DtoField.class);
        this.field = field;
        this.targetFieldName = annotation.value().equals(DtoField.DEFAULT) ? field.getName() : annotation.value();
        this.profiles = annotation.profile();
    }

    public static Optional<FieldConfiguration> load(Field field) {
        if (field.isAnnotationPresent(DtoField.class)) {
            return Optional.of(new FieldConfiguration(field));
        }
        return Optional.empty();
    }

    public boolean isAllowedInProfile(String profile) {
        if (profile == null || profiles.length == 0) return true;
        for (String s : profiles) {
            if (s.equals(profile))
                return true;
        }
        return false;
    }

}
