package com.cbh.muonlineguildcommerce.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageTemplate<T> {
	private List<T> data;
	private PageDescriptionDto description;

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public class PageDescriptionDto {
		private Integer number;
		private Integer size;
		private Integer totalPages;
		private Long totalElements;
	}
}
