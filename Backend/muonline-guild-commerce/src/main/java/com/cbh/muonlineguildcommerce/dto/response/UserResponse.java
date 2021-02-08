package com.cbh.muonlineguildcommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private Long id;
	private String nickname;
	private String contact;
	private Boolean enabled;
}
