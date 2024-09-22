package com.example.pessoas.controller;

import com.example.pessoas.model.Person;
import com.example.pessoas.service.PersonService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Flux<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public Mono<Person> createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
