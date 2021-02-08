package com.cbh.muonlineguildcommerce.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MuItemResponse {
	private Long id;
	private String name;
	private Boolean enabled;
	private Instant createdDate;
	private MuItemCategoryResponse muItemCategory;
}
