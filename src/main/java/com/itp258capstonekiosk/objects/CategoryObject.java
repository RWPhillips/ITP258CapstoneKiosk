package com.itp258capstonekiosk.objects;

import java.util.ArrayList;

public class CategoryObject {
	
	private String imageURL;
	private String name;
	private ArrayList<CategoryObject> category;
	
	public CategoryObject() {
	}
	
	public CategoryObject(String imageURL, String name) {

		this.imageURL = imageURL;
		this.name = name;
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
