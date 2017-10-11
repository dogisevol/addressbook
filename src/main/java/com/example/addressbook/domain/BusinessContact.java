package com.example.addressbook.domain;

import com.example.addressbook.domain.ref.Gender;
import com.example.addressbook.domain.ref.PersonType;
import com.example.addressbook.domain.ref.Title;
import org.hibernate.annotations.GenerationTime;
import org.metawidget.inspector.annotation.UiRequired;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="PERSON")
@DiscriminatorValue("B")
public class BusinessContact extends Person{

    @Override
    public PersonType getType() {
        return PersonType.Business;
    }
}
