package com.cbh.muonlineguildcommerce.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbh.muonlineguildcommerce.dto.request.PostRequest;
import com.cbh.muonlineguildcommerce.dto.response.PostResponse;
import com.cbh.muonlineguildcommerce.exception.PostNotFound;
import com.cbh.muonlineguildcommerce.mapper.PostMapper;
import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.repository.PostRepository;
import com.cbh.muonlineguildcommerce.model.service.PostService;
import com.cbh.muonlineguildcommerce.security.provider.AuthenticationFacade;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final AuthenticationFacade authenticationFacade;

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findAll(int page, int size) {
		Page<Post> entities = postRepository.findAll(PageRequest.of(page, size));
		return entities.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public PostResponse findOneById(Long id) {
		Post post = findPostById(id);
		return postMapper.mapEntityToDto(post);
	}

	@Override
	@Transactional
	public PostResponse save(PostRequest postRequest) {
		Post post = postMapper.mapToSave(postRequest);
		post.setUser(authenticationFacade.getCurrentLoggedInUser());
		return postMapper.mapEntityToDto(postRepository.saveAndFlush(post));
	}

	@Override
	@Transactional
	public PostResponse edit(PostRequest postRequest, Long id) {
		Post post = findPostById(id);
		if (!isCurrentLoggedInUserPost(post)) {
			return null;
		}
		post = postMapper.mapToEdit(postRequest, post);
		return postMapper.mapEntityToDto(postRepository.save(post));
	}

	@Override
	@Transactional(readOnly = true)
	public Post findPostById(Long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new PostNotFound("Post with ID - " + id + " is not found"));
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Post post = findPostById(id);
		if (isCurrentLoggedInUserPost(post)) {
			postRepository.delete(post);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isCurrentLoggedInUserPost(Post post) {
		if (post.getUser().equals(authenticationFacade.getCurrentLoggedInUser())) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findByEnabledAndMuServerId(boolean enabled, Long muServerId, int page, int size) {
		Page<Post> result = postRepository.findByEnabledAndMuServerId(enabled, muServerId, PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findByEnabledAndMuServerIdAndMuItemName(boolean enabled, Long muServerId,
			String muItemName, int page, int size) {
		Page<Post> result = postRepository.findByEnabledAndMuServerIdAndMuItemName(enabled, muServerId, muItemName,
				PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findByEnabledAndMuServerIdAndMuItemCategoryName(boolean enabled, Long muServerId,
			String muItemCategoryName, int page, int size) {
		Page<Post> result = postRepository.findByEnabledAndMuServerIdAndMuItemMuItemCategoryName(enabled, muServerId,
				muItemCategoryName, PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findByEnabledAndMuServerIdAndPostTypeName(boolean enabled, Long muServerId,
			String postTypeName, int page, int size) {
		Page<Post> result = postRepository.findByEnabledAndMuServerIdAndPostTypeName(enabled, muServerId,
				postTypeName, PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PostResponse> findByEnabledAndMuServerIdAndUserNickname(boolean enabled, Long muServerId,
			String userNickname, int page, int size) {
		Page<Post> result = postRepository.findByEnabledAndMuServerIdAndUserNickname(enabled, muServerId,
				userNickname, PageRequest.of(page, size));
		return result.map(postMapper::mapEntityToDto);
	}

}
