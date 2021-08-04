package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static reactor.core.scheduler.Schedulers.parallel;

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

    @Test
    public void transformFluxUsingFlatMap() {

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
                .flatMap(e -> {
                    return Flux.fromIterable(convertToList(e));
                }) // db call or external service call that returns a flux
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(12)
                .verifyComplete();
    }

    private List<String> convertToList(String str) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Arrays.asList(str, "newValue");
    }

    @Test
    public void transformFluxUsingFlatMap_inParallel() {

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
                .window(2) // Flux<Flux<String>>
                .flatMap((s) ->
                        s.map(this::convertToList).subscribeOn(parallel()))
                .flatMap(s -> Flux.fromIterable(s))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(12)
                .verifyComplete();
    }

    @Test
    public void transformFluxUsingFlatMap_parallel_maintainOrder() {

        // Use flatMapSequential() to maintain order

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
                .window(2) // Flux<Flux<String>>
                .flatMapSequential((s) ->
                        s.map(this::convertToList).subscribeOn(parallel()))
                .flatMap(s -> Flux.fromIterable(s))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(12)
                .verifyComplete();
    }

}
