package com.cbh.muonlineguildcommerce.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationSuccessfulResponse {
	private String accessToken;
	private Instant createdAt;
	private Instant expiresIn;
	private String tokenType;
}
