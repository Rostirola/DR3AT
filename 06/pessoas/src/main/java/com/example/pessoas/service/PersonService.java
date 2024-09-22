package com.example.pessoas.service;

import com.example.pessoas.model.Person;
import com.example.pessoas.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Mono<Person> createPerson(Person person) {
        return personRepository.save(person);
    }
}
