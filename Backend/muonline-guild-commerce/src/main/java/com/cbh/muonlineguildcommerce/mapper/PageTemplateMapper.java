package com.cbh.muonlineguildcommerce.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.response.PageTemplate;

@Component
public class PageTemplateMapper<T> {

	public PageTemplate<T> map(Page<T> page) {
		PageTemplate<T> pageTemplateResponse = new PageTemplate<T>();
		PageTemplate<T>.PageDescriptionDto pageDescriptionDto = pageTemplateResponse.new PageDescriptionDto(
				page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());

		pageTemplateResponse.setData(page.getContent());
		pageTemplateResponse.setDescription(pageDescriptionDto);

		return pageTemplateResponse;
	}
}
