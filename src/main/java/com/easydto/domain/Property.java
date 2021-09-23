package com.easydto.domain;

import com.easydto.enums.PropertyDeclarationType;
import com.easydto.enums.PropertyType;

public interface Property {

    boolean canRead();

    boolean canWrite();

    Class<?> getType();

    PropertyDeclarationType declaredType();

    default PropertyType propertyType() {
        return TypeManager.instance.resolveType(this);
    }

}
