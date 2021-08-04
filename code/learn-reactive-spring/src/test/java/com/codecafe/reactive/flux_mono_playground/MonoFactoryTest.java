package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Supplier;

public class MonoFactoryTest {

    @Test
    public void createMonoUsingJustOrEmpty() {

        Mono<String> mono = Mono.justOrEmpty(null);// Mono.Empty()

        StepVerifier.create(mono.log())
                .verifyComplete();
    }

    @Test
    public void createMonoUsingSupplier() {

        Supplier<String> fruitSupplier = () -> "Apple";

        Mono<String> fruitMono = Mono.fromSupplier(fruitSupplier);

        StepVerifier.create(fruitMono.log())
                .expectNext("Apple")
                .verifyComplete();
    }

}
