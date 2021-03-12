package com.cbh.muonlineguildcommerce.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	List<User> findByEnabledTrue();

	@Query("SELECT p FROM Post p WHERE p.user.id = :id AND p.enabled = :enabled ORDER BY p.createdDate DESC")
	Page<Post> findPostsOrderByDesc(Long id, boolean enabled, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.muItem.name = :muItemName AND p.enabled = :enabled ORDER BY p.createdDate DESC")
	Page<Post> findPostsByLoggedInUserAndMuItemNameAndEnabledOrderByDesc(Long userId, String muItemName,
			boolean enabled, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.muItem.muItemCategory.name = :muItemCategoryName AND p.enabled = :enabled ORDER BY p.createdDate DESC")
	Page<Post> findPostsByLoggedInUserAndMuItemCategoryNameAndEnabledOrderByDesc(Long userId, String muItemCategoryName,
			boolean enabled, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.postType.name = :postTypeName AND p.enabled = :enabled ORDER BY p.createdDate DESC")
	Page<Post> findPostsByLoggedInUserAndPostTypeNameAndEnabledOrderByDesc(Long userId, String postTypeName,
			boolean enabled, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.muServer.name = :muServerName AND p.enabled = :enabled ORDER BY p.createdDate DESC")
	Page<Post> findPostsByLoggedInUserAndMuServerNameAndEnabledOrderByDesc(Long userId, String muServerName,
			boolean enabled, Pageable pageable);
}
