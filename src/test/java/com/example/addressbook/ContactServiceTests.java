package com.example.addressbook;

import com.example.addressbook.repository.ContactRepository;
import com.example.addressbook.service.AddressBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTests {

    @Autowired
    AddressBookService contactService;
    @MockBean
    ContactRepository contactRepository;

    @Test
    public void contextLoads() {
    }
}
