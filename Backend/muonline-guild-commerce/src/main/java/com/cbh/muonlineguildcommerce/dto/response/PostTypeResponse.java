package com.cbh.muonlineguildcommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostTypeResponse {
	private Long id;
	private String name;
	private Boolean enabled;
}
