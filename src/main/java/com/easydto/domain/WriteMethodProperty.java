package com.easydto.domain;

import com.easydto.exception.PropertyWriteException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WriteMethodProperty extends MethodProperty implements WriteProperty {

    public WriteMethodProperty(Method method) {
        super(method);
//        assert method.getReturnType() == Void.class;
        assert method.getParameterCount() == 1;
    }

    @Override
    public void write(Object targetObject, Object value) throws PropertyWriteException {
        try {
            method.invoke(targetObject, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PropertyWriteException("Failed to invoke method : " + method + " with argument : " + value, e);
        }
    }

    @Override
    public boolean canRead() {
        return false;
    }

    @Override
    public Class<?> getType() {
        return method.getParameterTypes()[0];
    }
}
