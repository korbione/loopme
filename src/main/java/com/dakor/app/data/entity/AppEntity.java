package com.dakor.app.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
@Entity
@Table(name = "APP")
public class AppEntity {
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false, unique = true, insertable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TYPE", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private AppType type;

	@ElementCollection(targetClass = ContentType.class)
	@CollectionTable(name = "CONTENT_TYPES", joinColumns = @JoinColumn(name = "ID"))
	@Column(name = "CONTENT_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<ContentType> contentTypes;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "USER_ID")
	private UserEntity user;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public List<ContentType> getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(List<ContentType> contentTypes) {
		this.contentTypes = contentTypes;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
