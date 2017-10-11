package com.example.addressbook.repository.dto;


import com.example.addressbook.domain.*;
import com.example.addressbook.domain.ref.Gender;
import com.example.addressbook.domain.ref.Title;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class PersonDTO implements Comparable<PersonDTO> {
    private AddressDTO address;
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String personType;
    private Collection<ContactDTO> contacts = new ArrayList<>();

    public PersonDTO() {

    }

    public PersonDTO(Person person) {

        this.address = new AddressDTO(person.getAddress());
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender().name();
        this.id = person.getId();
        this.title = person.getTitle().name();
        if(person.getCommunications() != null && person.getCommunications().size() > 0)
            for (Communication communication : person.getCommunications()) {
                this.contacts.add(new ContactDTO(communication));
            }
    }

    public Person toEntity() {
        Person person = new BusinessContact();
        person.setAddress(this.getAddress().toEntity());
        person.setFirstName(this.getFirstName());
        person.setLastName(this.getLastName());
        person.setGender(Gender.valueOf(this.getGender()));
        person.setId(this.getId());
        person.setTitle(Title.valueOf(this.getTitle()));
        person.setCommunications(new HashSet<>());
        for (ContactDTO contactDTO :this.getContacts()) {
            Communication communication = contactDTO.toEntity();
            communication.setPerson(person);
            person.getCommunications().add(communication);
        }
        return person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(PersonDTO o) {
        return 0;
//        if (this.getaddress() == null)
//            return -1;
//        if (o == null || o.getaddress() == null)
//            return 1;
//        else return this.getaddress().compareTo(o.getaddress());
    }

    public Collection<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}

