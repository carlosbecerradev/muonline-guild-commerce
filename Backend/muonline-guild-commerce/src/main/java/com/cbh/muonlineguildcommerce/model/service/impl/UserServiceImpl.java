package com.cbh.muonlineguildcommerce.model.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.mapper.PostMapper;
import com.cbh.muonlineguildcommerce.mapper.UserMapper;
import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.entity.User;
import com.cbh.muonlineguildcommerce.model.repository.UserRepository;
import com.cbh.muonlineguildcommerce.model.service.UserService;
import com.cbh.muonlineguildcommerce.security.provider.AuthenticationFacade;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PostMapper postMapper;
	private final AuthenticationFacade authenticationFacade;

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

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> findAll() {
		return userRepository.findByEnabledTrue().stream().map(userMapper::mapEntityToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findPostsByEnabled(boolean enabled, int page, int size) {
		Long userId = authenticationFacade.getCurrentLoggedInUser().getId();
		Page<Post> result = userRepository.findPostsOrderByDesc(userId, enabled, PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

}
