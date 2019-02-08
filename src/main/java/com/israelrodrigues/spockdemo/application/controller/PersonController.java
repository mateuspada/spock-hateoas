package com.israelrodrigues.spockdemo.application.controller;

import com.israelrodrigues.spockdemo.application.representation.common.IdRepresentation;
import com.israelrodrigues.spockdemo.application.representation.person.PersonRepresentation;
import com.israelrodrigues.spockdemo.application.representation.person.PersonResource;
import com.israelrodrigues.spockdemo.application.request.person.CreatePersonRequest;
import com.israelrodrigues.spockdemo.domain.Person;
import com.israelrodrigues.spockdemo.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public IdRepresentation create(@RequestBody CreatePersonRequest request) {
        Person person = service.create(request.getFirstName(), request.getLastName(), request.getPassport());
        return new IdRepresentation(person.getId());
    }

    @GetMapping
    @ResponseStatus(OK)
    public PagedResources<PersonResource> findAll(Pageable pageable,
                                                        PagedResourcesAssembler assembler) {
        Page<PersonResource> persons = service.findAll(pageable).map(this::toRepresentation).map(this::toResource);
        return assembler.toResource(persons);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public PersonResource find(@PathVariable("id") Long id) {
        Person person = service.findOne(id);
        return toResource(toRepresentation(person));
    }

    //mapstruct
    private PersonRepresentation toRepresentation(Person person) {
        return new PersonRepresentation(person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getPassport());
    }

    private PersonResource toResource(PersonRepresentation personRepresentation) {
        return new PersonResource(personRepresentation);
    }
}