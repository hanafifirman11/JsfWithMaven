package com.project.config;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.project.dao.UserDAO;
import com.project.model.User;

public class ConnectionConfig {
	
	private static final Logger LOGGER = Logger.getLogger(ConnectionConfig.class);
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/test";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "postgres";

	public static void main(String[] argv) {
		UserDAO userDAO = new UserDAO();
		
		User user = new User(3l, "aaa", "nam", new Date(), "jojo", new BigDecimal(1232), true); 
		
		LOGGER.info(userDAO.insertUser(user));
		
		LOGGER.info(userDAO.findAlluser());
	}

	public static Connection getDBConnection() {
		Connection dbConnection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}

		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return dbConnection;
	}
}
