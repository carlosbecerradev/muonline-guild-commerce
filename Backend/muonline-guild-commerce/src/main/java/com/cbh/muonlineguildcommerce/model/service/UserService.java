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
}
