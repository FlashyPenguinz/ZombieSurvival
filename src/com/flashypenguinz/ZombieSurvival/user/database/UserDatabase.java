package com.flashypenguinz.ZombieSurvival.user.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

public class UserDatabase {

	private DatabaseManager dm;

	public UserDatabase(DatabaseManager dm) {
		this.dm = dm;
	}

	public void signup(String username, String email, String hashedPassword) {
		LocalDate tld = LocalDate.now(ZoneId.of("America/Montreal"));

		try {
			Statement statement = dm.getConnection().createStatement();
			String sql = "INSERT INTO users (user_name, user_pass, user_email, user_date, user_level) VALUES ('"
					+ username + "', '" + hashedPassword + "', '" + email + "', '"
					+ tld.getYear() + "-" + tld.getMonthValue() + "-" + tld.getDayOfMonth() + "', 0)";
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}