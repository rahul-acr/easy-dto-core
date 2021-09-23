package org.easydto.domain;

import org.easydto.enums.PropertyDeclarationType;

import java.lang.reflect.Method;

public abstract class MethodProperty implements Property {

    protected final Method method;

    public MethodProperty(Method method) {
        this.method = method;
    }

    @Override
    public PropertyDeclarationType declaredType() {
        return PropertyDeclarationType.METHOD;
    }
}
