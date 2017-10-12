package com.example.addressbook.domain;

import com.example.addressbook.domain.ref.CommunicationType;
import org.hibernate.annotations.Cascade;
import org.metawidget.inspector.annotation.UiRequired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Communication implements Serializable {

    @Id
    @SequenceGenerator(name = "Communication_generator", sequenceName = "Communication_generator", initialValue = 100)
    @GeneratedValue(generator = "Communication_generator")
    private Long id;

    @UiRequired
    @Enumerated(EnumType.STRING)
    private CommunicationType communicationType;
    @ManyToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "person_id")
    private Person person;

    @UiRequired
    @Column(nullable = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
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

