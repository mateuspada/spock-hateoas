package com.israelrodrigues.spockdemo.domain;

import com.israelrodrigues.spockdemo.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String passport;

    public Person(String firstName, String lastName, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public static Person create(String firstName,
                                String lastName,
                                String passport,
                                PersonRepository repository) {
        return repository.save(new Person(firstName, lastName, passport));
    }

    public Person update(String firstName, String lastName, String passport, PersonRepository repository) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        return repository.save(this);
    }

    public Person update(Person person, PersonRepository repository) {
        return update(person.getFirstName(), person.getPassport(), person.getPassport(), repository);
    }

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}