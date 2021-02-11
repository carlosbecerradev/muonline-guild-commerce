package com.cbh.muonlineguildcommerce.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String password;
}
