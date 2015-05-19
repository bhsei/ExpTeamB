package com.buaa.KNN;

public class Node {

	private double dist;
	private String categ;
	
	
	public Node(double dist, String categ) {
		super();
		this.dist = dist;
		this.categ = categ;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	
	
}
