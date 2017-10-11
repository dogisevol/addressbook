package com.example.addressbook.service;

import com.example.addressbook.domain.Person;
import com.example.addressbook.repository.AddressRepository;
import com.example.addressbook.repository.PersonRepository;
import com.example.addressbook.repository.dto.PersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component("questionService")
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    private final PersonRepository personRepository;
    private String personSchema;
    private String personTypeSchema;
    private String communicationTypeSchema;
    private String businessSchema;
    private String addressSchema;

    public AddressBookServiceImpl(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Iterable<PersonDTO> fetchAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(PersonDTO::new).collect(Collectors.toList());
    }

    @Override
    public PersonDTO fetchByID(Long id) {
        Person person = personRepository.findById(id).get();
        return person == null ? null : new PersonDTO(person);
    }

    @Override
    public String getPersonTypeSchema() {
        return this.personTypeSchema;
    }

    @Override
    public void setPersonTypeSchema(String json) {
        this.personTypeSchema = json;
    }

    @Override
    public String getCommunicationTypeSchema() {
        return this.communicationTypeSchema;
    }

    @Override
    public void setCommunicationTypeSchema(String json) {
        this.communicationTypeSchema = json;
    }

    @Override
    public void setPersonSchema(String jsonSchema) {
        this.personSchema = jsonSchema;
    }

    @Override
    public void setAddressSchema(String json) {
        this.addressSchema = json;
    }

    @Override
    public String getAddressSchema() {
        return this.addressSchema;
    }

//    public static String[] getNames(Class<? extends Enum<?>> e) {
//        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
//    }
//
//    public static String[] getValues(Class<? extends Enum<?>> e) {
//        return Arrays.stream(e.getEnumConstants()).map(Enum::toString).toArray(String[]::new);
//    }

    @Override
    public String getPersonSchema() {
        return personSchema;
    }

    @Override
    public void setBusinessSchema(String json) {
        this.businessSchema = json;
    }

    @Override
    public String getBusinessSchema() {
        return this.businessSchema;
    }
}
