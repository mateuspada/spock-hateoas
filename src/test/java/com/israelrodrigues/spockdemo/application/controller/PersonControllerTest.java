package com.israelrodrigues.spockdemo.application.controller;

import com.israelrodrigues.spockdemo.domain.Person;
import com.israelrodrigues.spockdemo.repository.PersonRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class PersonControllerTest extends MockMvcBase {

    @Autowired
    private PersonRepository repository;

    @Test
    public void shouldFindAll() throws Exception {
        createPerson(new Person("Israel", "Rodrigues"));

        this.mockMvc
                .perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.size", is(20)))
                .andExpect(jsonPath("$.content.[0].id", notNullValue()))
                .andExpect(jsonPath("$.content.[0].firstName", is("Israel")))
                .andExpect(jsonPath("$.content.[0].lastName", is("Rodrigues")));
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }
}