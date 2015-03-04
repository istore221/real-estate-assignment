package dsa_project;

import java.io.Serializable;
import java.math.BigDecimal;

public class House implements Comparable<House>,Serializable,Cloneable {
	
	
	private static final long serialVersionUID = 1L; // version 
	
	/*
	 * 
	 *  Hold the information  about a specific
	 *  house
	 * 
	 */
	
	private long lotnumber;
	private Owner owner;
	private int nobedRooms;
	private double squareFeet;
	private BigDecimal price;
	
	
	
	public House(){
		
		this.lotnumber = 0L;
		this.owner = null;
		this.nobedRooms = 0;
		this.squareFeet = 0.00;
		this.price = null;
		
	}
	
	public House(long lotnumber,int nobedRooms,double squareFeet,BigDecimal price){
		
		this.lotnumber = lotnumber;
		this.nobedRooms = nobedRooms;
		this.squareFeet = squareFeet;
		this.price = price;
	}
	
	public House(long lotnumber,Owner owner,int nobedRooms,double squareFeet,BigDecimal price){
		
		this(lotnumber,nobedRooms,squareFeet,price);
		this.owner = owner;
	}
	
	
	public long getLotnumber() {
		return lotnumber;
	}


	public void setLotnumber(long lotnumber) {
		this.lotnumber = lotnumber;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public int getNobedRooms() {
		return nobedRooms;
	}


	public void setNobedRooms(int nobedRooms) {
		this.nobedRooms = nobedRooms;
	}


	public double getSquareFeet() {
		return squareFeet;
	}


	public void setSquareFeet(double squareFeet) {
		this.squareFeet = squareFeet;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	
	/*
	 * compareTo
	 * 
	 * If the Integer is equal to the argument then 0 is returned.
	 * If the Integer is less than the argument then -1 is returned.
	 * If the Integer is greater than the argument then 1 is returned.
	 * 
	 */
	public int compareTo(House house) {		
		return Long.compare(this.lotnumber, house.lotnumber);
	}

	@Override
	public boolean equals(Object object) {
		
		return (((House)object).lotnumber == this.lotnumber) ? true : false;
		
	}
	
	
	
	 protected Object clone() throws CloneNotSupportedException {
		
		 	
		    return super.clone();
	 }
	
	
	
}
