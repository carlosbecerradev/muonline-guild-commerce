package com.cbh.muonlineguildcommerce.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.MuItemOption;

@Repository
public interface MuItemOptionRepository extends JpaRepository<MuItemOption, Long> {

	List<MuItemOption> findByEnabledTrue();

}
