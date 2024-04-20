package com.itp258capstonekiosk.objects;

public class SubItemObject {

	private String name;
	private int category;
	private double cost;

	public SubItemObject() {
	}
	
	public SubItemObject(String name) {
		this.name = name;
	}

	public SubItemObject(String name, double cost, int category) {
		this.name = name;
		this.cost = cost;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}


}
