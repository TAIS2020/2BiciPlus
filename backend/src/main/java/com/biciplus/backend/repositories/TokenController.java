package com.biciplus.backend.repositories;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biciplus.backend.model.Person;
import com.biciplus.backend.services.AuthenticationService;

@RestController
@RequestMapping("token")
public class TokenController<T extends Person> {
	@Autowired
	private AuthenticationService<T> autheService;

	@RequestMapping(method = RequestMethod.POST)
	public String personPost(@RequestParam("username") final String username,
			@RequestParam("password") final String password) {
		String token = autheService.login(username, password);
		if (StringUtils.isEmpty(token)) {
			return "no token found";
		}
		return token;
	}
}
