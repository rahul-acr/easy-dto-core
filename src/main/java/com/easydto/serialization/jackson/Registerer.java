package com.easydto.serialization.jackson;

import com.easydto.proxy.Dto;
import com.easydto.proxy.ValueMapDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Registerer {

    public static void registerModules(ObjectMapper mapper){
        SimpleModule module = new SimpleModule()
                .addDeserializer(Dto.class, new DtoDeserializer())
                .addSerializer(ValueMapDto.class, new DtoSerializer());
        mapper.registerModule(module);
    }

}
