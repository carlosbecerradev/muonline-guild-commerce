package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cbh.muonlineguildcommerce.dto.request.PostRequest;
import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.PostNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.PostService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestControllerAdvice
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

	private final PostService postService;
	private final PageTemplateMapper<PostResponse> pageTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<PostResponse>> findAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<PostResponse> resultPage = postService.findAll(page, size);
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> findOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(postService.findOneById(id), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping
	public ResponseEntity<PostResponse> save(@RequestBody @Valid PostRequest postRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequest));
	}

	@RolesAllowed(value = { "ROLE_USER", "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid PostRequest postRequest) {
		return new ResponseEntity<>(postService.edit(postRequest, id), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_USER", "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneById(@PathVariable @Positive long id) {
		postService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/with-mu-server-id/{muServerId}")
	public ResponseEntity<PageTemplate<PostResponse>> findPostsPageByMuServerId(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "true") boolean enabled,
			@PathVariable Long muServerId,
			@RequestParam(required = false, defaultValue = "") String muItemName,
			@RequestParam(required = false, defaultValue = "") String muItemCategoryName,
			@RequestParam(required = false, defaultValue = "") String postTypeName,
			@RequestParam(required = false, defaultValue = "") String userNickname) {

		Page<PostResponse> resultPage = null;

		if (!muItemName.equalsIgnoreCase("")) {
			resultPage = postService.findByEnabledAndMuServerIdAndMuItemName(enabled, muServerId, muItemName, page,
					size);
		}
		if (!muItemCategoryName.equalsIgnoreCase("")) {
			resultPage = postService.findByEnabledAndMuServerIdAndMuItemCategoryName(enabled, muServerId,
					muItemCategoryName, page, size);
		}
		if (!postTypeName.equalsIgnoreCase("")) {
			resultPage = postService.findByEnabledAndMuServerIdAndPostTypeName(enabled, muServerId, postTypeName, page,
					size);
		}
		if (!userNickname.equalsIgnoreCase("")) {
			resultPage = postService.findByEnabledAndMuServerIdAndUserNickname(enabled, muServerId, userNickname, page,
					size);
		}
		if (resultPage == null) {
			resultPage = postService.findByEnabledAndMuServerId(enabled, muServerId, page, size);
		}

		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream()
				.map(err -> new ApiError(err.getCodes(), err.getDefaultMessage())).collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public List<ApiError> handleValidationExceptions(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(err -> new ApiError(err.getPropertyPath().toString(), err.getMessage()))
				.collect(Collectors.toList());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PostNotFound.class)
	public List<ApiError> handleNotFoundExceptions(PostNotFound ex) {
		return Collections.singletonList(new ApiError("post.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
