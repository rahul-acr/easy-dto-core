package com.easydto;

import java.lang.reflect.Field;

public class DtoConverter {

    public <T> Dto<T> convert(T obj) throws IllegalAccessException {
        var proxyMaker = new ProxyMaker();
        @SuppressWarnings("unchecked")
        Dto<T> proxy = (Dto<T>) proxyMaker.createProxy(obj.getClass());

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DtoField.class)) {
                field.setAccessible(true);
                proxy.setField(field.getName(), field.get(obj));
            }
        }

        return proxy;
    }

}
