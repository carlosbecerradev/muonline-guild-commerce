package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.PostRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.exception.PostNotFound;
import com.cbh.muonlineguildcommerce.mapper.PostMapper;
import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.repository.PostRepository;
import com.cbh.muonlineguildcommerce.model.service.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findAll(int page, int size) {
		Page<Post> entities = postRepository.findAll(PageRequest.of(page, size));
		return entities.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public PostResponse findOneById(Long id) {
		Post post = findPostById(id);
		return postMapper.mapEntityToDto(post);
	}

	@Override
	@Transactional
	public PostResponse save(PostRequest postRequest) {
		Post post = postMapper.mapToSave(postRequest);
		return postMapper.mapEntityToDto(postRepository.saveAndFlush(post));
	}

	@Override
	@Transactional
	public PostResponse edit(PostRequest postRequest, Long id) {
		Post post = findPostById(id);
		post = postMapper.mapToEdit(postRequest, post);
		return postMapper.mapEntityToDto(postRepository.save(post));
	}

	@Override
	@Transactional(readOnly = true)
	public Post findPostById(Long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new PostNotFound("Post with ID - " + id + " is not found"));
	}

}
