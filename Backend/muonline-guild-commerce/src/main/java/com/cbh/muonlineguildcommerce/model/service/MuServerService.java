package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.MuServerRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuServerResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuServer;

public interface MuServerService {
	public Page<MuServerResponse> findAll(int page, int size);

	public MuServerResponse findOneById(Long id);

	public MuServerResponse save(MuServerRequest muServerRequest);

	public MuServerResponse edit(MuServerRequest muServerRequest, Long id);

	public void deleteById(Long id);

	public MuServer findMuServerById(Long id);
}
