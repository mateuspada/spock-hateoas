package com.israelrodrigues.spockdemo.domain

import com.israelrodrigues.spockdemo.SpockDemoApplication
import com.israelrodrigues.spockdemo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = SpockDemoApplication.class)
class PersonTest extends Specification {

    @Autowired
    PersonRepository repository

    @Transactional
    def "should create person"() {
        when:
        def person = Person.create("Israel", "Rodrigues", "423432D", repository)

        then:
        person.id != null
        person.firstName == "Israel"
        person.lastName == "Rodrigues"
        person.passport == "423432D"
    }

    @Transactional
    def "should update person"() {
        given:
        def person = Person.create("Samuel", "Rodrigues", "423432D", repository)

        when:
        def personUpdated = person.update("Israel", "Rodrigues", "423432D", repository)

        then:
        personUpdated == new Person(person.id, "Israel", "Rodrigues", "423432D")
    }

    def "should get full name"() {
        given:
        def person = new Person("Israel", "Rodrigues", "423432D")

        expect:
        person.getFullName() == "Israel Rodrigues"
    }

    def "should get full name in batch"() {
        expect:
        person.getFullName() == fullName

        where:
        person                            | fullName
        new Person("Israel", "Rodrigues") | "Israel Rodrigues"
        new Person("Samuel", "Rodrigues") | "Samuel Rodrigues"
        new Person("Rafael", "Rodrigues") | "Rafael Rodrigues"
        new Person("123", "456")          | "123 456"
    }

    @Unroll
    def "should get #fullName full name"() {
        expect:
        person.getFullName() == fullName

        where:
        person                            | fullName
        new Person("Israel", "Rodrigues") | "Israel Rodrigues"
        new Person("Samuel", "Rodrigues") | "Samuel Rodrigues"
        new Person("Rafael", "Rodrigues") | "Rafael Rodrigues"
        new Person("123", "456")          | "123 456"
    }
}