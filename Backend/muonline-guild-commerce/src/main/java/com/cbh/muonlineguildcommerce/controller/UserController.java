package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.UserNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestControllerAdvice
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	private final PageTemplateMapper<UserResponse> pageTemplateMapper;
	private final PageTemplateMapper<PostResponse> pagePostTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<UserResponse>> findAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<UserResponse> resultPage = userService.findAll(page, size);
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(userService.findOneById(id), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<UserResponse>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/posts")
	public ResponseEntity<PageTemplate<PostResponse>> findPosts(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "true") boolean enabled) {

		Page<PostResponse> resultPage = null;

		if (resultPage == null) {
			resultPage = userService.findPostsByEnabled(enabled, page, size);
		}

		return new ResponseEntity<>(pagePostTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFound.class)
	public List<ApiError> handleNotFoundExceptions(UserNotFound ex) {
		return Collections.singletonList(new ApiError("user.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
