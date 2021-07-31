package com.easydto;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyMaker {

    @SuppressWarnings("unchecked")
    public <T> Dto<T> createProxy(Class<T> targetClass) {
        return (Dto<T>) Proxy.newProxyInstance(ProxyMaker.class.getClassLoader(),
                new Class[]{Dto.class},
                new InvocationHandler() {

                    private final ProxyObject backingObject = new ProxyObject(targetClass);

                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) {
                        System.out.println("m:" + method + " -> " + Arrays.toString(objects));
                        switch (method.getName()) {
                            case "getField":
                                return backingObject.getValue((String) objects[0]);
                            case "setField":
                                backingObject.setValue((String) objects[0], objects[1]);
                                return null;
                            case "toString":
                                return backingObject.toString();
                            case "getValues":
                                return backingObject.getValueMap();
                            case "getTargetClass":
                                return backingObject.getTargetClass();
                        }
                        throw new UnsupportedOperationException("Unsupported method : " + method.getName());
                    }
                });
    }

}
