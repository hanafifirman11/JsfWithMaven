package com.project.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.project.bl.UserBL;
import com.project.model.User;


@ManagedBean
@ViewScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserBean.class);
	
	private List<User> listOfUser = new ArrayList<User>();
	private User user = new User();
	private User userSelected = new User();
	private List<User> listOfUserTmp = new ArrayList<User>();
	private UserBL userBL = new UserBL();
	
	public UserBean() {
		init();
	}
	
	public void init() {
		LOGGER.info("Constructor for UserBean");
		user = new User();
		listOfUser = userBL.findAllUser();
		userSelected = new User();
		listOfUserTmp = new ArrayList<User>();
		LOGGER.info(listOfUser);
	}
	
	public void selectionUser() {
		user = userSelected;
	}

	public void save() {
		userBL.saveUser(userSelected);
		init();
	}

	public List<User> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<User> listOfUser) {
		this.listOfUser = listOfUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListOfUserTmp() {
		return listOfUserTmp;
	}

	public void setListOfUserTmp(List<User> listOfUserTmp) {
		this.listOfUserTmp = listOfUserTmp;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}
}
