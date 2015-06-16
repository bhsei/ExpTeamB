package com.buaa.KNN;

public class KNNNode {

	private int index;
	private double dist;
	private String categ;
	
	
	public KNNNode(int index, double dist, String categ) {
		super();
		this.index = index;
		this.dist = dist;
		this.categ = categ;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
