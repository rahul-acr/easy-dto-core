package org.easydto;

import org.easydto.domain.Person;
import org.easydto.proxy.Dto;
import org.easydto.proxy.DtoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeConversionTest {

    @Test
    public void testPropertyIntToLong() {
        Dto<Person> dto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        dto.putProperty("age", 15);

        Person person = new Person();
        dto.map(person);

        Assertions.assertEquals(15L, person.age);
    }


    @Test
    public void testPropertyLongToLong() {
        Dto<Person> dto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        dto.putProperty("age", 15L);

        Person person = new Person();
        dto.map(person);

        Assertions.assertEquals(15L, person.age);
    }

    @Test
    public void testPropertyString() {
        Dto<Person> dto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        dto.putProperty("name", "John Doe");

        Person person = new Person();
        dto.map(person);

        Assertions.assertEquals("John Doe", person.name());
    }

    @Test
    public void testDerivedProperty() {
        Dto<Person> dto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        dto.putProperty("country", "India");

        Person person = new Person();
        dto.map(person);

        Assertions.assertFalse(person.isNri);
        Assertions.assertEquals("India", person.country);
    }

    @Test
    public void testProfile(){
        Dto<Person> dto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        dto.putProperty("address", "Kolkata");

        Person personRegularProfile = new Person();
        dto.map(personRegularProfile);

        Assertions.assertEquals("Kolkata", personRegularProfile.address);

        Person personSecureProfile = new Person();
        dto.map(personSecureProfile, "NONSECURE");
        Assertions.assertNull(personSecureProfile.address);
    }

    @Test
    public void testNested(){
        Dto<Person> personDto = DtoFactory.INSTANCE.createDtoFor(Person.class, null);
        personDto.putProperty("name", "John Doe");

        Dto<Person.Contact> contactDto = DtoFactory.INSTANCE.createDtoFor(Person.Contact.class, null);
        contactDto.putProperty("phone", "1234-55-66");
        contactDto.putProperty("email", "john@example.com");

        personDto.putProperty("contact", contactDto);

        Person person = new Person();
        personDto.map(person);

        Assertions.assertEquals("John Doe", person.name());
        Assertions.assertEquals("john@example.com", person.contact.email);
        Assertions.assertEquals("1234-55-66", person.contact.phone);
    }

}
