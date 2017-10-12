package com.example.addressbook.dto;


import com.example.addressbook.domain.*;
import com.example.addressbook.domain.ref.Gender;
import com.example.addressbook.domain.ref.PersonType;
import com.example.addressbook.domain.ref.Title;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class PersonDTO implements Comparable<PersonDTO> {
    private String company;
    private AddressDTO address;
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private Date dob;
    private Date creationDate;
    private String gender;
    private String type;
    private Collection<ContactDTO> communications = new ArrayList<>();

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
                this.communications.add(new ContactDTO(communication));
            }
        this.type = person.getType().name();
        if(person instanceof PersonalContact){
            this.dob = ((PersonalContact)person).getDob();
        }else{
            this.company = ((BusinessContact)person).getCompany();
        }
    }

    public Person toEntity() {
        Person person;
        if(PersonType.Business.name().equalsIgnoreCase(this.type)){
            person = new BusinessContact();
        }else{
            person = new PersonalContact();
            ((PersonalContact)person).setDob(this.getDob());
        }

        person.setAddress(this.getAddress().toEntity());
        person.getAddress().setPerson(person);
        person.setFirstName(this.getFirstName());
        person.setLastName(this.getLastName());
        person.setGender(Gender.valueOf(this.getGender()));
        person.setId(this.getId());
        person.setTitle(Title.valueOf(this.getTitle()));
        person.setCommunications(new HashSet<>());
        for (ContactDTO contactDTO :this.getCommunications()) {
            Communication communication = contactDTO.toEntity();
            communication.setPerson(person);
            person.getCommunications().add(communication);
            communication.setPerson(person);
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

    public Collection<ContactDTO> getCommunications() {
        return communications;
    }

    public void setCommunications(Collection<ContactDTO> communications) {
        this.communications = communications;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

