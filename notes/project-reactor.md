# Project Reactor

https://projectreactor.io/

* reactor-core
  - core library for Project Reactor
  - requires Java 8 (minimum)
  - Flux and Mono are the Reactive Types of the Project Reactor
* reactor-test
* reactor-netty


## [Flux](Flux "https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html")

```java
public abstract class Flux<T>
extends Object
implements CorePublisher<T>
```

* A Reactive Streams Publisher with rx operators that emits 0 to N elements, and then completes (successfully or with an error).

* Flux represents 0 to N elements.

![Flux](./images/flux.png)

## Mono

```java
public abstract class Mono<T>
extends Object
implements CorePublisher<T>
```

* A Reactive Streams Publisher with basic rx operators that emits at most one item via the onNext signal then terminates with an onComplete signal (successful Mono, with or without value), or only emits a single onError signal (failed Mono).
* Mono represents 0 to 1 elements.

![Flux](./images/mono.png)