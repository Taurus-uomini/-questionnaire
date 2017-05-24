package com.city.javaEEdzy.factory;

import com.city.javaEEdzy.dao.impl.surveyQuestionDaoImpl;
import com.city.javaEEdzy.dao.impl.webCheckSumDaoImpl;
import com.city.javaEEdzy.service.ISurveyItemService;
import com.city.javaEEdzy.service.ISurveyQuestionService;
import com.city.javaEEdzy.service.ISurveyService;
import com.city.javaEEdzy.service.IUserService;
import com.city.javaEEdzy.service.IWebCheckSumService;
import com.city.javaEEdzy.service.impl.surveyItemServiceImpl;
import com.city.javaEEdzy.service.impl.surveyQuestionServiceImpl;
import com.city.javaEEdzy.service.impl.surveyServiceImpl;
import com.city.javaEEdzy.service.impl.userServiceImpl;
import com.city.javaEEdzy.service.impl.webCheckSumServiceImpl;

public class ServiceFactory {
	public static IUserService getIUserService()throws Exception
	{
		return new userServiceImpl();
	}
	public static ISurveyService getISurveyService()throws Exception
	{
		return new surveyServiceImpl();
	}
	public static ISurveyItemService getISurveyItemService()throws Exception
	{
		return new surveyItemServiceImpl();
	}
	public static ISurveyQuestionService getISurveyQuestionService()throws Exception
	{
		return new surveyQuestionServiceImpl();
	}
	public static IWebCheckSumService getIWebCheckSumService()throws Exception
	{
		return new webCheckSumServiceImpl();
	}
}
