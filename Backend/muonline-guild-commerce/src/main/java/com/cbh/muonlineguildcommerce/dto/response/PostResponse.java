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
public class PostResponse {
	private Long id;
	private MuServerResponse muServer;
	private UserResponse user;
	private MuItemResponse muItem;
	private MuItemOptionResponse muItemOption;
	private PostTypeResponse postType;
	private Byte itemLevel;
	private String observation;
	private Boolean enabled;
	private Instant createdDate;
}
