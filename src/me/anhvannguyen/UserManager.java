package me.anhvannguyen;

import java.util.ArrayList;

/**
 * A class to help create user and their data or update the user data
 * if they already exist
 * @author Anh
 *
 */
public class UserManager {
	private ArrayList<User> users;

	/**
	 * Initialize the list
	 */
	public UserManager() {
		users = new ArrayList<User>();
	}
	
	/**
	 * Add the user play data to the user data list, if the user does
	 * not exist, we create a new user and add the new user and data to
	 * the user list
	 */
	public void add(String userId, String data) {
		if (idExist(userId)) {
			getUser(userId).add(data);
		} else {
			User newUser = new User(userId, data);
			users.add(newUser);
		}
	}
	
	/**
	 * Check if the user already exist in the list
	 */
	public boolean idExist(String userId) {
		for (User user : users) {
			if (user.getId().equals(userId))
				return true;
		}
		return false;
	}
	
	/**
	 * Get the user based on the string identifier
	 */
	public User getUser(String userId) {
		for (User user : users) {
			if (userId.equals(user.getId()))
				return user;
		}
		return null;
	}
	
	/**
	 * Return the number of users in the list
	 */
	public int getUserNumber() {
		return users.size();
	}
	
	/**
	 * Return the list of users
	 */
	public ArrayList<User> getUserList() {
		return users;
	}
	
	/**
	 * Print all the user data
	 */
	public void printAllUser() {
		for (User user : users) {
			user.printUserId();
			user.printComboList();
		}
	}
	
}
