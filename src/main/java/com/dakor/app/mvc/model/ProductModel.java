package com.dakor.app.mvc.model;

import com.dakor.app.data.entity.AppType;
import com.dakor.app.data.entity.ContentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * .
 *
 * @author dkor
 */
public class ProductModel extends AbstractModel {
	@NotNull
	@NotEmpty
	private String name;

	@NotNull

	private AppType type;

	@NotNull
	@NotEmpty
	private List<ContentType> contentTypes;

	@NotNull
	private OwnerModel owner;

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

	public OwnerModel getOwner() {
		return owner;
	}

	public void setOwner(OwnerModel owner) {
		this.owner = owner;
	}
}
