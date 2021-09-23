package org.easydto.caching;

import org.easydto.domain.DtoConfiguration;

import java.util.function.Function;

public interface ConfigurationCache {

    DtoConfiguration loadConfiguration(Class<?> clazz, Function<Class<?>, DtoConfiguration> computeFunction);

    int size();

}
