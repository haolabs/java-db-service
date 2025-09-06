package com.example.demo;

import com.example.demo.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemFlowTests {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @Test
    void createThenFetch() throws Exception {
        Item i = new Item();
        i.setName("Book");
        i.setPrice(new BigDecimal("9.99"));

        // create
        String res = mvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(i)))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Item saved = om.readValue(res, Item.class);

        // fetch
        mvc.perform(get("/items/{id}", saved.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Book"));
    }
}
