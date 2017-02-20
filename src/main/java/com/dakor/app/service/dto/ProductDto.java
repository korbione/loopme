package com.dakor.app.service.dto;

import com.dakor.app.data.entity.AppType;
import com.dakor.app.data.entity.ContentType;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author dkor
 */
public class ProductDto extends AbstractIdentifierDto {
	private String name;
	private AppType type;
	private List<ContentType> contentTypes;
	private UserDto owner;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(AppType type) {
		this.type= type;
	}

	public AppType getType() {
		return type;
	}

	public void setContentTypes(List<ContentType> contentTypes) {
		getContentTypes().clear();
		getContentTypes().addAll(contentTypes);
	}

	public List<ContentType> getContentTypes() {
		if (contentTypes == null) {
			contentTypes = new ArrayList<>();
		}

		return contentTypes;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}

	public UserDto getOwner() {
		return owner;
	}
}
