import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FluxWithTimeTest {

    @Test
    public void generateInfiniteSequence() throws InterruptedException {

        Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(100))
                .log();// starts from 0 to infinity

        infiniteFlux
                .subscribe((element) -> System.out.println("Value is : " + element));

        Thread.sleep(1000);
    }

    @Test
    public void finiteSequenceTest() {

        Flux<Long> finiteFlux = Flux.interval(Duration.ofMillis(500))
                .take(5)
                .log();

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .expectNext(0L, 1L, 2L, 3L, 4L)
                .verifyComplete();
    }

}
