package com.codecafe.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class FirstReactiveController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux() {

        return Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> returnFluxStream() {

        return Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

}