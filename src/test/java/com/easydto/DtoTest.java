package com.easydto;

import com.easydto.converter.impl.DefaultDtoConverter;
import com.easydto.converter.impl.DtoDeConverterImpl;
import com.easydto.proxy.Dto;
import com.easydto.serialization.jackson.DtoDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DtoTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        SimpleModule module = new SimpleModule().addDeserializer(Dto.class, new DtoDeserializer());
        mapper.registerModule(module);

        Student student = new Student("Shalmoli", new Department(1L, "CST"));
//        System.out.println(mapper.writeValueAsString(student));


//        ProxyMaker proxyMaker = new ProxyMaker();
//        Dto<?> dto = proxyMaker.createProxy();
//        dto.getField("name");

        DefaultDtoConverter dtoConverter = new DefaultDtoConverter();
        Dto<Student> dto = dtoConverter.convert(student);
        System.out.println(dto);

        String json = mapper.writeValueAsString(dto);
        System.out.println(json);

        Dto<Student> ndto = mapper.readValue(json, Dto.class);
        System.out.println("object read as : " + ndto);

        System.out.println("object converted to : " + new DtoDeConverterImpl().convert(ndto, new Student()));

    }

}
