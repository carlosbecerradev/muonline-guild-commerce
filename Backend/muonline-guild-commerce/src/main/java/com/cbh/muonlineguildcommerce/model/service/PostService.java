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

	public boolean isCurrentLoggedInUserPost(Post post);

	public Page<PostResponse> findByEnabledAndMuServerId(boolean enabled, Long muServerId, int page, int size);

	public Page<PostResponse> findByEnabledAndMuServerIdAndMuItemName(boolean enabled, Long muServerId,
			String muItemName, int page, int size);

	public Page<PostResponse> findByEnabledAndMuServerIdAndMuItemCategoryName(boolean enabled, Long muServerId,
			String muItemCategoryName, int page, int size);

	public Page<PostResponse> findByEnabledAndMuServerIdAndPostTypeName(boolean enabled, Long muServerId,
			String postTypeName, int page, int size);

	public Page<PostResponse> findByEnabledAndMuServerIdAndUserNickname(boolean enabled, Long muServerId,
			String userNickname, int page, int size);
}
