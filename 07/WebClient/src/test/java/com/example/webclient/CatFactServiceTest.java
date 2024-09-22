package com.example.webclient;

import com.example.webclient.service.CatFactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@Testcontainers
public class CatFactServiceTest {

    @Autowired
    private CatFactService catFactService;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void testGetCatFacts() {
        Flux<String> facts = catFactService.getCatFacts();

        StepVerifier.create(facts)
                .expectNextMatches(fact -> fact.contains("cat"))
                .thenCancel()
                .verify();
    }

}
