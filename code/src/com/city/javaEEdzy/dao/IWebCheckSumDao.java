package com.city.javaEEdzy.dao;

import com.city.javaEEdzy.model.webCheckSumModel;

public interface IWebCheckSumDao {
	public int update(String type,int sum) throws Exception;
	public webCheckSumModel getOne(String type) throws Exception;
}
