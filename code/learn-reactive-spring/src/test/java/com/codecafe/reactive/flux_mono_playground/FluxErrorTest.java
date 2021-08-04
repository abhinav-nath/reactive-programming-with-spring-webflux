package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class FluxErrorTest {

    @Test
    public void fluxErrorHandling_using_onErrorResume() {

        Flux<String> stringFlux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .concatWith(Flux.just("D")) // will not be emitted because exception occurred already
                .onErrorResume((e) -> {  // gets executed in case of error
                    System.out.println("Exception is : " + e);
                    return Flux.just("default1", "default2");
                });

        StepVerifier.create(stringFlux.log())
                .expectSubscription()
                .expectNext("A", "B", "C")
                .expectNext("default1", "default2")
                .verifyComplete();
    }

    @Test
    public void fluxErrorHandling_using_onErrorReturn() {

        Flux<String> stringFlux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .onErrorReturn("default"); // provide a fallback value

        StepVerifier.create(stringFlux.log())
                .expectSubscription()
                .expectNext("A", "B", "C")
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    public void fluxErrorHandling_using_onErrorMap() {

        Flux<String> stringFlux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .onErrorMap((e) -> new CustomException(e)); // convert an exception from one type to another type

        StepVerifier.create(stringFlux.log())
                .expectSubscription()
                .expectNext("A", "B", "C")
                .expectError(CustomException.class)
                .verify();
    }

    @Test
    public void fluxErrorHandling_using_onErrorMap_with_retry() {

        Flux<String> stringFlux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .onErrorMap((e) -> new CustomException(e)) // convert an exception from one type to another type
                .retry(2);

        StepVerifier.create(stringFlux.log())
                .expectSubscription()
                .expectNext("A", "B", "C")
                .expectNext("A", "B", "C")
                .expectNext("A", "B", "C")
                .expectError(CustomException.class)
                .verify();
    }

}
