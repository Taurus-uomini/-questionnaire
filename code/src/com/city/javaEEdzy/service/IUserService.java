package com.city.javaEEdzy.service;

import java.util.HashMap;
import java.util.List;

import com.city.javaEEdzy.model.userModel;

public interface IUserService {
	public int login(String username,String password)throws Exception;
	public userModel getUserInfo(int id)throws Exception;
	public int addUser(userModel um)throws Exception;
	public List<userModel> getAllUserList()throws Exception;
	public int deleteUser(int id)throws Exception;
	public int editUser(userModel um)throws Exception;
	public int checkSameUser(String username) throws Exception;
	public void sendMail(String from,String to,String title,String content) throws Exception;
	public int activationUser(String link)throws Exception;
	public boolean editPassword(userModel um)throws Exception;
}
