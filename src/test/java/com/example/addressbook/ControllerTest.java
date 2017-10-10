package com.example.addressbook;

import com.example.addressbook.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    @Autowired
    AddressBookService contactService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetcontacts() throws Exception {
        assertResult(this.mockMvc.perform(get("/contact")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()));

    }

    private void assertResult(ResultActions perform) throws Exception {
        String content = perform
                .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andReturn().getResponse().getContentAsString();
        assert content.contains("coins");
        assert content.contains("banknotes");
    }
}
