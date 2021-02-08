package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.MuItemOptionRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemOptionResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItemOption;

public interface MuItemOptionService {
	public Page<MuItemOptionResponse> findAll(int page, int size);

	public MuItemOptionResponse findOneById(Long id);

	public MuItemOptionResponse save(MuItemOptionRequest muItemOptionRequest);

	public MuItemOptionResponse edit(MuItemOptionRequest muItemOptionRequest, Long id);

	public MuItemOption findMuItemOptionById(Long id);
}
