package com.city.javaEEdzy.dao;

import java.util.HashMap;
import java.util.List;

import com.city.javaEEdzy.model.surveyItemModel;

public interface ISurveyItemDao {
	public int create(surveyItemModel sim) throws Exception;
	public int delete(int id) throws Exception;
	public int update(HashMap<Object, Object> m) throws Exception;
	public surveyItemModel getOne(int id) throws Exception;
	public List<surveyItemModel> getList(int sqid) throws Exception;
	public int getId(HashMap<Object, Object> m) throws Exception;
}
