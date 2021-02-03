package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.MuItemCategoryRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemCategoryResponse;

public interface MuItemCategoryService {
	public Page<MuItemCategoryResponse> findAll(int page, int size);

	public MuItemCategoryResponse findOneById(Long id);

	public MuItemCategoryResponse save(MuItemCategoryRequest muItemCategoryRequest);

	public MuItemCategoryResponse edit(MuItemCategoryRequest muItemCategoryRequest, Long id);

	public boolean validateUniqueConstraints(MuItemCategoryRequest muItemCategoryRequest);
}
