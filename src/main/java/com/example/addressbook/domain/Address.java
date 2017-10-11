package com.example.addressbook.domain;

import org.hibernate.annotations.Cascade;
import org.metawidget.inspector.annotation.UiRequired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable {


    private static final long serialVersionUID = 1292824767348769583L;
    @Id
    @SequenceGenerator(name = "address_generator", sequenceName = "address_generator")
    @GeneratedValue(generator = "address_generator")
    private Long id;

    @UiRequired
    @Column(nullable = false)
    private String street;

    @UiRequired
    @Column(nullable = false)
    private String city;

    @UiRequired
    @Column(nullable = false)
    private String state;

    @UiRequired
    @Column(nullable = false)
    private String postcode;

    @OneToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

