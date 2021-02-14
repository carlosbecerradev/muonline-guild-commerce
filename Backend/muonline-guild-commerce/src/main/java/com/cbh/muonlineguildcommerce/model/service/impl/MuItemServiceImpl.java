package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.MuItemRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemResponse;
import com.cbh.muonlineguildcommerce.exception.MuItemNotFound;
import com.cbh.muonlineguildcommerce.mapper.MuItemMapper;
import com.cbh.muonlineguildcommerce.model.entity.MuItem;
import com.cbh.muonlineguildcommerce.model.repository.MuItemRepository;
import com.cbh.muonlineguildcommerce.model.service.MuItemService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MuItemServiceImpl implements MuItemService {

	private final MuItemRepository muItemRepository;
	private final MuItemMapper muItemMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<MuItemResponse> findAll(int page, int size) {
		Page<MuItem> entities = muItemRepository.findAll(PageRequest.of(page, size));
		return entities.map(muItemMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public MuItemResponse findOneById(Long id) {
		MuItem muItem = findMuItemById(id);
		return muItemMapper.mapEntityToDto(muItem);
	}

	@Override
	@Transactional
	public MuItemResponse save(MuItemRequest muItemRequest) {
		MuItem muItem = muItemRepository.saveAndFlush(muItemMapper.mapToSave(muItemRequest));
		return muItemMapper.mapEntityToDto(muItem);
	}

	@Override
	@Transactional
	public MuItemResponse edit(MuItemRequest muItemRequest, Long id) {
		MuItem muItem = findMuItemById(id);
		muItem = muItemRepository.save(muItemMapper.mapToEdit(muItemRequest, muItem));
		return muItemMapper.mapEntityToDto(muItem);
	}

	@Override
	@Transactional(readOnly = true)
	public MuItem findMuItemById(Long id) {
		return muItemRepository.findById(id)
				.orElseThrow(() -> new MuItemNotFound("Mu Item with ID - " + id + " is not found"));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		muItemRepository.deleteById(id);
	}

}
