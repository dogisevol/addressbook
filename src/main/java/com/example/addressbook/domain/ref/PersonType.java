package com.example.addressbook.domain.ref;


public enum PersonType {
    Personal("Personal Communication"), Business("Business Communication");
    private final String text;

    PersonType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}