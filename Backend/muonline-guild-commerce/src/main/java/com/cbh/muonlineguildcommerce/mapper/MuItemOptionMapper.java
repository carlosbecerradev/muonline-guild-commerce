package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.MuItemOptionRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemOptionResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItemOption;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MuItemOptionMapper {

	public MuItemOptionResponse mapEntityToDto(MuItemOption muItemOption) {
		MuItemOptionResponse muItemOptionResponse = new MuItemOptionResponse(muItemOption.getId(),
				muItemOption.getName(), muItemOption.getEnabled(), muItemOption.getCreatedDate());
		return muItemOptionResponse;
	}

	public MuItemOption mapToSave(MuItemOptionRequest muItemOptionRequest) {
		MuItemOption muItemOption = new MuItemOption(null, muItemOptionRequest.getName(), true, Instant.now());
		return muItemOption;
	}

	public MuItemOption mapToEdit(MuItemOptionRequest muItemOptionRequest, MuItemOption muItemOption) {
		muItemOption = new MuItemOption(muItemOption.getId(), muItemOptionRequest.getName(), muItemOption.getEnabled(),
				muItemOption.getCreatedDate());
		return muItemOption;
	}
}
