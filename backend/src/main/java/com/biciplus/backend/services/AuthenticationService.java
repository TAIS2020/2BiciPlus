package com.biciplus.backend.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.biciplus.backend.model.Person;
import com.biciplus.backend.repositories.PersonRepository;

@Service("authenticactionService")
public class AuthenticationService<T extends Person> {
	@Autowired
	PersonRepository<T> personRepository;

	public String login(String username, String password) {
		T person = personRepository.login(username, password);
		if (person != null) {
			String token = UUID.randomUUID().toString();
			person.setToken(token);
			personRepository.save(person);
			return token;
		}

		return "";
	}

	public Optional<User> findByToken(String token) {
		Optional<T> optionalPerson = personRepository.findByToken(token);
		if (optionalPerson.isPresent()) {
			Person person = optionalPerson.get();
			User user = new User(person.getLogin(), person.getPassword(), true, true, true, true,
					AuthorityUtils.createAuthorityList(person.getType()));
			return Optional.of(user);
		}
		return Optional.empty();
	}
}
