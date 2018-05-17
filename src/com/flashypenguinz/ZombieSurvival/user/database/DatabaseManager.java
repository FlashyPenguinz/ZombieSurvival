package com.flashypenguinz.ZombieSurvival.user.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private Connection connection;
	
	private UserDatabase ud;
	
	public DatabaseManager() {
		this.ud = new UserDatabase(this);
		openConnection();
	}
	
	public synchronized void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://25.13.40.96:3306/zombiesurvival", "zombiesurvival", "bk6tAvNWU8oJkpY6");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UserDatabase getUserDatabase() {
		return ud;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
