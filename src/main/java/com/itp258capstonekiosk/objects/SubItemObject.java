package com.itp258capstonekiosk.objects;

import java.io.Serializable;

public class SubItemObject implements Serializable {
	private static int counter = -100; 
	private String name;
	private int category;
	private double cost;
	private int id; 
	
	public SubItemObject() {
		this.id = counter;
		counter -= 1; 
		//System.out.println("empty");
	}
	
	public SubItemObject(String name) {
		this.name = name;
		this.id = counter;
		counter -= 1; 
		//System.out.println("one");
	}

	public SubItemObject(String name, double cost, int category) {
		this.name = name;
		this.cost = cost;
		this.category = category;
		this.id = counter;
		counter -= 1; 
		//System.out.println("full");
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

	public int getId() {
		return id;
	}


}
