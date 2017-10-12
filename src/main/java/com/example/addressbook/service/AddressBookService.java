package com.example.addressbook.service;

import com.example.addressbook.domain.Person;
import com.example.addressbook.dto.PersonDTO;

public interface AddressBookService {

    Iterable<PersonDTO> fetchAll();

    PersonDTO fetchByID(Long id);


    String getPersonTypeSchema();

    void setPersonTypeSchema(String json);

    String getCommunicationTypeSchema();

    void setCommunicationTypeSchema(String json);

    void setPersonSchema(String json);

    void delete(Long person);

    String getPersonSchema();

    void setBusinessSchema(String json);

    String getBusinessSchema();

    void setAddressSchema(String json);

    String getAddressSchema();

    Person save(Person person);
}
