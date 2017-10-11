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

import static org.hibernate.hql.internal.antlr.HqlSqlTokenTypes.WHERE;

@Entity
@Table(name="PERSON")
@DiscriminatorValue("P")
public class PersonalContact extends Person {
    @UiRequired
    private Date dob;

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public PersonType getType() {
        return PersonType.Personal;
    }
}
