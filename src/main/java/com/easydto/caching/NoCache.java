package com.easydto.caching;

import com.easydto.domain.DtoConfiguration;

import java.util.function.Function;

public class NoCache implements ConfigurationCache {

    @Override
    public DtoConfiguration loadConfiguration(Class<?> clazz, Function<Class<?>, DtoConfiguration> computeFunction) {
        return computeFunction.apply(clazz);
    }

    @Override
    public int size() {
        return 0;
    }

}
