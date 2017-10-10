package com.example.addressbook.domain;

import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = -3501514625217380338L;
    @Id
    @SequenceGenerator(name = "contact_generator", sequenceName = "contact_sequence", initialValue = 100)
    @GeneratedValue(generator = "contact_generator")
    private Long id;

    @Column(name = "creation_date", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    private ZonedDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    private String firstName;
    private String lastName;
    private Title title;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date dob;

    @OneToOne(mappedBy = "person")
    private Address address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Contact> contacts;


    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
