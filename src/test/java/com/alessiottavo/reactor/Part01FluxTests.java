package com.alessiottavo.reactor;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Part01Flux;

import reactor.test.StepVerifier;

public class Part01FluxTests {

	Part01Flux testee = new Part01Flux();

	@Test
	void expectEmptyFlux() {

		StepVerifier.create(testee.emptyFlux()).verifyComplete();

	}

	@Test
	void expectFooBarFromValues() {

		StepVerifier.create(testee.fooBarFluxFromValues()).expectNext("foo", "bar").verifyComplete();
	}

	@Test
	void expectFooBarFromList() {

		StepVerifier.create(testee.fooBarFluxFromValues()).expectNextSequence(Arrays.asList("foo", "bar"))
				.verifyComplete();
	}

	@Test
	void expectError() {

		StepVerifier.create(testee.errorFlux()).expectError().verify();
	}

	@Test
	void expectCounter() {

		StepVerifier.create(testee.counter()).expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L).verifyComplete();
	}

}
