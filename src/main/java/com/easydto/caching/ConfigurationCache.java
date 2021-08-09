package com.easydto.caching;

import com.easydto.domain.DtoConfiguration;

import java.util.function.Function;

public interface ConfigurationCache {

    DtoConfiguration loadConfiguration(Class<?> clazz, Function<Class<?>, DtoConfiguration> computeFunction);

    int size();

}
