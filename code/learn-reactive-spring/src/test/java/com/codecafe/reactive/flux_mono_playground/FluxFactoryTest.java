package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxFactoryTest {

    @Test
    public void createFluxUsingIterable() {

        List<String> names = Arrays.asList("John", "Tony", "Stacy", "Sarah");

        Flux<String> namesFlux = Flux.fromIterable(names);

        StepVerifier.create(namesFlux.log())
                .expectNext("John", "Tony", "Stacy", "Sarah")
                .verifyComplete();
    }

    @Test
    public void createFluxUsingArray() {

        String[] fruits = new String[]{"Apple", "Mango", "Cherry", "Banana"};

        Flux<String> fruitsFlux = Flux.fromArray(fruits);

        StepVerifier.create(fruitsFlux.log())
                .expectNext("Apple", "Mango", "Cherry", "Banana")
                .verifyComplete();
    }

    @Test
    public void createFluxUsingStream() {

        List<String> cities = Arrays.asList("Bangalore", "Pune", "Mumbai", "Delhi");

        Flux<String> citiesFlux = Flux.fromStream(cities.stream());

        StepVerifier.create(citiesFlux.log())
                .expectNext("Bangalore", "Pune", "Mumbai", "Delhi")
                .verifyComplete();
    }

    @Test
    public void createFluxUsingRange() {

        Flux<Integer> integerFlux = Flux.range(1, 10);

        StepVerifier.create(integerFlux.log())
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }

}