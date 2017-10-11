package com.example.addressbook.controller;

import com.example.addressbook.dto.PersonDTO;
import com.example.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional(readOnly = true)
    public Iterable<PersonDTO> getAll() {
        return addressBookService.fetchAll();
    }

    @RequestMapping(value = "/schema/personal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPersonalSchema() {
        return addressBookService.getPersonSchema();
    }

    @RequestMapping(value = "/schema/business", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String  getBusinessSchema() {
        return addressBookService.getBusinessSchema();
    }

    @RequestMapping(value = "/schema/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAddressSchema() {
        return addressBookService.getAddressSchema();
    }

    @RequestMapping(value = "/schema/contactType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPersonTypeSchema() {
        return addressBookService.getPersonTypeSchema();
    }

    @RequestMapping(value = "/schema/communicationType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getContactTypeSchema() {
        return addressBookService.getCommunicationTypeSchema();
    }


//    @RequestMapping(value = "/contact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    @Transactional
//    public ResponseEntity<ContactDTO> initATM(@RequestBody ContactDTO body, UriComponentsBuilder b) {
//        ContactDTO contactDTO = addressBookService.initializeATM(body);
//        UriComponents uriComponents =
//                b.path("/contact/{id}").buildAndExpand(contactDTO.getId());
//        return ResponseEntity.created(uriComponents.toUri()).body(contactDTO);
//    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public ResponseEntity<PersonDTO> findATM(@PathVariable Long id) {
        PersonDTO personDTO = addressBookService.fetchByID(id);
        if (personDTO != null)
            return ResponseEntity.ok(personDTO);
        else
            return ResponseEntity.notFound().build();
    }
}
