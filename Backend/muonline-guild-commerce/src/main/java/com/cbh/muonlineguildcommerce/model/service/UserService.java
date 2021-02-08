package com.cbh.muonlineguildcommerce.model.service;

import org.springframework.data.domain.Page;

import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.model.entity.User;

public interface UserService {
	public Page<UserResponse> findAll(int page, int size);

	public UserResponse findOneById(Long id);

	public User findUserById(Long id);
}
