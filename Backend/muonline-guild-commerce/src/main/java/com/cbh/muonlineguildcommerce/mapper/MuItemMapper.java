package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.MuItemRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItem;
import com.cbh.muonlineguildcommerce.model.entity.MuItemCategory;
import com.cbh.muonlineguildcommerce.model.service.MuItemCategoryService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MuItemMapper {

	private final MuItemCategoryMapper muItemCategoryMapper;
	private final MuItemCategoryService muItemCategoryService;

	public MuItemResponse mapEntityToDto(MuItem muItem) {
		MuItemResponse muItemResponse = new MuItemResponse(muItem.getId(), muItem.getName(), muItem.getEnabled(),
				muItem.getCreatedDate(), muItemCategoryMapper.mapEntityToDto(muItem.getMuItemCategory()));
		return muItemResponse;
	}

	public MuItem mapToSave(MuItemRequest muItemRequest) {
		MuItemCategory muItemCategory = muItemCategoryService
				.findMuItemCategoryById(muItemRequest.getMuItemCategoryId());
		MuItem muItem = new MuItem(null, muItemRequest.getName(), true, Instant.now(), muItemCategory);
		return muItem;
	}

	public MuItem mapToEdit(MuItemRequest muItemRequest, MuItem muItem) {
		MuItemCategory muItemCategory = muItemCategoryService
				.findMuItemCategoryById(muItemRequest.getMuItemCategoryId());
		muItem = new MuItem(muItem.getId(), muItemRequest.getName(), muItem.getEnabled(), muItem.getCreatedDate(),
				muItemCategory);
		return muItem;
	}
}
