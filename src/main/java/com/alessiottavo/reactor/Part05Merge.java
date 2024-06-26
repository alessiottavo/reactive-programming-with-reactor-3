package com.alessiottavo.reactor;

import com.alessiottavo.reactor.Repository.Entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part05Merge {

	Flux<User> mergeFluxWithInterleave(Flux<User> f1, Flux<User> f2) {

		return Flux.merge(f1, f2);
	}

	Flux<User> mergeFluxWithNoInterleave(Flux<User> f1, Flux<User> f2) {

		return Flux.concat(f1, f2);
	}

	Flux<User> mergeFluxWithInterleave(Mono<User> f1, Mono<User> f2) {

		return Flux.merge(f1, f2);
	}
}
