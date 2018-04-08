package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project.config.ConnectionConfig;
import com.project.model.User;

public class UserDAO {
	
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
	
	public List<User> findAlluser(){
		Connection connection = ConnectionConfig.getDBConnection();
		String sql = "SELECT * FROM users ORDER BY id DESC";
		List<User> listUser = new ArrayList<User>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(
						rs.getLong("id"), 
						rs.getString("email"), 
						rs.getString("name"), 
						rs.getDate("birth_date"), 
						rs.getString("address"), 
						rs.getBigDecimal("salary"), 
						rs.getBoolean("status")
				);
				listUser.add(user);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage(), e2);
			}
		}
		
		return listUser;
	}
	
	public List<User> findGlobalSearchUser(String search){
		Connection connection = ConnectionConfig.getDBConnection();
		
		//Query Global Search
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM users WHERE 1 = 1");
		if(search != null && !"".equals(search)) {
			sql.append(" OR ");
			sql.append(" email ilike '%"+search+"%' ");
			sql.append(" OR ");
			sql.append(" name ilike '%"+search+"%' ");
			sql.append(" OR ");
			sql.append(" date(birth_date) ilike '%"+search+"%' ");
			sql.append(" OR ");
			sql.append(" address ilike '%"+search+"%' ");
			sql.append(" OR ");
			sql.append(" salary ilike '%"+search+"%' ");
			if("false".equals(search) || "true".equals(search)) {
				sql.append(" OR ");
				sql.append(" status is "+search+" ");
			}
		}
		
		List<User> listUser = new ArrayList<User>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(
						rs.getLong("id"), 
						rs.getString("email"), 
						rs.getString("name"), 
						rs.getDate("birth_date"), 
						rs.getString("address"), 
						rs.getBigDecimal("salary"), 
						rs.getBoolean("status")
				);
				listUser.add(user);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage(), e2);
			}
		}
		
		return listUser;
	}
	
	public List<User> findAdvancedSearchUser(User user){
		Connection connection = ConnectionConfig.getDBConnection();
		
		//Query Global Search
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM users WHERE 1 = 1");
		if(user != null) {
			if(user.getEmail() != null) {
				sql.append(" AND ");
				sql.append(" email ilike '%"+user.getEmail()+"%' ");
			}
			if(user.getName() != null) {
				sql.append(" AND ");
				sql.append(" name ilike '%"+user.getName()+"%' ");
			}
			if(user.getBirthDate() != null) {
				sql.append(" AND ");
				sql.append(" date(birth_date) ilike '%"+new SimpleDateFormat("yyyMMdd").format(user.getBirthDate())+"%' ");
			}
			if(user.getAddress() != null) {
				sql.append(" AND ");
				sql.append(" address ilike '%"+user.getAddress()+"%' ");
			}
			if(user.getSalary() != null) {
				sql.append(" AND ");
				sql.append(" salary = %"+user.getSalary()+"% ");
			}
			if(user.getStatus() != null) {
				sql.append(" AND ");
				sql.append(" status is "+user.getStatus()+" ");
			}
		}
		
		List<User> listUser = new ArrayList<User>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User(
						rs.getLong("id"), 
						rs.getString("email"), 
						rs.getString("name"), 
						rs.getDate("birth_date"), 
						rs.getString("address"), 
						rs.getBigDecimal("salary"), 
						rs.getBoolean("status")
				);
				listUser.add(u);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage(), e2);
			}
		}
		
		return listUser;
	}
	
	public User findUserById(Long id){
		Connection connection = ConnectionConfig.getDBConnection();
		String sql = "SELECT * FROM users WHERE id = "+id+" ORDER BY id DESC LIMIT 1";
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(
						rs.getLong("id"), 
						rs.getString("email"), 
						rs.getString("name"), 
						rs.getDate("birth_date"), 
						rs.getString("address"), 
						rs.getBigDecimal("salary"), 
						rs.getBoolean("status")
				);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage(), e2);
			}
		}
		
		return user;
	}
	
	public Boolean insertListUser(List<User> listUser) {
		for(User u : listUser) {
			if(!insertUser(u)) {
				LOGGER.debug("data error in "+u);
				return false;
			}
		}
		return true;
	}
	
	public Boolean insertUser(User user) {
		Connection connection = ConnectionConfig.getDBConnection();
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO users"
				+ "(id, email, name, birth_date, address, salary, status) VALUES"
				+ "(?,?,?,?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);

			preparedStatement.setLong(1, user.getId());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setTimestamp(4, new Timestamp(user.getBirthDate().getTime()));
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setBigDecimal(6, user.getSalary());
			preparedStatement.setBoolean(7, user.getStatus());
			
			int check = preparedStatement.executeUpdate();

			LOGGER.info("Record tabel check -- "+check);

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e2) {
				LOGGER.error(e2.getMessage(), e2);
			}
		}
		return true;
	}

	
}
