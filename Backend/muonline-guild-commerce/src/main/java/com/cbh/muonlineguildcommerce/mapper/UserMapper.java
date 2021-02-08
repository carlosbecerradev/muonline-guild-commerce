package com.cbh.muonlineguildcommerce.mapper;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.model.entity.User;

@Component
public class UserMapper {

	public UserResponse mapEntityToDto(User user) {
		UserResponse userResponse = new UserResponse(user.getId(), user.getNickname(), user.getContact(),
				user.getEnabled());
		return userResponse;
	}
}
