package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.MuItemRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItem;

public interface MuItemService {
	public Page<MuItemResponse> findAll(int page, int size);

	public MuItemResponse findOneById(Long id);

	public MuItemResponse save(MuItemRequest muItemRequest);

	public MuItemResponse edit(MuItemRequest muItemRequest, Long id);

	public MuItem findMuItemById(Long id);
}
