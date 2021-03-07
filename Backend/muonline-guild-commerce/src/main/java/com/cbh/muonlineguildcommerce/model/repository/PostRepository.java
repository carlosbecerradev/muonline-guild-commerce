package com.cbh.muonlineguildcommerce.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Page<Post> findByEnabledAndMuServerId(boolean enabled, Long muServerId, Pageable pageable);

	Page<Post> findByEnabledAndMuServerIdAndMuItemName(boolean enabled, Long muServerId, String muItemName,
			Pageable pageable);

	Page<Post> findByEnabledAndMuServerIdAndMuItemMuItemCategoryName(boolean enabled, Long muServerId,
			String muItemCategoryName, Pageable pageable);

	Page<Post> findByEnabledAndMuServerIdAndPostTypeName(boolean enabled, Long muServerId, String postTypeName,
			Pageable pageable);

	Page<Post> findByEnabledAndMuServerIdAndUserNickname(boolean enabled, Long muServerId, String userNickname,
			Pageable pageable);

}
