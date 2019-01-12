package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private String DBUrl;
	private String username;
	private String password;

	public DataBase(String jdbcUrl, String username, String password) {
		this.DBUrl = jdbcUrl;
		this.username = username;
		this.password = password;
	}

	public boolean connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBUrl, username, password);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean disconnectFromDB() {
		try {
			if (resultSet != null && !resultSet.isClosed())
				resultSet.close();
			if (statement != null && !statement.isClosed())
				statement.close();
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public ResultSet getDataFromDB(String query) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			return null;
		}
		return resultSet;
	}
}
