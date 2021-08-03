package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

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

}