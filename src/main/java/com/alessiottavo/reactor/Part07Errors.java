package com.alessiottavo.reactor;

import com.alessiottavo.reactor.Repository.Entity.User;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

public class Part07Errors {
	
	// TODO Implement a method that capitalizes each user of the incoming flux using the
	// #capitalizeUser method and emits an error containing a GetOutOfHereException error
	Flux<User> capitalizeMany(Flux<User> flux) {

		return flux.map(s -> {
			try {
				return capitalizeUser(s);
			} catch (GetOutOfHereException e) {
				throw Exceptions.propagate(e);
			}
		});
	}

	User capitalizeUser(User user) throws GetOutOfHereException {
		if (user.equals(User.SAUL)) {
			throw new GetOutOfHereException();
		}
		return new User(user.getUsername(), user.getFirstname(), user.getLastname());
	}

	protected final class GetOutOfHereException extends Exception {
		private static final long serialVersionUID = 0L;
	}
}
