package com.city.javaEEdzy.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.city.javaEEdzy.model.userModel;

public interface IUserDao {
	public int create(userModel um) throws Exception;
	public int delete(int id) throws Exception;
	public int update(HashMap<Object, Object> m) throws Exception;
	public userModel getOne(int id) throws Exception;
	public List<userModel> getList(HashMap<Object, Object> m) throws Exception;
	public int getId(HashMap<Object, Object> m) throws Exception;
}
