package com.cbh.muonlineguildcommerce.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbh.muonlineguildcommerce.dto.request.MuItemCategoryRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemCategoryResponse;
import com.cbh.muonlineguildcommerce.model.service.MuItemCategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/mu-item-category")
@AllArgsConstructor
public class MuItemCategoryController {

	private final MuItemCategoryService muItemCategoryService;

	@GetMapping
	public ResponseEntity<List<MuItemCategoryResponse>> finAll(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Page<MuItemCategoryResponse> dtoPage = muItemCategoryService.findAll(page, size);
		return new ResponseEntity<>(dtoPage.getContent(), HttpStatus.OK);
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
}
