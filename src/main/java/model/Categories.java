package model;

import java.util.Objects;

/** 
 * Categories class
 * Represents a category in the menu
 * @author Cody Cao & Xiaolin Zhan
 */
public class Categories {
	private int categoryId;
	private String categoryName;

	/**
	 * Constructor used when retrieving a category in the database where all parameters are known.
	 * @param categoryId
	 * @param name
	 */
	public Categories(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, categoryName);
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
		return categoryId == other.categoryId && Objects.equals(categoryName, other.categoryName);
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", name=" + categoryName + "]";
	}
	
}
