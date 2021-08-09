package com.easydto.caching;

import com.easydto.domain.DtoConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DefaultConfigurationCache implements ConfigurationCache {

    private final Map<Class<?>, DtoConfiguration> cache = new ConcurrentHashMap<>();

    @Override
    public DtoConfiguration loadConfiguration(Class<?> clazz, Function<Class<?>, DtoConfiguration> computeFunction) {
        return cache.computeIfAbsent(clazz, computeFunction);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
