package com.cbh.muonlineguildcommerce.model.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.model.entity.User;

public interface UserService {
	public Page<UserResponse> findAll(int page, int size);

	public UserResponse findOneById(Long id);

	public User findUserById(Long id);

	public List<UserResponse> findAll();

	public Page<PostResponse> findPostsByEnabled(boolean enabled, int page, int size);

	public Page<PostResponse> findPostsByEnabledAndMuItemName(boolean enabled, String muItemName, int page, int size);

	public Page<PostResponse> findPostsByEnabledAndMuItemCategoryName(boolean enabled, String muItemCategoryName, int page,
			int size);

	public Page<PostResponse> findPostsByEnabledAndPostTypeName(boolean enabled, String postTypeName, int page, int size);

	public Page<PostResponse> findPostsByEnabledAndMuServerName(boolean enabled, String muServerName, int page, int size);
}
