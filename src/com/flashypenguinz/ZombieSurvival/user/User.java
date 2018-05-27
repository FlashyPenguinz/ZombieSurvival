package com.flashypenguinz.ZombieSurvival.user;

public class User {

	private int userId;
	private String username;
	private String email;
	private String password;
	private int userLevel;
	
	private UserStats stats;
	
	public User(int userId, String username, String email, String password, int userLevel) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userLevel = userLevel;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public UserStats getStats() {
		return stats;
	}
	
}
