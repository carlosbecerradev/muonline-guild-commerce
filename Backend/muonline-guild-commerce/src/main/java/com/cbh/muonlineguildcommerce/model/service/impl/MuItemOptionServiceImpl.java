package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.MuItemOptionRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemOptionResponse;
import com.cbh.muonlineguildcommerce.exception.MuItemOptionNotFound;
import com.cbh.muonlineguildcommerce.mapper.MuItemOptionMapper;
import com.cbh.muonlineguildcommerce.model.entity.MuItemOption;
import com.cbh.muonlineguildcommerce.model.repository.MuItemOptionRepository;
import com.cbh.muonlineguildcommerce.model.service.MuItemOptionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MuItemOptionServiceImpl implements MuItemOptionService {

	private final MuItemOptionRepository muItemOptionRepository;
	private final MuItemOptionMapper muItemOptionMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<MuItemOptionResponse> findAll(int page, int size) {
		Page<MuItemOption> entities = muItemOptionRepository.findAll(PageRequest.of(page, size));
		return entities.map(muItemOptionMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public MuItemOptionResponse findOneById(Long id) {
		return muItemOptionMapper.mapEntityToDto(findMuItemOptionById(id));
	}

	@Override
	@Transactional
	public MuItemOptionResponse save(MuItemOptionRequest muItemOptionRequest) {
		return muItemOptionMapper
				.mapEntityToDto(muItemOptionRepository.saveAndFlush(muItemOptionMapper.mapToSave(muItemOptionRequest)));
	}

	@Override
	@Transactional
	public MuItemOptionResponse edit(MuItemOptionRequest muItemOptionRequest, Long id) {
		MuItemOption muItemOption = findMuItemOptionById(id);
		muItemOption = muItemOptionRepository.save(muItemOptionMapper.mapToEdit(muItemOptionRequest, muItemOption));
		return muItemOptionMapper.mapEntityToDto(muItemOption);
	}

	@Override
	@Transactional(readOnly = true)
	public MuItemOption findMuItemOptionById(Long id) {
		return muItemOptionRepository.findById(id)
				.orElseThrow(() -> new MuItemOptionNotFound("Mu Item Option with ID - " + id + " is not found"));
	}

}
