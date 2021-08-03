package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void fluxTest() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();

        // the only way to access elements from Flux is by subscribing to it

        // attach a subscriber to the Flux
        stringFlux.subscribe(System.out::println);
    }

    @Test
    public void fluxErrorTest() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        stringFlux.subscribe(System.out::println,
                e -> System.err.println(e));
    }

    @Test
    public void fluxTestElements_WithoutError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElements_WithError1() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void fluxTestElements_WithErrorMessage() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

    @Test
    public void fluxTestElementsCount_withError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Exception Occurred")
                .verify();
    }

}