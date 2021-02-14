package com.cbh.muonlineguildcommerce.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

import com.cbh.muonlineguildcommerce.dto.request.PostTypeRequest;
import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.PostTypeNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.PostTypeService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/post-type")
@AllArgsConstructor
public class PostTypeController {

	private final PostTypeService postTypeService;
	private final PageTemplateMapper<PostTypeResponse> pageTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<PostTypeResponse>> finAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<PostTypeResponse> resultPage = postTypeService.findAll(page, size);
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostTypeResponse> finOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(postTypeService.findOneById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PostTypeResponse> save(@RequestBody @Valid PostTypeRequest postTypeRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postTypeService.save(postTypeRequest));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostTypeResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid PostTypeRequest postTypeRequest) {
		return new ResponseEntity<>(postTypeService.edit(postTypeRequest, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneById(@PathVariable @Positive long id) {
		postTypeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
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
	@ExceptionHandler(PostTypeNotFound.class)
	public List<ApiError> handleNotFoundExceptions(PostTypeNotFound ex) {
		return Collections.singletonList(new ApiError("mu-server.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
