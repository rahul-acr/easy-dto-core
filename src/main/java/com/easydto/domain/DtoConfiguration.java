package com.easydto.domain;

import com.easydto.conversion.impl.ReflectionUtils;

import java.util.Collections;
import java.util.List;

public class DtoConfiguration {

    private final List<PropertyConfiguration> propertyConfigurations;

    public DtoConfiguration(Class<?> clazz) {
        this.propertyConfigurations = Collections.unmodifiableList(ReflectionUtils.getDtoProperties(clazz));
    }

    public List<PropertyConfiguration> getPropertyConfigurations() {
        return propertyConfigurations;
    }
}
