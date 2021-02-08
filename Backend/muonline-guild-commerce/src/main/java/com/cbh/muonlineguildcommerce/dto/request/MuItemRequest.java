package com.cbh.muonlineguildcommerce.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MuItemRequest {
	@NotBlank
	@Size(min = 1, message = "Name should be between 1 and 255 characters")
	private String name;
	@Positive
	private Long muItemCategoryId;
}
