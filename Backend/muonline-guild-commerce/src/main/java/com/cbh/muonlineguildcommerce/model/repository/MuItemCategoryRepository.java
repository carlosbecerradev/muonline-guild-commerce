package com.cbh.muonlineguildcommerce.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.MuItemCategory;

@Repository
public interface MuItemCategoryRepository extends JpaRepository<MuItemCategory, Long> {
	boolean existsByName(String name);

	List<MuItemCategory> findByEnabledTrue();

}
