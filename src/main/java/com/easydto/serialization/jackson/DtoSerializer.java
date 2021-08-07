package com.easydto.serialization.jackson;

import com.easydto.proxy.Dto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class DtoSerializer extends StdSerializer<Dto> {


    public DtoSerializer(){
        this(null);
    }

    protected DtoSerializer(Class<Dto> t) {
        super(t);
    }

    @Override
    public void serialize(Dto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        serializers.findTypedValueSerializer(Map.class, true, null)
                .serialize(value.getValues(), gen, serializers);
    }
}
