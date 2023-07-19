package model;

import java.util.Objects;

/** 
 * Categories class
 * Represents a category in the menu
 * @author Cody Cao & Xiaolin Zhan
 */
public class Categories {
	private int categoryId;
	private String name;
	
	/**
	 * Constructor used when creating a new category in the database, where categoryId is unknown.
	 */
	public Categories(String name) {
		super();
		this.name = name;
	}

	/**
	 * Constructor used when retrieving a category in the database where all parameters are known.
	 * @param categoryId
	 * @param name
	 */
	public Categories(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		return categoryId == other.categoryId && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
}
