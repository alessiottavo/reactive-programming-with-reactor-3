package com.alessiottavo.reactor;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;
import com.alessiottavo.reactor.Repository.Entity.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Part05MergeTests {
	
	Part05Merge testee = new Part05Merge();
	ReactiveUserRepository repository = new ReactiveUserRepository(10L);
	ReactiveUserRepository repositoryWithLatency = new ReactiveUserRepository(1000L);
	
	@Test
	void expectFluxNoOrder() {
		
		repository.save(Mono.just(new User("new", "new", "new"))).block();
		List<User> users = repository.findAll().collect(Collectors.toList()).block();
		
		StepVerifier.create(testee.mergeFluxWithInterleave(repositoryWithLatency.findAll(), repository.findAll()))
		.expectSubscription()
		.expectNext(users.get(0))
		.expectNext(users.get(1))
		.expectNext(users.get(2))
		.expectNext(users.get(3))
		.expectNext(users.get(4))
		.expectNext(users.get(0))
		.expectNext(users.get(1))
		.expectNext(users.get(2))
		.expectNext(users.get(3))
		.verifyComplete();
	}
	
	@Test
	void expectFluxInOrder() {
		
		repository.save(Mono.just(new User("new", "new", "new"))).block();
		List<User> users = repository.findAll().collect(Collectors.toList()).block();
		
		StepVerifier.create(testee.mergeFluxWithNoInterleave(repository.findAll(), repositoryWithLatency.findAll()))
		.expectSubscription()
		.expectNext(users.get(0))
		.expectNext(users.get(1))
		.expectNext(users.get(2))
		.expectNext(users.get(3))
		.expectNext(users.get(4))
		.expectNext(users.get(0))
		.expectNext(users.get(1))
		.expectNext(users.get(2))
		.expectNext(users.get(3))
		.verifyComplete();
	}


}
