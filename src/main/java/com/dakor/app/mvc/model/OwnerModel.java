package com.dakor.app.mvc.model;

/**
 * .
 *
 * @author dkor
 */
public class OwnerModel extends AbstractModel {
	private String name;

	public OwnerModel() {
	}

	public OwnerModel(Integer id, String name) {
		setId(id);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
