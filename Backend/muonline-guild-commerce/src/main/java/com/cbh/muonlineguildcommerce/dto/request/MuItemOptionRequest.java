package com.cbh.muonlineguildcommerce.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MuItemOptionRequest {
	@NotBlank
	@Size(min = 1, max = 60, message = "Name should be between 1 and 60 characters")
	private String name;
}
