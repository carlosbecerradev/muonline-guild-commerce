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

import com.cbh.muonlineguildcommerce.dto.PageDto;
import com.cbh.muonlineguildcommerce.dto.request.MuItemCategoryRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemCategoryResponse;
import com.cbh.muonlineguildcommerce.exception.ApiError;
import com.cbh.muonlineguildcommerce.exception.MuItemCategoryNotFound;
import com.cbh.muonlineguildcommerce.model.service.MuItemCategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/mu-item-category")
@AllArgsConstructor
public class MuItemCategoryController {

	private final MuItemCategoryService muItemCategoryService;

	@GetMapping
	public ResponseEntity<PageDto<MuItemCategoryResponse>> finAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<MuItemCategoryResponse> resultPage = muItemCategoryService.findAll(page, size);

		PageDto<MuItemCategoryResponse> pageDto = new PageDto<MuItemCategoryResponse>();
		PageDto<MuItemCategoryResponse>.PageDescriptionDto pageDescriptionDto = pageDto.new PageDescriptionDto(page, size, resultPage.getTotalPages(), resultPage.getTotalElements());
		
		pageDto.setData(resultPage.getContent());;
		pageDto.setDescription(pageDescriptionDto);
		
		return new ResponseEntity<>(pageDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MuItemCategoryResponse> finOneById(@PathVariable @Positive long id) {
		return new ResponseEntity<>(muItemCategoryService.findOneById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MuItemCategoryResponse> save(
			@RequestBody @Valid MuItemCategoryRequest muItemCategoryRequest) {
		return new ResponseEntity<>(muItemCategoryService.save(muItemCategoryRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MuItemCategoryResponse> edit(@PathVariable @Positive long id,
			@RequestBody @Valid MuItemCategoryRequest muItemCategoryRequest) {
		return new ResponseEntity<>(muItemCategoryService.edit(muItemCategoryRequest, id), HttpStatus.OK);
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
	@ExceptionHandler(MuItemCategoryNotFound.class)
	public List<ApiError> handleNotFoundExceptions(MuItemCategoryNotFound ex) {
		return Collections.singletonList(new ApiError("mu-item-category.notfound", ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ApiError> handleOtherException(Exception ex) {
		return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
	}
}
