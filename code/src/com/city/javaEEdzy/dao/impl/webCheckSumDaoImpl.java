package com.city.javaEEdzy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.city.javaEEdzy.dao.IWebCheckSumDao;
import com.city.javaEEdzy.factory.ConnectFactory;
import com.city.javaEEdzy.model.webCheckSumModel;

public class webCheckSumDaoImpl implements IWebCheckSumDao {

	@Override
	public int update(String type, int sum) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="update webchecksum set sum=? where type=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, sum);
		ps.setString(2, type);
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public webCheckSumModel getOne(String type) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from webchecksum where type=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setString(1, type);
		ResultSet rs=ps.executeQuery();
		webCheckSumModel wcsm=new webCheckSumModel();
		if(rs.next())
		{
			wcsm.setType(rs.getString("type"));
			wcsm.setSum(rs.getInt("sum"));
		}
		ps.close();
		cn.close();
		return wcsm;
	}

}
