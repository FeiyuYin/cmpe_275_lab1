package org.feiyu.cmpe275.Lab1;

/*
This class creates getter and setter of parameters used in Profile Service Interface
Getter and Setter of parameter UserId, Name and Age
*/

public class Profile {

	private String userId; 
	private String name; 
	private int age; 
	
	public String getUserId(){
		
		return userId; 
	}
	
	public void setUserId(String userId){
		
		this.userId = userId; 
	}
	
	public String getName(){
		
		return name; 
	}
	public void setName(String name){
		
		this.name = name; 
	}
	
	public int getAge(){
		
		return age; 
	}
	public void setAge(int age){
		
		this.age = age; 
	}
}
