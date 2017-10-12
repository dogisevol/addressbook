package com.example.addressbook;

import com.example.addressbook.domain.Address;
import com.example.addressbook.domain.BusinessContact;
import com.example.addressbook.domain.Person;
import com.example.addressbook.domain.ref.Gender;
import com.example.addressbook.domain.ref.PersonType;
import com.example.addressbook.domain.ref.Title;
import com.example.addressbook.dto.AddressDTO;
import com.example.addressbook.dto.PersonDTO;
import com.example.addressbook.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = AddessBookApp.class
)
@AutoConfigureMockMvc
public class ControllerTest {

    @MockBean
    AddressBookService addressBookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        List<PersonDTO> list = new ArrayList();
        PersonDTO dto = new PersonDTO();
        dto.setAddress(new AddressDTO());
        list.add(dto);

        Person person = new BusinessContact();
        person.setAddress(new Address());
        person.setGender(Gender.Female);
        person.setTitle(Title.Miss);
        person.setCommunications(new HashSet<>());

        Mockito.when(addressBookService.fetchAll()).thenReturn(list);
        Mockito.when(addressBookService.save(Mockito.any(Person.class)))
                .thenReturn(person);
        Mockito.when(addressBookService.fetchByID(Mockito.any(Long.class)))
                .thenReturn(dto);
    }

    @Test
    public void testGetContact() throws Exception {
        assertResult(this.mockMvc.perform(get("/contact")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()));

    }

    @Test
    public void testSaveContacts() throws Exception {
        PersonDTO dto = new PersonDTO();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setCompany("test");
        dto.setGender(Gender.Female.name());
        dto.setTitle(Title.Miss.name());
        dto.setCreationDate(new Date());
        dto.setDob(new Date());
        dto.setId(1L);
        dto.setTitle(Title.Miss.name());
        dto.setType(PersonType.Business.name());
        AddressDTO addressDTO = new AddressDTO();
        dto.setAddress(addressDTO);
        addressDTO.setCity("test");
        addressDTO.setPostcode("test");
        addressDTO.setStreet("test");
        addressDTO.setId(1L);
        assertResult(this.mockMvc.perform(post("/contact")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))));

    }

    private void assertResult(ResultActions perform) throws Exception {
        String content = perform
                .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andReturn().getResponse().getContentAsString();
        assert content.contains("address");
        assert content.contains("firstName");
    }
}
