package com.easydto;

import com.easydto.conversion.converter.impl.DefaultDtoConverter;
import com.easydto.conversion.converter.impl.DefaultDtoDeConverter;
import com.easydto.proxy.Dto;
import com.easydto.proxy.ValueMapDto;
import com.easydto.serialization.jackson.DtoDeserializer;
import com.easydto.serialization.jackson.DtoSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DtoTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        SimpleModule module = new SimpleModule()
                .addDeserializer(Dto.class, new DtoDeserializer())
                .addSerializer(ValueMapDto.class, new DtoSerializer());
        mapper.registerModule(module);

        Student student = new Student("John", new Department(1, "CST"));

        DefaultDtoConverter dtoConverter = new DefaultDtoConverter();
        Dto<Student> dto = dtoConverter.convert(student);
        System.out.println(dto);

        String json = mapper.writeValueAsString(dto);
        System.out.println(json);

        Dto<Student> ndto = mapper.readValue(json, Dto.class);
        System.out.println("object read as : " + ndto);

        System.out.println("object converted to : " + new DefaultDtoDeConverter().convert(ndto, new Student()));



        // profile based conversion
        {
            Dto<Student> adto = dtoConverter.convert(student, "REST");
            System.out.println(mapper.writeValueAsString(adto));
        }
    }

}
