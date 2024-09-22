package com.example.webclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CatFactService {

    private final WebClient webClient;

    public CatFactService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://cat-fact.herokuapp.com").build();
    }

    public Flux<String> getCatFacts() {
        return this.webClient.get()
                .uri("/facts")
                .retrieve()
                .bodyToFlux(CatFact.class)
                .map(CatFact::getText);
    }

    public static class CatFact {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
