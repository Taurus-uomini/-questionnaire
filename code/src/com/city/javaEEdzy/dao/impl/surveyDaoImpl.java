package com.city.javaEEdzy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.city.javaEEdzy.dao.ISurveyDao;
import com.city.javaEEdzy.dao.ISurveyItemDao;
import com.city.javaEEdzy.dao.ISurveyQuestionDao;
import com.city.javaEEdzy.factory.ConnectFactory;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.surveyItemModel;
import com.city.javaEEdzy.model.surveyModel;
import com.city.javaEEdzy.model.userModel;

public class surveyDaoImpl implements ISurveyDao {

	@Override
	public int create(surveyModel sm) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		PreparedStatement ps=null;
		String sql="insert into survey(title,uid,icheckcolor,ichecktype,has_delete) values(?,?,?,?,?)";
		ps=cn.prepareStatement(sql);
		ps.setString(1, sm.getTitle());
		ps.setInt(2, sm.getUid());
		ps.setString(3, sm.getIcheckcolor());
		ps.setString(4, sm.getIchecktype());
		ps.setInt(5, sm.getHasdelect());
		int re=ps.executeUpdate();
		ps.close();
		sql="select count(id) num from survey";
		ps=cn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			re=rs.getInt("num");
		}
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int delete(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="delete from survey where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int update(int hasdelete,int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="update survey set has_delete=? where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, hasdelete);
		ps.setInt(2, id);
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public surveyModel getOne(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		surveyModel sm=null;
		ISurveyQuestionDao isqd=DaoFactory.getISurveyQuestionDao();
		if(rs.next())
		{
			sm=new surveyModel();
			int sid=rs.getInt("id");
			sm.setId(sid);
			sm.setSqm(isqd.getList(sid));
			sm.setTitle(rs.getString("title"));
			sm.setUid(rs.getInt("uid"));
			sm.setIcheckcolor(rs.getString("icheckcolor"));
			sm.setIchecktype(rs.getString("ichecktype"));
			sm.setHasdelect(rs.getInt("has_delete"));
		}
		if(sm.getHasdelect()==1)
		{
			sm=null;
		}
		rs.close();
		ps.close();
		cn.close();
		return sm;
	}

	@Override
	public List<surveyModel> getList(int uid) throws Exception {
		List<surveyModel> list=new ArrayList<surveyModel>();
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey where uid=?";
		PreparedStatement ps;
		ps=cn.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs=ps.executeQuery();
		ISurveyQuestionDao isqd=DaoFactory.getISurveyQuestionDao();
		while(rs.next())
		{
			surveyModel sm=new surveyModel();
			sm.setId(rs.getInt("id"));
			sm.setSqm(isqd.getList(sm.getId()));
			sm.setTitle(rs.getString("title"));
			sm.setUid(rs.getInt("uid"));
			sm.setIcheckcolor(rs.getString("icheckcolor"));
			sm.setIchecktype(rs.getString("ichecktype"));
			sm.setHasdelect(rs.getInt("has_delete"));
			if(sm.getHasdelect()!=1)
			{
				list.add(sm);
			}		
		}
		rs.close();
		ps.close();
		cn.close();
		return list;
	}

	@Override
	public int getId(HashMap<Object, Object> m) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select id from survey where ";
		Iterator iter=m.entrySet().iterator();
		int size=m.size();
		int i=0;
		while(iter.hasNext())
		{
			Map.Entry entry=(Map.Entry)iter.next();
			sql+=(entry.getKey()+"=?");
			if(i!=size-1)
			{
				sql+=" and ";
			}
			++i;
		}
		iter=m.entrySet().iterator();
		i=1;
		PreparedStatement ps=cn.prepareStatement(sql);
		while(iter.hasNext())
		{
			Map.Entry entry=(Map.Entry)iter.next();
			ps.setObject(i++, entry.getValue());
		}
		ResultSet rs=ps.executeQuery();
		int id=-1;
		if(rs.next())
		{
			id=rs.getInt("id");
		}
		rs.close();
		ps.close();
		cn.close();
		return id;
	}

}
