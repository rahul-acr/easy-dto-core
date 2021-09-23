package org.easydto.proxy;

public class DtoFactory {

    private DtoFactory(){
    }

    public final static DtoFactory INSTANCE = new DtoFactory();

    public <T> Dto<T> createDtoFor(Class<T> targetClass, String profile) {
        return new ValueMapDto<>(targetClass, profile);
    }

}
