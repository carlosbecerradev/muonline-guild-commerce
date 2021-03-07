package com.cbh.muonlineguildcommerce.model.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.PostTypeRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.exception.PostTypeNotFound;
import com.cbh.muonlineguildcommerce.mapper.PostTypeMapper;
import com.cbh.muonlineguildcommerce.model.entity.PostType;
import com.cbh.muonlineguildcommerce.model.repository.PostTypeRepository;
import com.cbh.muonlineguildcommerce.model.service.PostTypeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostTypeServiceImpl implements PostTypeService {

	private final PostTypeRepository postTypeRepository;
	private final PostTypeMapper postTypeMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<PostTypeResponse> findAll(int page, int size) {
		Page<PostType> entities = postTypeRepository.findAll(PageRequest.of(page, size));
		return entities.map(postTypeMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public PostTypeResponse findOneById(Long id) {
		PostType postType = findPostTypeById(id);
		return postTypeMapper.mapEntityToDto(postType);
	}

	@Override
	@Transactional
	public PostTypeResponse save(PostTypeRequest postTypeRequest) {
		PostType postType = postTypeRepository.saveAndFlush(postTypeMapper.mapToSave(postTypeRequest));
		return postTypeMapper.mapEntityToDto(postType);
	}

	@Override
	@Transactional
	public PostTypeResponse edit(PostTypeRequest postTypeRequest, Long id) {
		PostType postType = findPostTypeById(id);
		postType = postTypeRepository.save(postTypeMapper.mapToEdit(postTypeRequest, postType));
		return postTypeMapper.mapEntityToDto(postType);
	}

	@Override
	@Transactional(readOnly = true)
	public PostType findPostTypeById(Long id) {
		return postTypeRepository.findById(id)
				.orElseThrow(() -> new PostTypeNotFound("Post Type with ID - " + id + " is not found."));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		postTypeRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PostTypeResponse> findAll() {
		return postTypeRepository.findByEnabledTrue().stream().map(postTypeMapper::mapEntityToDto)
				.collect(Collectors.toList());
	}

}
