package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.PostTypeRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.model.entity.PostType;

@Component
public class PostTypeMapper {

	public PostTypeResponse mapEntityToDto(PostType postType) {
		PostTypeResponse postTypeResponse = new PostTypeResponse(postType.getId(), postType.getName(),
				postType.getEnabled());
		return postTypeResponse;
	}

	public PostType mapToSave(PostTypeRequest postTypeRequest) {
		PostType postType = new PostType(null, postTypeRequest.getName(), true, Instant.now());
		return postType;
	}

	public PostType mapToEdit(PostTypeRequest postTypeRequest, PostType postType) {
		postType.setName(postTypeRequest.getName());
		return postType;
	}
}
