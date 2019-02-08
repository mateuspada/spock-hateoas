package com.israelrodrigues.spockdemo.application.representation.person;

import com.israelrodrigues.spockdemo.application.controller.PersonController;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class PersonResource extends ResourceSupport {
    private PersonRepresentation personRepresentation;

    public PersonResource(PersonRepresentation personRepresentation) {
        this.personRepresentation = personRepresentation;
        Long id = personRepresentation.getId();
        add(linkTo(PersonController.class).withRel("person"));
        add(linkTo(methodOn(PersonController.class).find(id)).withSelfRel());
    }
}
