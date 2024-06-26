package com.alessiottavo.reactor;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;

import reactor.test.StepVerifier;

public class Part04TransofrmTests {

	Part04Transform testee = new Part04Transform();
	ReactiveUserRepository repository = new ReactiveUserRepository(1);

	@Test
	void expectOneUppercase() {

		StepVerifier.create(testee.capitalizeOne(repository.findFirst()))
				.assertNext(s -> s.getFirstname().matches("A-Z")).verifyComplete();
	}

	@Test
	void expectManyUppercase() {

		StepVerifier.create(testee.capitalizeMany(repository.findAll()))
				.assertNext(s -> s.getFirstname().matches("A-Z")).assertNext(s -> s.getFirstname().matches("A-Z"))
				.assertNext(s -> s.getFirstname().matches("A-Z")).assertNext(s -> s.getFirstname().matches("A-Z"))
				.verifyComplete();
	}

	@Test
	void asyncExpectManyUppercase() {

		StepVerifier.create(testee.asyncCapitalizeMany(repository.findAll()))
				.assertNext(s -> s.getFirstname().matches("A-Z")).assertNext(s -> s.getFirstname().matches("A-Z"))
				.assertNext(s -> s.getFirstname().matches("A-Z")).assertNext(s -> s.getFirstname().matches("A-Z"))
				.verifyComplete();
	}

}
