package org.easydto.domain;

import org.easydto.exception.PropertyWriteException;

public interface WriteProperty extends Property {

    void write(Object targetObject, Object value) throws PropertyWriteException;

    @Override
    default boolean canWrite() {
        return true;
    }

}
