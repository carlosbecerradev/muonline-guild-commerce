package com.cbh.muonlineguildcommerce.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.MuItem;

@Repository
public interface MuItemRepository extends JpaRepository<MuItem, Long> {

	@Query("SELECT mi FROM MuItem mi WHERE mi.name LIKE %:name% AND mi.enabled = 1")
	Page<MuItem> findByNameLikeAndEnabledTrue(@Param("name") String name, Pageable pageable);

}
