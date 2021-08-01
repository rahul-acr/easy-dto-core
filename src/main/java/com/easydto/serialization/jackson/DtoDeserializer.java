package com.easydto.serialization.jackson;

import com.easydto.Dto;
import com.easydto.proxy.ProxyMaker;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Map;

public class DtoDeserializer extends StdDeserializer<Dto<?>> {

    private final ProxyMaker proxyMaker = new ProxyMaker();

    public DtoDeserializer() {
        this(Dto.class);
    }

    protected DtoDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Dto<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, Object> map = ctxt.readValue(p, Map.class);
        Dto<?> proxy = proxyMaker.createProxy(null);
        map.forEach(proxy::setField);
        return proxy;
    }
}
