package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.request.PostRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.model.entity.Post;

public interface PostService {
	public Page<PostResponse> findAll(int page, int size);

	public PostResponse findOneById(Long id);

	public PostResponse save(PostRequest postRequest);

	public PostResponse edit(PostRequest postRequest, Long id);

	public void deleteById(Long id);

	public Post findPostById(Long id);
}
