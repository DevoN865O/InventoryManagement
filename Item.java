package org.millardps.PracticeCreate;

public abstract class Item {
	int quantity;
	String name;
	String category;
	String description;
	double price;
	public Item(int q, String cat, String n, double p, String des ){
		quantity = q;
		category = cat;
		name = n;
		price = p;
		description = des;
	}
	public int getQuantity(){
		return quantity;
	}
	public String getName(){
		return name;
	}
	public String getCategory(){
		return category;
	}
	public String getDescription(){
		return description;
	}
	public double getPrice(){
		return price;
	}
	public void setQuantity(int q){
		quantity = q;
	}
	public void setName(String n){
		name = n;
	}
	public void setCategory(String c){
		category = c;
	}
	public void setDescription(String d){
		description = d;
	}
	public void setPrice(double p){
		price = p;
	}
	
}
