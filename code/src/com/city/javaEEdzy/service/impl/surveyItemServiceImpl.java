package com.city.javaEEdzy.service.impl;

import com.city.javaEEdzy.dao.ISurveyItemDao;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.surveyItemModel;
import com.city.javaEEdzy.service.ISurveyItemService;

public class surveyItemServiceImpl implements ISurveyItemService {

	@Override
	public int addSurveyItem(surveyItemModel sim) throws Exception {
		ISurveyItemDao isid=DaoFactory.getISurveyItemDao();
		int re=isid.create(sim);
		return re;
	}

}
