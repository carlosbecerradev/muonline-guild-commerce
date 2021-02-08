package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.mapper.UserMapper;
import com.cbh.muonlineguildcommerce.model.entity.User;
import com.cbh.muonlineguildcommerce.model.repository.UserRepository;
import com.cbh.muonlineguildcommerce.model.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	@Transactional(readOnly = true)
	public Page<UserResponse> findAll(int page, int size) {
		Page<User> entities = userRepository.findAll(PageRequest.of(page, size));
		return entities.map(userMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findOneById(Long id) {
		return userMapper.mapEntityToDto(findUserById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFound("User with ID - " + id + " is not found."));
	}

}
