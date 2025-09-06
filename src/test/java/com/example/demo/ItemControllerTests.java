
package com.example.demo;

import com.example.demo.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
class ItemControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper om;

    @BeforeEach
    void setup() throws Exception {
        Item sample = new Item();
        sample.setName("Pen");
        sample.setPrice(new BigDecimal("1.99"));
        mvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(sample)))
            .andExpect(status().isOk());
    }

    @Test
    void listWithPagination() throws Exception {
        mvc.perform(get("/items?page=0&size=1")).andExpect(status().isOk());
    }

    @Test
    void createItem() throws Exception {
        Item i = new Item();
        i.setName("Notebook");
        i.setPrice(new BigDecimal("3.50"));
        mvc.perform(post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(i)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void updateItem() throws Exception {
        Item update = new Item();
        update.setName("Pen Pro");
        update.setPrice(new BigDecimal("2.49"));
        mvc.perform(put("/items/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(update)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Pen Pro"));
    }

    @Test
    void deleteItem() throws Exception {
        mvc.perform(delete("/items/{id}", 1))
           .andExpect(status().isNoContent());
    }
}
