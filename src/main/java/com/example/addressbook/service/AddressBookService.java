package com.example.addressbook.service;

import com.example.addressbook.repository.dto.ContactDTO;
import com.example.addressbook.repository.dto.PersonDTO;

import java.util.Map;

public interface AddressBookService {

    Iterable<PersonDTO> fetchAll();

    PersonDTO fetchByID(Long id);

    Map<Object, Object> getBusinessSchema();
    Map<Object, Object> getPersonalSchema();
}
