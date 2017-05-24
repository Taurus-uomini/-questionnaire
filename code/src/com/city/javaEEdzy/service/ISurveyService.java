package com.city.javaEEdzy.service;

import java.util.List;

import com.city.javaEEdzy.model.surveyModel;

public interface ISurveyService {
	public int addSurvey(surveyModel sm)throws Exception;
	public surveyModel showSurvey(int id)throws Exception;
	public int deleteSurvey(int id)throws Exception;
	public List<surveyModel> getMySurveyList(int uid) throws Exception;
}
