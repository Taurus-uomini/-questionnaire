package com.city.javaEEdzy.dao;

import java.util.HashMap;
import java.util.List;

import com.city.javaEEdzy.model.surveyModel;

public interface ISurveyDao {
	public int create(surveyModel sm) throws Exception;
	public int delete(int id) throws Exception;
	public int update(int hasdelete,int id) throws Exception;
	public surveyModel getOne(int id) throws Exception;
	public List<surveyModel> getList(int uid) throws Exception;
	public int getId(HashMap<Object, Object> m) throws Exception;
}
