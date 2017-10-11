package com.example.addressbook.dto;


import com.example.addressbook.domain.Communication;
import com.example.addressbook.domain.ref.CommunicationType;

public class ContactDTO {
    private Long id;
    private String type;
    private String value;

    public ContactDTO() {
    }

    public ContactDTO(Communication communication) {
        this.id = communication.getId();
        this.value = communication.getValue();
        this.type = communication.getCommunicationType().name();
    }

    public Communication toEntity() {
        Communication communication = new Communication();
        communication.setId(this.getId());
        communication.setValue(this.getValue());
        communication.setCommunicationType(CommunicationType.valueOf(this.getType()));
        return communication;
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
