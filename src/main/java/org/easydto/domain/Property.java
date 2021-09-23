package org.easydto.domain;

import org.easydto.enums.PropertyDeclarationType;
import org.easydto.enums.PropertyType;

public interface Property {

    boolean canRead();

    boolean canWrite();

    Class<?> getType();

    PropertyDeclarationType declaredType();

    default PropertyType propertyType() {
        return TypeManager.instance.resolveType(this);
    }

}
