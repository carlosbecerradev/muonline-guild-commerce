package com.cbh.muonlineguildcommerce.model.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_level")
	private Byte itemLevel;
	private String observation;
	@Column(nullable = false)
	private Boolean enabled;
	@Column(name = "created_date", nullable = false, updatable = false)
	private Instant createdDate;
	
	@ManyToOne
	@JoinColumn(name = "mu_server_id", nullable = false)
	private MuServer muServer;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "mu_item_id", nullable = false)
	private MuItem muItem;
	
	@ManyToOne
	@JoinColumn(name = "mu_item_option_id", nullable = false)
	private MuItemOption muItemOption;

	@ManyToOne
	@JoinColumn(name = "post_type_id", nullable = false)
	private PostType postType;
}
