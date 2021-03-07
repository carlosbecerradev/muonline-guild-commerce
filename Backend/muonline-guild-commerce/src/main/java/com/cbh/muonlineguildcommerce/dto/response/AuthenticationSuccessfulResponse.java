package com.cbh.muonlineguildcommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationSuccessfulResponse {
	private String accessToken;
	private String nickname;
	private Long createdAt;
	private Long expiresIn;
	private String tokenType;
}
