package org.easydto.domain;

import org.easydto.exception.PropertyReadException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReadMethodProperty extends MethodProperty implements ReadProperty {

    public ReadMethodProperty(Method method) {
        super(method);
        assert method.getParameterCount() == 0;
    }

    @Override
    public Object read(Object targetObject) throws PropertyReadException {
        try {
            return method.invoke(targetObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PropertyReadException("Could not invoke method" + method + ". Ensure it takes no arguments", e);
        }
    }

    @Override
    public boolean canWrite() {
        return false;
    }

    @Override
    public Class<?> getType() {
        return method.getReturnType();
    }
}
