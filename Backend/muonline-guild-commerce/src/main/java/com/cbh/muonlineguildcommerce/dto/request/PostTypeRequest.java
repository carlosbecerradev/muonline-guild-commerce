package com.cbh.muonlineguildcommerce.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostTypeRequest {
	@NotBlank
	@Size(min = 1, max = 16, message = "Name should be between 1 and 16 characters")
	private String name;
}
