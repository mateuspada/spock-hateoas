package com.israelrodrigues.spockdemo.application.request.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePersonRequest {
    private String firstName;
    private String lastName;
    private String passport;
}