package com.city.javaEEdzy.service.impl;

import java.util.List;

import com.city.javaEEdzy.dao.ISurveyDao;
import com.city.javaEEdzy.dao.IUserDao;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.surveyModel;
import com.city.javaEEdzy.service.ISurveyService;

public class surveyServiceImpl implements ISurveyService {

	@Override
	public int addSurvey(surveyModel sm) throws Exception {
		ISurveyDao isd=DaoFactory.getISurveyDao();
		int re=isd.create(sm);
		return re;
	}

	@Override
	public surveyModel showSurvey(int id) throws Exception {
		surveyModel sm=DaoFactory.getISurveyDao().getOne(id);
		return sm;
	}

	@Override
	public int deleteSurvey(int id) throws Exception {
		ISurveyDao isd=DaoFactory.getISurveyDao();
		int re=isd.update(1, id);
		return re;
	}

	@Override
	public List<surveyModel> getMySurveyList(int uid) throws Exception {
		List<surveyModel> list=DaoFactory.getISurveyDao().getList(uid);
		return list;
	}

}
