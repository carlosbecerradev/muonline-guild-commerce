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
}
