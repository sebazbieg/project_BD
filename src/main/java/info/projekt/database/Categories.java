package info.projekt.database;
// Generated 2016-11-29 19:03:16 by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;

/**
 * Categories generated by hbm2java
 */
@SuppressWarnings("serial")
public class Categories implements java.io.Serializable {

	/**
	 * 
	 */
	private Byte categoryId;
	private String categoryName;
	private String description;
	private String picture;
	private Set<Products> productses = new HashSet<Products>(0);

	public Categories() {
	}

	public Categories(String categoryName, String description, String picture) {
		this.categoryName = categoryName;
		this.description = description;
		this.picture = picture;
	}

	public Categories(String categoryName, String description, String picture, Set<Products> productses) {
		this.categoryName = categoryName;
		this.description = description;
		this.picture = picture;
		this.productses = productses;
	}

	public Byte getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Byte categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set<Products> getProductses() {
		return this.productses;
	}

	public void setProductses(Set<Products> productses) {
		this.productses = productses;
	}

}