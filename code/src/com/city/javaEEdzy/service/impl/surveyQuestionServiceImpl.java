package com.city.javaEEdzy.service.impl;

import com.city.javaEEdzy.dao.ISurveyQuestionDao;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.surveyQuestionModel;
import com.city.javaEEdzy.service.ISurveyQuestionService;

public class surveyQuestionServiceImpl implements ISurveyQuestionService {

	@Override
	public int addSurveyQuestion(surveyQuestionModel sqm) throws Exception {
		ISurveyQuestionDao isqd=DaoFactory.getISurveyQuestionDao();
		int re=isqd.create(sqm);
		return re;
	}

}
