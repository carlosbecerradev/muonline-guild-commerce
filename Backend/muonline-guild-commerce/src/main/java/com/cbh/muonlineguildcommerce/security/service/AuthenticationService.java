package com.cbh.muonlineguildcommerce.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cbh.muonlineguildcommerce.dto.request.LoginRequest;
import com.cbh.muonlineguildcommerce.dto.response.AuthenticationSuccessfulResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;

	public AuthenticationSuccessfulResponse login(LoginRequest loginRequest) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			;

			AuthenticationSuccessfulResponse authenticationResponse = new AuthenticationSuccessfulResponse();

			return authenticationResponse;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
