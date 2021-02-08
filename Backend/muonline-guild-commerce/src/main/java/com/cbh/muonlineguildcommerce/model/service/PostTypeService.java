package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.PostTypeRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.model.entity.PostType;

public interface PostTypeService {
	public Page<PostTypeResponse> findAll(int page, int size);

	public PostTypeResponse findOneById(Long id);

	public PostTypeResponse save(PostTypeRequest postTypeRequest);

	public PostTypeResponse edit(PostTypeRequest postTypeRequest, Long id);

	public PostType findPostTypeById(Long id);
}
