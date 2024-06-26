package com.alessiottavo.reactor;

import java.time.Duration;
import java.util.function.Supplier;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;
import com.alessiottavo.reactor.Repository.Entity.User;

import reactor.core.publisher.Flux;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of
 * Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href=
 *      "https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier
 *      Javadoc</a>
 */
public class Part03StepVerifier {
	ReactiveUserRepository userRepository = new ReactiveUserRepository(1000);

//========================================================================================

	// TODO Use StepVerifier to check that the flux parameter emits a User with
	// "swhite"username
	// and another one with "jpinkman" then completes successfully.
	Flux<User> skylerJesseComplete() {
		return userRepository.findById("swhite").concatWith(userRepository.findById("jpinkman"));
	}

//========================================================================================

	// TODO Expect 3600 elements at intervals of 1 second, and verify quicker than
	// 3600s
	// by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice
	// how long the test takes
	Flux<Long> flux3600Elements() {
		return Flux.interval(Duration.ofMillis(1000L)).take(3600L);
	}
}
