package com.easydto.domain;

import com.easydto.exception.PropertyReadException;

public interface ReadProperty extends Property {

    Object read(Object targetObject) throws PropertyReadException;

    @Override
    default boolean canRead() {
        return true;
    }

}
