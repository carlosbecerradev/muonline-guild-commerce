package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.MuItemCategoryRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemCategoryResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItemCategory;

@Component
public class MuItemCategoryMapper {

	public MuItemCategoryResponse mapEntityToDto(MuItemCategory muItemCategory) {
		MuItemCategoryResponse dto = new MuItemCategoryResponse(muItemCategory.getId(), muItemCategory.getName(),
				muItemCategory.getEnabled(), muItemCategory.getCreatedDate());
		return dto;
	}

	public MuItemCategory mapToSave(MuItemCategoryRequest MuItemCategoryRequest) {
		MuItemCategory entity = new MuItemCategory(null, MuItemCategoryRequest.getName(), true, Instant.now());
		return entity;
	}

	public MuItemCategory mapToEdit(MuItemCategoryRequest MuItemCategoryRequest, MuItemCategory muItemCategory) {
		muItemCategory.setName(MuItemCategoryRequest.getName());
		return muItemCategory;
	}
}
