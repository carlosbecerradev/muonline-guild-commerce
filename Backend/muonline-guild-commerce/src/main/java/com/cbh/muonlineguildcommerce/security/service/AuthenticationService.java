package com.cbh.muonlineguildcommerce.security.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cbh.muonlineguildcommerce.dto.request.LoginRequest;
import com.cbh.muonlineguildcommerce.dto.response.AuthenticationSuccessfulResponse;
import com.cbh.muonlineguildcommerce.security.provider.AuthenticationFacade;
import com.cbh.muonlineguildcommerce.security.provider.JWTProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final JWTProvider jwtProvider;
	private final AuthenticationFacade authenticationFacade;

	public AuthenticationSuccessfulResponse login(LoginRequest loginRequest) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);

			final long CURRENT_DATE_IN_SECONDS = Instant.now().getEpochSecond();
			final long SECONDS_UNTIL_EXPIRES = Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond()
					- CURRENT_DATE_IN_SECONDS;

			AuthenticationSuccessfulResponse response = new AuthenticationSuccessfulResponse();
			response.setAccessToken(jwtProvider.generateJWT(auth));
			response.setCreatedAt(CURRENT_DATE_IN_SECONDS);
			response.setExpiresIn(SECONDS_UNTIL_EXPIRES);
			response.setTokenType("bearer");

			String nickname = authenticationFacade.getCurrentLoggedInUser().getNickname();
			response.setNickname(nickname);

			return response;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Map<String, Object> checkAccessToken() {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		String nickname = authenticationFacade.getCurrentLoggedInUser().getNickname();
		response.put("nickname", nickname);
		return response;
	}

}
