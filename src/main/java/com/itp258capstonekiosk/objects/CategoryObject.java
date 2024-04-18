package com.itp258capstonekiosk.objects;

import java.util.ArrayList;

public class CategoryObject {
	
	private int categoryId;
	private String imageURL;
	private String name;
	private ArrayList<CategoryObject> category;
	
	public CategoryObject() {
	}
	
	public CategoryObject(int categoryId, String imageURL, String name) {
		this.categoryId = categoryId;
		this.imageURL = imageURL;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<CategoryObject> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<CategoryObject> category) {
		this.category = category;
	}
	
}
