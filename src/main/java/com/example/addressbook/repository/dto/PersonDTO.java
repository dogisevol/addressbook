package com.example.addressbook.repository.dto;


import com.example.addressbook.domain.*;

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
        this.dob = person.getDob();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender().name();
        this.id = person.getId();
        this.title = person.getTitle().name();
        if(person.getContacts() != null && person.getContacts().size() > 0)
            for (Contact contact : person.getContacts()) {
                this.contacts.add(new ContactDTO(contact));
            }
    }

    public Person toEntity() {
        Person person = new Person();
        person.setAddress(this.getAddress().toEntity());
        person.setDob(this.getDob());
        person.setFirstName(this.getFirstName());
        person.setLastName(this.getLastName());
        person.setGender(Gender.valueOf(this.getGender()));
        person.setId(this.getId());
        person.setTitle(Title.valueOf(this.getTitle()));
        person.setContacts(new HashSet<>());
        for (ContactDTO contactDTO :this.getContacts()) {
            Contact contact = contactDTO.toEntity();
            contact.setPerson(person);
            person.getContacts().add(contact);
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

