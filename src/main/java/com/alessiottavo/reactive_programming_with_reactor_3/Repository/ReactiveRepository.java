package com.alessiottavo.reactive_programming_with_reactor_3.Repository;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveRepository<T> {
	

	Mono<Void> save(Publisher<T> publisher);

	Mono<T> findFirst();

	Flux<T> findAll();

	Mono<T> findById(String id);

}
