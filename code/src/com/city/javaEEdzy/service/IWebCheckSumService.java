package com.city.javaEEdzy.service;

import com.city.javaEEdzy.model.webCheckSumModel;

public interface IWebCheckSumService {
	public int editUser(webCheckSumModel wcsm)throws Exception;
	public webCheckSumModel getwebCheckSum(String type)throws Exception;
}
