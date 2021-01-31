package com.cbh.muonlineguildcommerce.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mu_servers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuServer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false, length = 40)
	private String name;
	@Column(length = 60)
	private String season;
	@Column(length = 200)
	private String website;
	@Column(nullable = false)
	private Boolean enabled;
	@Column(name = "created_date", nullable = false, updatable = false)
	private Instant createdDate;
	

	@OneToMany(mappedBy = "muServer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;
}
