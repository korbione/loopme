package com.dakor.app.data.entity;

import javax.persistence.*;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@Entity
@Table(name = "USER")
public class UserEntity {
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false, unique = true, insertable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ROLE", nullable = false, length = 30)
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<AppEntity> products;

	@PrePersist
	public void prePersist() {
		if (role == null) {
			role = UserRole.PUBLISHER;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public List<AppEntity> getProducts() {
		return products;
	}

	public void setProducts(List<AppEntity> products) {
		this.products = products;
	}
}
