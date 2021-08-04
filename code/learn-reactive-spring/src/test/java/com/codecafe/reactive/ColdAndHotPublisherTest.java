package com.codecafe.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

public class ColdAndHotPublisherTest {

    @Test
    public void coldPublisherTest() throws InterruptedException {

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
                .delayElements(Duration.ofSeconds(1));

        stringFlux.subscribe(s -> System.out.println("Subscriber 1 : " + s)); // publisher emits the value from beginning

        Thread.sleep(1000);

        stringFlux.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // publisher emits the value from beginning

        Thread.sleep(4000);
    }

    @Test
    public void hotPublisherTest() throws InterruptedException {

        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<String> connectableFlux = stringFlux.publish();
        connectableFlux.connect();

        connectableFlux.subscribe(s -> System.out.println("Subscriber 1 : " + s)); // publisher emits the value from beginning

        Thread.sleep(1000);

        connectableFlux.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // publisher emits the value from the time when subscriber2 got subscribed

        Thread.sleep(4000);
    }

}