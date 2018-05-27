package com.flashypenguinz.ZombieSurvival.user.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import com.flashypenguinz.ZombieSurvival.user.PasswordAuthentication;

public class UserDatabase {

	private DatabaseManager dm;
	private PasswordAuthentication pa;
	
	public UserDatabase(DatabaseManager dm) {
		this.dm = dm;
		this.pa = new PasswordAuthentication();
	}
	
	public String getPassword(String username) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_name='"+username+"'";
			ResultSet result = statement.executeQuery(sql);
			if(!result.next()) return "";
			return result.getString("user_pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getUsername(String email) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_email='"+email+"'";
			ResultSet result = statement.executeQuery(sql);
			if(!result.next()) return "";
			return result.getString("user_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getUserId(String username) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_name='"+username+"'";
			ResultSet result = statement.executeQuery(sql);
			if(!result.next()) return -1;
			return result.getInt("user_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getUserLevel(String username) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_name='"+username+"'";
			ResultSet result = statement.executeQuery(sql);
			if(!result.next()) return -1;
			return result.getInt("user_level");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean hasUsername(String username) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_name='"+username+"'";
			ResultSet result = statement.executeQuery(sql);
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean hasEmail(String email) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_email='"+email+"'";
			ResultSet result = statement.executeQuery(sql);
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void signup(String username, String email, String password) {
		LocalDate tld = LocalDate.now(ZoneId.of("America/Montreal"));

		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "INSERT INTO users (user_name, user_pass, user_email, user_date, user_level) VALUES ('"
					+ username + "', '" + pa.hash(password.toCharArray()) + "', '" + email + "', '"
					+ tld.getYear() + "-" + tld.getMonthValue() + "-" + tld.getDayOfMonth() + "', 0)";
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(String email, String password) {
		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "SELECT * FROM users WHERE user_email='"+email+"'";
			ResultSet result = statement.executeQuery(sql);
			if(!result.next())
				return false;
			if(pa.authenticate(password.toCharArray(), result.getString("user_pass")))
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}