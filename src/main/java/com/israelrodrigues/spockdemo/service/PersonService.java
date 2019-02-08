package com.israelrodrigues.spockdemo.service;

import com.israelrodrigues.spockdemo.configuration.NotFoundException;
import com.israelrodrigues.spockdemo.domain.Person;
import com.israelrodrigues.spockdemo.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Page<Person> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Person findOne(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Person create(String firstName, String lastName, String passport) {
        return Person.create(firstName, lastName, passport, repository);
    }

    public Person update(Person personFromRequest) {
        Person personToUpdate = repository.findById(personFromRequest.getId())
                .orElseThrow(NotFoundException::new);
        return personToUpdate.update(personFromRequest, repository);
    }
}