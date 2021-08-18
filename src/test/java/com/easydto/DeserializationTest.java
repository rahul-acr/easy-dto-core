package com.easydto;

import com.easydto.domain.Student;
import com.easydto.proxy.Dto;
import com.easydto.serialization.jackson.Registerer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeserializationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void setup(){
        Registerer.registerModules(mapper);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void deserializeWithoutType() throws JsonProcessingException {
        String json = "{\"name\":\"John\",\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":false}";
        Dto<Student> dto = mapper.readValue(json, Dto.class);

        Assertions.assertNotNull(dto);

        Student student = new Student();
        dto.map(student);

        assertEquals("John", student.name);
        assertTrue(student.hasDepartment());
        assertEquals("CST", student.department.name);
        assertEquals(1, student.department.id);
    }

    @Test
    public void deserializeWithType() throws JsonProcessingException {
        String json = "{\"name\":\"John\",\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":false}";
        Dto<Student> ccdto;
        JavaType javaType = mapper.constructType(ParameterizedTypeImpl.make(Dto.class, new Type[]{Student.class}, null));
        ccdto = mapper.readValue(json, javaType);
        assert ccdto.getTargetClass() == Student.class;
    }

}
