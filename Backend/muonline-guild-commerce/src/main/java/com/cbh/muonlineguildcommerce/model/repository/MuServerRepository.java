package com.cbh.muonlineguildcommerce.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.MuServer;

@Repository
public interface MuServerRepository extends JpaRepository<MuServer, Long> {

	List<MuServer> findByEnabledTrue();

	Page<MuServer> findByEnabled(Boolean enabled, Pageable pageable);
	
}
