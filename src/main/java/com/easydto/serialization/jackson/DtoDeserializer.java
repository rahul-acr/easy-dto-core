package com.easydto.serialization.jackson;

import com.easydto.proxy.Dto;
import com.easydto.proxy.DtoFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Map;

public class DtoDeserializer extends StdDeserializer<Dto<?>> {

    private final DtoFactory dtoFactory = DtoFactory.INSTANCE;

    public DtoDeserializer() {
        this(Dto.class);
    }

    protected DtoDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Dto<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, Object> map = ctxt.readValue(p, Map.class);
        Dto<?> proxy = dtoFactory.createDtoFor(null, null);
        map.forEach(proxy::putProperty);
        return proxy;
    }
}
