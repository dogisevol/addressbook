package com.example.addressbook.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @Id
    @SequenceGenerator(name = "contact_generator", sequenceName = "contact_generator")
    @GeneratedValue(generator = "contact_generator")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @ManyToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(nullable = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

