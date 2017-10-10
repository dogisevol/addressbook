package com.example.addressbook;

public enum Error {

    contact_NOT_FOUND_ERROR("contact has not been found"),
    WITHDRAWAL_ERROR("Required amount cannot be withdrawn"),
    NEGATIVE_COUNT("Count cannot be negative");

    private final String text;

    Error(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
