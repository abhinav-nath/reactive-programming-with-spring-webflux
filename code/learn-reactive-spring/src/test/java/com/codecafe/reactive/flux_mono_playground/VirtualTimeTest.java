package com.codecafe.reactive.flux_mono_playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class VirtualTimeTest {

    @Test
    public void testWithoutVirtualTime() {

        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1))
                .take(5);

        // it takes 5 seconds to execute this test
        // we can use virtual time in this case

        StepVerifier.create(longFlux.log())
                .expectSubscription()
                .expectNext(0L, 1L, 2L, 3L, 4L)
                .verifyComplete();
    }

    @Test
    public void testWithVirtualTime() {

        VirtualTimeScheduler.getOrSet();

        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1))
                .take(5);

        StepVerifier.withVirtualTime(() -> longFlux.log())
                .expectSubscription()
                .thenAwait(Duration.ofSeconds(5))
                .expectNext(0L, 1L, 2L, 3L, 4L)
                .verifyComplete();
    }

}