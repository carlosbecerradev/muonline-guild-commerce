package com.cbh.muonlineguildcommerce.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.model.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.cbh.muonlineguildcommerce.model.entity.User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFound("User with email -" + email + " is not found."));

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.addAll(authorities);

		return new User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}
}
