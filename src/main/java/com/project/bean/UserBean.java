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
	private List<User> listOfUserTmp = new ArrayList<User>();
	private UserBL userBL = new UserBL();
	
	public UserBean() {
		LOGGER.info("Constructor for UserBean");
		listOfUser = userBL.findAllUser();
		LOGGER.info(listOfUser);
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
}
