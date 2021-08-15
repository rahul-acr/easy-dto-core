package com.easydto.serialization.jackson;

import com.easydto.proxy.Dto;
import com.easydto.proxy.DtoFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Map;

public class DtoDeserializer extends StdDeserializer<Dto<?>> implements ContextualDeserializer{

    private final DtoFactory dtoFactory = DtoFactory.INSTANCE;
    private Class<?> dtoTargetClass;

    public DtoDeserializer() {
        this(Dto.class, null);
    }

    public DtoDeserializer(JavaType type) {
        this(Dto.class, type);
    }

    protected DtoDeserializer(Class vc, JavaType type) {
        super(vc);
        if(type != null && type.getBindings().size() > 0) {
            dtoTargetClass = type.getBindings().getBoundType(0).getRawClass();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Dto<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, Object> map = ctxt.readValue(p, Map.class);
        Dto<?> proxy = dtoFactory.createDtoFor(dtoTargetClass, null);
        map.forEach(proxy::putProperty);
        return proxy;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JavaType type = ctxt.getContextualType() != null ? ctxt.getContextualType() : property.getMember().getType();
        return new DtoDeserializer(type);
    }
}
