
[![Build Result](https://github.com/alessiottavo/reactive-programming-with-reactor-3/actions/workflows/maven.yml/badge.svg)](https://github.com/alessiottavo/reactive-programming-with-reactor-3/actions/workflows/maven.yml)

# Reactive Programming Using Reactor 3

These exercises have been created following the official documentation from [projectreactor.io](https://projectreactor.io/).

Explore the exercises here: [Reactive Programming with Reactor 3](https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/)

Reactive programming is a new paradigm that uses declarative code, similar to functional programming, to build asynchronous processing pipelines. This event-based model pushes data to the consumer as it becomes available. Reactive programming is fully asynchronous and non-blocking.

## Reactive Streams in JVM

Reactive programming in the JVM is based on Reactive Streams, which consist of four interfaces and a Technology Compatibility Kit (TCK):

### Core Interfaces

- **Publisher<T>**: Represents a data source. A `Publisher` emits a sequence of elements of type `T` to subscribers (`Subscriber`).
- **Subscriber<T>**: Represents a data consumer. A `Subscriber` receives data emitted by the `Publisher` and reacts to it.
- **Subscription**: Represents a link between a `Publisher` and a `Subscriber`. It manages flow control, allowing the `Subscriber` to request a specific number of elements from the `Publisher`.
- **Processor<T, R>**: Acts as both a `Publisher` and a `Subscriber`, capable of transforming received elements before passing them to its subscribers.

### Technology Compatibility Kit (TCK)

The TCK is a set of tests to verify the compliance of an implementation with the Reactive Streams specifications. It ensures that different Reactive Streams implementations can interact seamlessly, maintaining predictable and correct behavior.

## Reactor

To simplify the use of Reactive Streams, Reactor provides high-level APIs built on the `Publisher` interface. Reactor introduces the concept of operators, which are linked together to describe a process applied to each data state. Applying an operator returns a new intermediate `Publisher`. At the end of the pipeline, there is a final subscriber.

### Flux

A `Flux<T>` is a `Publisher` in Reactive Streams, enhanced with many operators to orchestrate `Flux` sequences. A `Flux` can emit 0 to n elements of type `<T>` (onNext events) and can either complete or error out (onComplete and onError terminal events). If no terminal event triggers, the `Flux` is infinite.

#### Key Operations

1. **Flux#subscribe()**:
    - Registers a subscriber to receive events from the `Flux`, starting the data flow.

2. **Multicasting Operations (Flux#publish, Flux#publishNext)**:
    - **publish()**: Shares a single subscription among multiple subscribers, transforming a hot stream into a cold one.
    - **publishNext()**: Emits only the next available event to its subscribers.

3. **Materializing a Dedicated Instance of the Pipeline**:
    - Each `subscribe()`, `publish()`, or `publishNext()` call creates a dedicated instance of the data processing pipeline, ensuring independent operation for each subscription or multicasted flow.

4. **Triggering the Data Flow**:
    - Once an instance is created, the data flow is activated, executing defined operations on each element of the stream.

### Mono<T>

A `Mono` is another `Publisher` in Reactive Streams, enhanced with operators for orchestrating `Mono` sequences. It is a specialization of `Flux` that emits at most one element. A `Mono` can be valued, empty, or failed. A `Mono<void>` is equivalent to a completion signal. Like `Flux`, it is used to define asynchronous pipelines.


### StepVerifier

StepVerifier allows subscribing to any publisher and asserting user-defined expectations. It is obtained from its static factory and requires calling `verify()` to trigger the subscription.

## Transformations

### Using `flatMap`

For asynchronous transformations, use `flatMap` which takes a transformation `Function` returning a `Publisher<U>`. This merges all inner publishers into a single output `Flux<U>`.

### Merging Sequences

Merge values from several `Publisher`s into a single `Flux`. To preserve the order of sources, use the `concat` operator.

## Backpressure

Backpressure is a feedback mechanism allowing a `Subscriber` to signal how much data it can process, controlling the `Publisher`'s data emission rate. This is managed at the `Subscription` level.

## Error Handling

Recover from errors using `onErrorReturn`. Handle checked exceptions with a `try-catch` block, transforming them into `RuntimeException`.


## Integration with RxJava and CompletableFuture

Reactor can interoperate with RxJava and `CompletableFuture` using conversion methods that start with `from` (to Reactor) and `to` (from Reactor).

## Reactive to Blocking

To block until a value is available, use `Mono#block()`. This should be avoided in the middle of reactive code to prevent locking the pipeline.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.1/maven-plugin/reference/html/#build-image)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.3.1/reference/htmlsingle/index.html#web.reactive)

This project was created using Spring Initializr.
