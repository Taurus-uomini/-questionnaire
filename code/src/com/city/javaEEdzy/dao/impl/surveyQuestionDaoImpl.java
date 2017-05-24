package com.city.javaEEdzy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.city.javaEEdzy.dao.ISurveyItemDao;
import com.city.javaEEdzy.dao.ISurveyQuestionDao;
import com.city.javaEEdzy.factory.ConnectFactory;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.surveyModel;
import com.city.javaEEdzy.model.surveyQuestionModel;

public class surveyQuestionDaoImpl implements ISurveyQuestionDao {

	@Override
	public int create(surveyQuestionModel sqm) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		PreparedStatement ps=null;
		String sql="insert into survey_question(sid,question) values(?,?)";
		ps=cn.prepareStatement(sql);
		ps.setInt(1, sqm.getSid());
		ps.setString(2, sqm.getQuestion());
		int re=ps.executeUpdate();
		ps.close();
		sql="select count(id) num from survey_question";
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
		String sql="delete from survey_question where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int update(HashMap<Object, Object> m) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public surveyQuestionModel getOne(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey_question where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		surveyQuestionModel sqm=null;
		ISurveyItemDao isid=DaoFactory.getISurveyItemDao();
		if(rs.next())
		{
			sqm=new surveyQuestionModel();
			int sqid=rs.getInt("id");
			sqm.setId(sqid);
			sqm.setSim(isid.getList(sqid));
			sqm.setSid(rs.getInt("sid"));
			sqm.setQuestion(rs.getString("question"));
		}
		rs.close();
		ps.close();
		cn.close();
		return sqm;
	}

	@Override
	public List<surveyQuestionModel> getList(int sid) throws Exception {
		List<surveyQuestionModel> list=new ArrayList<surveyQuestionModel>();
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey_question where sid=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, sid);
		ResultSet rs=ps.executeQuery();
		ISurveyItemDao isid=DaoFactory.getISurveyItemDao();
		while(rs.next())
		{
			surveyQuestionModel sqm=new surveyQuestionModel();
			int sqid=rs.getInt("id");
			sqm.setId(sqid);
			sqm.setSim(isid.getList(sqid));
			sqm.setSid(rs.getInt("sid"));
			sqm.setQuestion(rs.getString("question"));
			list.add(sqm);
		}
		rs.close();
		ps.close();
		cn.close();
		return list;
	}

	@Override
	public int getId(HashMap<Object, Object> m) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select id from survey_question where ";
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
