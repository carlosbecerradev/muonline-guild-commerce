package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.MuServerRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuServerResponse;
import com.cbh.muonlineguildcommerce.exception.MuServerNotFound;
import com.cbh.muonlineguildcommerce.mapper.MuServerMapper;
import com.cbh.muonlineguildcommerce.model.entity.MuServer;
import com.cbh.muonlineguildcommerce.model.repository.MuServerRepository;
import com.cbh.muonlineguildcommerce.model.service.MuServerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MuServerServiceImpl implements MuServerService {

	private final MuServerRepository muServerRepository;
	private final MuServerMapper muServerMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<MuServerResponse> findAll(int page, int size) {
		Page<MuServer> entities = muServerRepository.findAll(PageRequest.of(page, size));
		return entities.map(muServerMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public MuServerResponse findOneById(Long id) {
		return muServerMapper.mapEntityToDto(findMuServerById(id));
	}

	@Override
	@Transactional
	public MuServerResponse save(MuServerRequest muServerRequest) {
		MuServer muServer = muServerRepository.saveAndFlush(muServerMapper.mapToSave(muServerRequest));
		return muServerMapper.mapEntityToDto(muServer);
	}

	@Override
	@Transactional
	public MuServerResponse edit(MuServerRequest muServerRequest, Long id) {
		MuServer muServer = findMuServerById(id);
		muServer = muServerRepository.save(muServerMapper.mapToEdit(muServerRequest, muServer));
		return muServerMapper.mapEntityToDto(muServer);
	}

	@Override
	@Transactional(readOnly = true)
	public MuServer findMuServerById(Long id) {
		return muServerRepository.findById(id)
				.orElseThrow(() -> new MuServerNotFound("Mu Server with ID - " + id + " is not found"));
	}

}
