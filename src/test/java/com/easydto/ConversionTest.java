package com.easydto;

import com.easydto.domain.Person;
import com.easydto.proxy.Dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConversionTest {

    @Test
    public void testSimpleStringProperty(){
        Person person = new Person();
        person.name("John Doe");

        Dto<Person> personDto = Dto.from(person);
        Assertions.assertEquals("John Doe", personDto.getProperty("name"));
    }

    @Test
    public void testProfile(){
        Person person = new Person();
        person.address = "Kolkata";

        Dto<Person> securePersonDto = Dto.from(person, "SECURE");
        Assertions.assertEquals("Kolkata", securePersonDto.getProperty("address"));


        Dto<Person> regularPersonDto = Dto.from(person);
        Assertions.assertEquals("Kolkata", regularPersonDto.getProperty("address"));


        Dto<Person> insecurePersonDto = Dto.from(person, "INSECURE");
        Assertions.assertNull(insecurePersonDto.getProperty("address"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testNestedProperty(){
        Person person = new Person();
        person.name("John Doe");

        person.contact = new Person.Contact();
        person.contact.email = "john@example.com";
        person.contact.phone = "1234-55-66";

        Dto<Person> personDto = Dto.from(person);

        Assertions.assertEquals("John Doe", personDto.getProperty("name"));

        Dto<Person.Contact> contactDto = (Dto<Person.Contact>) personDto.getProperty("contact");
        Assertions.assertEquals("john@example.com", contactDto.getProperty("email"));
        Assertions.assertEquals("1234-55-66", contactDto.getProperty("phone"));
    }

    @Test
    public void testCustomPropertyName(){
        Person person = new Person();
        person.sex = "male";

        Dto<Person> personDto = Dto.from(person);

        Assertions.assertEquals("male", personDto.getProperty("gender"));
    }

    @Test
    public void testWrapperProperty(){
        Person person = new Person();
        person.age = 15L;

        Dto<Person> personDto = Dto.from(person);

        Assertions.assertEquals(15L, personDto.getProperty("age"));
    }
}
