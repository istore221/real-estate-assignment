package dsa_project;

import java.io.Serializable;



public class Owner implements Serializable {

	private static final long serialVersionUID = 1L; // version 
	
	/*
	 * 
	 * 
	 *  Hold the information about specific 
	 *  house owner
	 * 
	 * 
	 */
	
	
	
	
	private String firstname;
	private String lastname;
	
	
	public Owner(){
		
			this.firstname =  null;
			this.lastname = null;
	}
	
	public Owner(String firstname,String lastname){
		
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String toString() {
		
		return this.firstname.concat(" "+this.lastname);
	}
	
	
	
}
