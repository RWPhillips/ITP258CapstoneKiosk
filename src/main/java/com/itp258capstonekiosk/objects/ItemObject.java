package com.itp258capstonekiosk.objects;

import java.util.ArrayList;

public class ItemObject {

	private String name;
	private int category;
	private String description;
	private String picture;
	private double cost;
	private ArrayList<SubItemObject> subItems;

	public ItemObject() {
	}
	
	public ItemObject(String name) {
		this.name = name;
	}

	public ItemObject(int category, String name, double cost, String picture, String description) {
		this.name = name;
		this.category = category;
		this.description = description;
		this.picture = picture;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ArrayList<SubItemObject> getSubItems() {
		return subItems;
	}

	public void setSubItems(ArrayList<SubItemObject> subItems) {
		this.subItems = subItems;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}



}
