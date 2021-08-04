package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxFilterTest {

    List<String> names = Arrays.asList("John", "Tony", "Stacy", "Sarah");

    @Test
    public void filterTest() {

        Flux<String> namesFlux = Flux.fromIterable(names)
                .filter(name -> name.startsWith("S"))
                .log();

        StepVerifier.create(namesFlux)
                .expectNext("Stacy", "Sarah")
                .verifyComplete();
    }

}
