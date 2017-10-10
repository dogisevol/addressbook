package com.example.addressbook.service;

import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.Gender;
import com.example.addressbook.domain.Person;
import com.example.addressbook.domain.Title;
import com.example.addressbook.repository.AddressRepository;
import com.example.addressbook.repository.PersonRepository;
import com.example.addressbook.repository.dto.ContactDTO;
import com.example.addressbook.repository.dto.PersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component("questionService")
@Transactional
public class AddressBookServiceImpl implements AddressBookService {

    private final PersonRepository personRepository;

    public AddressBookServiceImpl(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Iterable<PersonDTO> fetchAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(PersonDTO::new).collect(Collectors.toList());
    }

    @Override
    public PersonDTO fetchByID(Long id) {
        Person person = personRepository.findById(id).get();
        return person == null ? null : new PersonDTO(person);
    }

    @Override
    public Map<Object, Object> getPersonalSchema() {
        Map<Object, Object> leadSchema = getLeadSchema();
        leadSchema.putAll(toMap("dateOfBirth", toMap("type", "date", "propertyOrder", 55)));
        return toMap("properties", leadSchema);
    }

    @Override
    public Map<Object, Object> getBusinessSchema() {
        Map<Object, Object> leadSchema = getLeadSchema();
        leadSchema.putAll(toMap("company", toMap("type", "string", "propertyOrder", 55)));
        leadSchema.putAll(toMap("numberOfStaff", toMap("type", "number", "minimum", 1, "maximum", 100, "section", "Other", "propertyOrder", 85)));
        return toMap("properties", leadSchema);
    }


    private Map<Object, Object> getLeadSchema() {
        return
                toMap(
                 "id",
                        toMap("hidden", true),
                        "type",
                        toMap("hidden", true),
                        "title",
                        toMap(
                                "enum", getNames(Title.class), "required", true, "propertyOrder", 10
                        ),
                        "firstName",
                        toMap(
                                "type", "string", "required", true, "propertyOrder", 20
                        ),
                        "lastName",
                        toMap(
                                "type", "string", "required", true, "propertyOrder", 30
                        ),
                        "gender",
                        toMap(
                                "enum", getNames(Gender.class), "required", true, "propertyOrder", 40
                        ),
                        "address",
                        toMap(
                                "section", "Address", "propertyOrder", 50

                        ),
                        "contact",
                        toMap(
                                "propertyOrder", 60

                        ),
                        "info",
                        toMap(
                                "type", "string", "large", true, "section", "Other", "propertyOrder", 90
                        )
                );
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    private Map<Object, Object> toMap(Object... pairs) {
        Map<Object, Object> result = new HashMap<>();
        for (int i = 0; i < pairs.length - 1; i = i + 2) {
            result.put(pairs[i], pairs[i + 1]);
        }
        return result;
    }
}
