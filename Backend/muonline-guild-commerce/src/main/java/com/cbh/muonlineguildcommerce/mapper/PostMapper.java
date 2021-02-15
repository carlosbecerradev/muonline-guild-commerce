package com.cbh.muonlineguildcommerce.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.cbh.muonlineguildcommerce.dto.request.PostRequest;
import com.cbh.muonlineguildcommerce.dto.response.MuItemOptionResponse;
import com.cbh.muonlineguildcommerce.dto.response.MuItemResponse;
import com.cbh.muonlineguildcommerce.dto.response.MuServerResponse;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.dto.response.PostTypeResponse;
import com.cbh.muonlineguildcommerce.dto.response.UserResponse;
import com.cbh.muonlineguildcommerce.model.entity.MuItem;
import com.cbh.muonlineguildcommerce.model.entity.MuItemOption;
import com.cbh.muonlineguildcommerce.model.entity.MuServer;
import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.entity.PostType;
import com.cbh.muonlineguildcommerce.model.service.MuItemOptionService;
import com.cbh.muonlineguildcommerce.model.service.MuItemService;
import com.cbh.muonlineguildcommerce.model.service.MuServerService;
import com.cbh.muonlineguildcommerce.model.service.PostTypeService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PostMapper {

	private final MuItemService muItemService;
	private final MuItemOptionService muItemOptionService;
	private final MuServerService muServerService;
	private final PostTypeService postTypeService;

	private final UserMapper userMapper;
	private final MuItemMapper muItemMapper;
	private final MuItemOptionMapper muItemOptionMapper;
	private final MuServerMapper muServerMapper;
	private final PostTypeMapper postTypeMapper;

	public PostResponse mapEntityToDto(Post post) {
		MuServerResponse muServerResponse = muServerMapper.mapEntityToDto(post.getMuServer());
		UserResponse userResponse = userMapper.mapEntityToDto(post.getUser());
		MuItemResponse muItemResponse = muItemMapper.mapEntityToDto(post.getMuItem());
		MuItemOptionResponse muItemOptionResponse = muItemOptionMapper.mapEntityToDto(post.getMuItemOption());
		PostTypeResponse postTypeResponse = postTypeMapper.mapEntityToDto(post.getPostType());

		PostResponse postResponse = new PostResponse(post.getId(), muServerResponse, userResponse, muItemResponse,
				muItemOptionResponse, postTypeResponse, post.getItemLevel(), post.getObservation(), post.getEnabled(),
				post.getCreatedDate());
		return postResponse;
	}

	public Post mapToSave(PostRequest postRequest) {
		MuServer muServer = muServerService.findMuServerById(postRequest.getMuServerId());
		MuItem muItem = muItemService.findMuItemById(postRequest.getMuItemId());
		MuItemOption muItemOption = muItemOptionService.findMuItemOptionById(postRequest.getMuItemOptionId());
		PostType postType = postTypeService.findPostTypeById(postRequest.getPostTypeId());

		Post post = new Post(null, postRequest.getItemLevel(), postRequest.getObservation(), true, Instant.now(),
				muServer, null, muItem, muItemOption, postType);
		return post;
	}

	public Post mapToEdit(PostRequest postRequest, Post post) {
		MuItem muItem = muItemService.findMuItemById(postRequest.getMuItemId());
		MuItemOption muItemOption = muItemOptionService.findMuItemOptionById(postRequest.getMuItemOptionId());
		PostType postType = postTypeService.findPostTypeById(postRequest.getPostTypeId());

		post.setItemLevel(postRequest.getItemLevel());
		post.setObservation(postRequest.getObservation());
		post.setMuItem(muItem);
		post.setMuItemOption(muItemOption);
		post.setPostType(postType);

		return post;
	}
}
