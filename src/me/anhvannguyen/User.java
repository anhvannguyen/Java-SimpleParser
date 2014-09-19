package me.anhvannguyen;

import java.util.ArrayList;

/**
 * A class to hold all the user data
 * @author Anh
 *
 */
public class User {
	private String dateId;				 // The ID of the user
	private ArrayList<String> userData;	 // The list of user input data
	
	public User(String dateId) {
		this(dateId, null);
	}
	
	
	/**
	 * Initialize the user and add the first input data to the list
	 */
	public User(String dateId, String data) {
		userData = new ArrayList<String>();
		this.dateId = dateId;
		this.userData.add(data);
	}
	
	/**
	 * Add the user data to the list
	 */
	public void add(String data) {
		userData.add(data);
	}

	/**
	 * Return the string ID of the user
	 */
	public String getId() {
		return dateId;
	}

	/**
	 * Return the list of the user input data
	 */
	public ArrayList<String> getUserData() {
		return userData;
	}
	
	/**
	 * ID print helper
	 */
	public void printUserId() {
		System.out.println("**********************");
		System.out.println(getId());
		System.out.println("**********************");
	}
	
	/**
	 * Data list print helper
	 */
	public void printComboList() {
		for (String combo : userData) {
			System.out.println(combo);
		}
		System.out.println();
	}

}
