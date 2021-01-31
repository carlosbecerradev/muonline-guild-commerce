package com.cbh.muonlineguildcommerce.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostType implements Serializable {
	private Long id;
	private String name;
	private Boolean enabled;
}
