package com.cbh.muonlineguildcommerce.model.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mu_item_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuItemOption implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false, length = 60)
	private String name;
	@Column(nullable = false)
	private Boolean enabled;
	@Column(name = "created_date", nullable = false, updatable = false)
	private Instant createdDate;	
}
