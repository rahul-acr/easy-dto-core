package com.easydto.domain;

import java.lang.reflect.Method;

public abstract class MethodProperty {

    protected final Method method;

    public MethodProperty(Method method){
        this.method = method;
    }

}
