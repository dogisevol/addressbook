package com.example.addressbook.domain;

import com.example.addressbook.domain.ref.Gender;
import com.example.addressbook.domain.ref.PersonType;
import com.example.addressbook.domain.ref.Title;
import org.hibernate.annotations.GenerationTime;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.inspector.annotation.UiLarge;
import org.metawidget.inspector.annotation.UiRequired;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@Table(name="PERSON")
@Entity
public abstract class Person {
    private static final long serialVersionUID = -3501514625217380338L;
    @Id
    @SequenceGenerator(name = "Person_generator", sequenceName = "Person_sequence", initialValue = 100)
    @GeneratedValue(generator = "Person_generator")
    private Long id;

    @Column(name = "creation_date", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    private ZonedDateTime creationDate;

    @UiRequired
    private String firstName;

    @UiRequired
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Title title;

    @UiRequired
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @UiRequired
    @OneToOne(mappedBy = "person")
    private Address address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Communication> communications;

    private String notes;


    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @UiHidden
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(Set<Communication> communications) {
        this.communications = communications;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @UiLarge
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    abstract public PersonType getType();
}
