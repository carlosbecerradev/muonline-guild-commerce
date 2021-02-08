package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.MuServerRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuServerResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuServer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MuServerMapper {

	public MuServerResponse mapEntityToDto(MuServer muServer) {
		MuServerResponse dto = new MuServerResponse(muServer.getId(), muServer.getName(), muServer.getSeason(),
				muServer.getWebsite(), muServer.getEnabled(), muServer.getCreatedDate());
		return dto;
	}

	public MuServer mapToSave(MuServerRequest muServerRequest) {
		MuServer entity = new MuServer(null, muServerRequest.getName(), muServerRequest.getSeason(),
				muServerRequest.getWebsite(), true, Instant.now());
		return entity;
	}

	public MuServer mapToEdit(MuServerRequest muServerRequest, MuServer muServer) {
		muServer.setName(muServerRequest.getName());
		muServer.setSeason(muServerRequest.getSeason());
		muServer.setWebsite(muServerRequest.getWebsite());
		return muServer;
	}
}
