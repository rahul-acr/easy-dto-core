package com.easydto.domain;

import com.easydto.enums.PropertyType;

import java.util.HashSet;
import java.util.Set;

public final class TypeManager {

    public final static TypeManager instance = new TypeManager();

    private final Set<Class<?>> wrapperTypes;

    private TypeManager() {
        wrapperTypes = new HashSet<>(16);
        wrapperTypes.add(Integer.class);
        wrapperTypes.add(Byte.class);
        wrapperTypes.add(Character.class);
        wrapperTypes.add(Boolean.class);
        wrapperTypes.add(Double.class);
        wrapperTypes.add(Float.class);
        wrapperTypes.add(Long.class);
        wrapperTypes.add(Short.class);
        wrapperTypes.add(Void.class);
    }

    PropertyType resolveType(Property property){
        if (isSimpleProperty(property))
            return PropertyType.SIMPLE;

        if(isBoxedType(property))
            return PropertyType.BOXED;

        return PropertyType.COMPLEX;
    }

    boolean isSimpleProperty(Property property){
        return property.getType().isPrimitive() || property.getType() == String.class;
    }

    boolean isBoxedType(Property property){
        return wrapperTypes.contains(property.getType());
    }

}
