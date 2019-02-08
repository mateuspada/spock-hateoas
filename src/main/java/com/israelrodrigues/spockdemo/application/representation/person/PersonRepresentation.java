package com.israelrodrigues.spockdemo.application.representation.person;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonRepresentation {
    private Long id;
    private String firstName;
    private String lastName;
    private String passport;
}