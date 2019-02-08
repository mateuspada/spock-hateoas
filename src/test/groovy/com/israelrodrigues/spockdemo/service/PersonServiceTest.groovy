package com.israelrodrigues.spockdemo.service

import com.israelrodrigues.spockdemo.configuration.NotFoundException
import com.israelrodrigues.spockdemo.domain.Person
import com.israelrodrigues.spockdemo.repository.PersonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import spock.lang.Specification

class PersonServiceTest extends Specification {

    def repository = Mock(PersonRepository)
    def service = new PersonService(repository)
    def pageable = Mock(Pageable)

    def "should find one person"() {
        given:
        def id = 123L
        def mockedPerson = Optional.of(new Person("Israel", "Rodrigues", "423432D"))

        when:
        def person = service.findOne(id)

        then:
        1 * repository.findById(id) >> mockedPerson
        person == person
        0 * _
    }

    def "should not find one and throw not found exception"() {
        when:
        service.findOne(999999L)

        then:
        1 * repository.findById(_) >> Optional.ofNullable(null)
        thrown(NotFoundException)
        0 * _
    }

    def "should find all people"() {
        given:
        def page = Mock(Page)

        when:
        def result = service.findAll(pageable)

        then:
        1 * repository.findAll(pageable) >> page
        result == page
        0 * _
    }
}