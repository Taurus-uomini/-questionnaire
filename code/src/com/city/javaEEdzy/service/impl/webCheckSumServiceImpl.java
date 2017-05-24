package com.city.javaEEdzy.service.impl;

import com.city.javaEEdzy.dao.IWebCheckSumDao;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.webCheckSumModel;
import com.city.javaEEdzy.service.IWebCheckSumService;

public class webCheckSumServiceImpl implements IWebCheckSumService {

	@Override
	public int editUser(webCheckSumModel wcsm) throws Exception {
		IWebCheckSumDao iwcsd=DaoFactory.getIWebCheckSumDao();
		int re=iwcsd.update(wcsm.getType(), wcsm.getSum());
		return re;
	}

	@Override
	public webCheckSumModel getwebCheckSum(String type) throws Exception {
		IWebCheckSumDao iwcsd=DaoFactory.getIWebCheckSumDao();
		webCheckSumModel wcsm=iwcsd.getOne(type);
		return wcsm;
	}

}
