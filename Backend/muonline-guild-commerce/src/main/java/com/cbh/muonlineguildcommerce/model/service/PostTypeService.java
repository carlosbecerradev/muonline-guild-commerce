package com.cbh.muonlineguildcommerce.model.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.PostTypeRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.model.entity.PostType;

public interface PostTypeService {
	public Page<PostTypeResponse> findAll(int page, int size);

	public PostTypeResponse findOneById(Long id);

	public PostTypeResponse save(PostTypeRequest postTypeRequest);

	public PostTypeResponse edit(PostTypeRequest postTypeRequest, Long id);

	public void deleteById(Long id);

	public PostType findPostTypeById(Long id);

	public List<PostTypeResponse> findAll();
}
