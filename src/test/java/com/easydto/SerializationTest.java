package com.easydto;

import com.easydto.conversion.converter.impl.DefaultDtoConverter;
import com.easydto.domain.Department;
import com.easydto.domain.Student;
import com.easydto.proxy.Dto;
import com.easydto.serialization.jackson.Registerer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SerializationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        Registerer.registerModules(mapper);
    }

    @Test
    public void simpleSerialization() throws JsonProcessingException {
        Student student = new Student("John", new Department(1, "CST"));

        DefaultDtoConverter dtoConverter = new DefaultDtoConverter();
        Dto<Student> dto = dtoConverter.convert(student);
        System.out.println(dto);

        final String json = mapper.writeValueAsString(dto);

        Assertions.assertEquals("{\"name\":\"John\",\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":true}", json);
    }

    @Test
    public void profileBasedSerialization() throws JsonProcessingException {
        Student student = new Student("John", new Department(1, "CST"));

        DefaultDtoConverter dtoConverter = new DefaultDtoConverter();
        Dto<Student> dto = dtoConverter.convert(student, "REST");
        System.out.println(dto);

        final String json = mapper.writeValueAsString(dto);

        Assertions.assertEquals("{\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":true}", json);
    }

}
