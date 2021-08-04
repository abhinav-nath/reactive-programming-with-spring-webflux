package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxTransformTest {

    List<String> names = Arrays.asList("John", "Tony", "Stacy", "Sarah");

    @Test
    public void transformFluxUsingMap_toUpperCase() {

        Flux<String> namesFlux = Flux.fromIterable(names)
                .map(name -> name.toUpperCase())
                .log();

        StepVerifier.create(namesFlux)
                .expectNext("JOHN", "TONY", "STACY", "SARAH")
                .verifyComplete();
    }

    @Test
    public void transformFluxUsingMap_stringLength() {

        Flux<Integer> namesFlux = Flux.fromIterable(names)
                .map(name -> name.length())
                .log();

        StepVerifier.create(namesFlux)
                .expectNext(4, 4, 5, 5)
                .verifyComplete();
    }

    @Test
    public void transformFluxUsingMap_stringLength_repeat() {

        Flux<Integer> namesFlux = Flux.fromIterable(names)
                .map(name -> name.length())
                .repeat(1)    // repeat one more time
                .log();

        StepVerifier.create(namesFlux)
                .expectNext(4, 4, 5, 5)
                .expectNext(4, 4, 5, 5)
                .verifyComplete();
    }

    @Test
    public void transformFluxUsingMapAndFilter() {

        Flux<String> namesFlux = Flux.fromIterable(names)
                .filter(name -> name.length() > 4)
                .map(name -> name.toUpperCase())
                .log();

        StepVerifier.create(namesFlux)
                .expectNext("STACY", "SARAH")
                .verifyComplete();
    }

}
