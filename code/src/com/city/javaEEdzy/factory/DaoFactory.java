package com.city.javaEEdzy.factory;

import com.city.javaEEdzy.dao.ISurveyDao;
import com.city.javaEEdzy.dao.ISurveyItemDao;
import com.city.javaEEdzy.dao.ISurveyQuestionDao;
import com.city.javaEEdzy.dao.IUserDao;
import com.city.javaEEdzy.dao.IWebCheckSumDao;
import com.city.javaEEdzy.dao.impl.surveyDaoImpl;
import com.city.javaEEdzy.dao.impl.surveyItemDaoImpl;
import com.city.javaEEdzy.dao.impl.surveyQuestionDaoImpl;
import com.city.javaEEdzy.dao.impl.userDaoImpl;
import com.city.javaEEdzy.dao.impl.webCheckSumDaoImpl;

public class DaoFactory {
	public static IUserDao getIUserDao()throws Exception
	{
		return new userDaoImpl();
	}
	public static ISurveyDao getISurveyDao()throws Exception
	{
		return new surveyDaoImpl();
	}
	public static ISurveyItemDao getISurveyItemDao()throws Exception
	{
		return new surveyItemDaoImpl();
	}
	public static ISurveyQuestionDao getISurveyQuestionDao()throws Exception
	{
		return new surveyQuestionDaoImpl();
	}
	public static IWebCheckSumDao getIWebCheckSumDao()throws Exception
	{
		return new webCheckSumDaoImpl();
	}
}
