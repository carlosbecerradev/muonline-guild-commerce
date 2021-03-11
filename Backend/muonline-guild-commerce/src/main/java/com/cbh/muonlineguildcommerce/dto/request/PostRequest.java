package com.cbh.muonlineguildcommerce.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
	@Positive
	private Long muServerId;
	@Positive
	private Long muItemId;
	@Positive
	private Long muItemOptionId;
	@Positive
	private Long postTypeId;
	@Min(value = 0)
	private Byte itemLevel;
	@Size(max = 255, message = "Name should be between 0 and 255 characters")
	private String observation;
}
