package com.city.javaEEdzy.dao;

import java.util.HashMap;
import java.util.List;

import com.city.javaEEdzy.model.surveyQuestionModel;

public interface ISurveyQuestionDao {
	public int create(surveyQuestionModel sqm) throws Exception;
	public int delete(int id) throws Exception;
	public int update(HashMap<Object, Object> m) throws Exception;
	public surveyQuestionModel getOne(int id) throws Exception;
	public List<surveyQuestionModel> getList(int sid) throws Exception;
	public int getId(HashMap<Object, Object> m) throws Exception;
}
