package com.project.bl;

import java.util.List;

import com.project.dao.UserDAO;
import com.project.model.User;

public class UserBL {
	private UserDAO userDAO;
	
	public UserBL() {
		userDAO = new UserDAO();
	}
	
	public List<User> findAllUser(){
		return userDAO.findAlluser();
	}
	
	public User findUserById(Long id) {
		return userDAO.findUserById(id);
	}
	
	public List<User> findGlobalSearchUser(String search){
		return userDAO.findGlobalSearchUser(search);
	}
	
	public List<User> findAdvancedSearchUser(User user){
		return userDAO.findAdvancedSearchUser(user);
	}
	
	public Boolean deleteUser (User user) {
		if(userDAO.deleteUser(user)) {
			return true;
		}
		return false;
	}
	
	public Boolean updateUser (User user) {
		if(userDAO.updateUser(user)) {
			return true;
		}
		return false;
	}
	
	public Boolean saveUser (User user) {
		if(userDAO.insertUser(user)) {
			return true;
		}
		return false;
	}
	
	public Boolean saveUserList (List<User> user) {
		if(userDAO.insertListUser(user)) {
			return true;
		}
		return false;
	}
}
