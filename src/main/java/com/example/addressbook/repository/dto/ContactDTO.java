package com.example.addressbook.repository.dto;


import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.ContactType;

public class ContactDTO {
    private Long id;
    private String type;
    private String value;

    public ContactDTO() {
    }

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.value = contact.getValue();
        this.type = contact.getType().name();
    }

    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setId(this.getId());
        contact.setValue(this.getValue());
        contact.setType(ContactType.valueOf(this.getType()));
        return contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
