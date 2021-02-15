package com.cbh.muonlineguildcommerce.security.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.model.entity.User;
import com.cbh.muonlineguildcommerce.model.repository.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticationFacade {

	private final UserRepository userRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public User getCurrentLoggedInUser() {
		return userRepository.findByEmail(getAuthentication().getName()).orElseThrow(
				() -> new UserNotFound("User with email - " + getAuthentication().getName() + " is not found."));
	}
}
