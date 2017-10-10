package com.example.addressbook.repository.dto;


import com.example.addressbook.domain.Address;

public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postcode;

    public AddressDTO() {

    }

    public AddressDTO(Address address) {

        this.id = address.getId();
        this.city = address.getCity();
        this.state = address.getState();
        this.street = address.getStreet();
        this.postcode = address.getPostcode();
    }

    public Address toEntity() {
        Address address = new Address();
        address.setId(this.id);
        address.setCity(this.getCity());
        address.setState(this.getState());
        address.setStreet(this.getStreet());
        address.setPostcode(this.postcode);
        return address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

