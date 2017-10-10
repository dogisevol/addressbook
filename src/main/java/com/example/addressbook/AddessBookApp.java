package com.example.addressbook;

import com.example.addressbook.repository.ContactRepository;
import com.example.addressbook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddessBookApp {

    public static void main(String[] args) {
        SpringApplication.run(AddessBookApp.class, args);
    }

    //Initialize contact on start
}
