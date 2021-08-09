package com.easydto.domain;

public interface Property {

    boolean canRead();

    boolean canWrite();

    Class<?> getType();
}
