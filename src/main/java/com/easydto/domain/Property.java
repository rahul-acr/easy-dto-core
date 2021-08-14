package com.easydto.domain;

import com.easydto.enums.PropertyType;

public interface Property {

    boolean canRead();

    boolean canWrite();

    Class<?> getType();

    PropertyType propertyType();

}
