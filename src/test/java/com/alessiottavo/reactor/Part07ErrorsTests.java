package com.alessiottavo.reactor;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;

import reactor.test.StepVerifier;

public class Part07ErrorsTests {

	ReactiveUserRepository repository = new ReactiveUserRepository(1L);
	Part07Errors testee = new Part07Errors();

	@Test
	void checkedExceptionTest() {

		StepVerifier.create(testee.capitalizeMany(repository.findAll()))
		.expectNextCount(3L)
		.verifyError(Part07Errors.GetOutOfHereException.class);
	}
}
