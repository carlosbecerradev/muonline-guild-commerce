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

import com.cbh.muonlineguildcommerce.dto.request.MuItemOptionRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemOptionResponse;
import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.MuItemOptionNotFound;
import com.cbh.muonlineguildcommerce.mapper.PageTemplateMapper;
import com.cbh.muonlineguildcommerce.model.service.MuItemOptionService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestControllerAdvice
@RestController
@RequestMapping("/api/mu-item-option")
@AllArgsConstructor
public class MuItemOptionController {

	private final MuItemOptionService muItemOptionService;
	private final PageTemplateMapper<MuItemOptionResponse> pageTemplateMapper;

	@GetMapping
	public ResponseEntity<PageTemplate<MuItemOptionResponse>> finAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<MuItemOptionResponse> resultPage = muItemOptionService.findAll(page, size);
		return new ResponseEntity<>(pageTemplateMapper.map(resultPage), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MuItemOptionResponse> finOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(muItemOptionService.findOneById(id), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_ADMIN" })
	@PostMapping
	public ResponseEntity<MuItemOptionResponse> save(@RequestBody @Valid MuItemOptionRequest muItemOptionRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(muItemOptionService.save(muItemOptionRequest));
	}

	@RolesAllowed(value = { "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<MuItemOptionResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid MuItemOptionRequest muItemOptionRequest) {
		return new ResponseEntity<>(muItemOptionService.edit(muItemOptionRequest, id), HttpStatus.OK);
	}

	@RolesAllowed(value = { "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneById(@PathVariable @Positive long id) {
		muItemOptionService.deleteById(id);
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
	@ExceptionHandler(MuItemOptionNotFound.class)
	public List<ApiError> handleNotFoundExceptions(MuItemOptionNotFound ex) {
		return Collections.singletonList(new ApiError("mu-item-option.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
