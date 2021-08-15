package com.easydto.caching;

import com.easydto.domain.DtoConfiguration;

public class Caching {

    private static ConfigurationCache cache;

    static {
        use(new DefaultConfigurationCache());
    }

    public static DtoConfiguration getConfiguration(Class<?> clazz) {
        return cache.loadConfiguration(clazz, DtoConfiguration::new);
    }

    public static void use(ConfigurationCache impl) {
        assert cache == null || cache.size() == 0;
        cache = impl;
    }

    public static void useNoCache() {
        use(new NoCache());
    }
}
