package com.example.addressbook.service;

import com.example.addressbook.domain.Communication;
import com.example.addressbook.domain.Person;
import com.example.addressbook.dto.PersonDTO;
import com.example.addressbook.repository.CommunicationRepository;
import com.example.addressbook.repository.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component("questionService")
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    private final PersonRepository personRepository;
    private final CommunicationRepository communicationRepository;
    private String personSchema;
    private String personTypeSchema;
    private String communicationTypeSchema;
    private String businessSchema;
    private String addressSchema;

    public AddressBookServiceImpl(PersonRepository personRepository, CommunicationRepository communicationRepository) {
        this.personRepository = personRepository;
        this.communicationRepository = communicationRepository;
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

    @Override
    public Person save(Person person) {
        Set<Communication> communications = person.getCommunications();
        person.setCommunications(new HashSet<>());
        Person result = this.personRepository.save(person);
        for (Communication c : communications) {
            c.setPerson(result);
            c.setId(null);
            this.communicationRepository.save(c);

        }
        return result;
    }

    @Override
    public void delete(Long id) {
        Optional<Person> byId = this.personRepository.findById(id);
        if (byId.isPresent())
            this.personRepository.delete(byId.get());
    }

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
