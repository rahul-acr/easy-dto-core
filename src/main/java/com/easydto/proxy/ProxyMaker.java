package com.easydto.proxy;

public class ProxyMaker {

    public <T> Dto<T> createProxy(Class<T> targetClass) {
        return new DtoProxy<>(targetClass);
    }

    //TODO as per required dto type

}
