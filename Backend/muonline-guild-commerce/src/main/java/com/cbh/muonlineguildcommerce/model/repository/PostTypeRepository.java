package com.cbh.muonlineguildcommerce.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbh.muonlineguildcommerce.model.entity.PostType;

@Repository
public interface PostTypeRepository extends JpaRepository<PostType, Long> {

}
