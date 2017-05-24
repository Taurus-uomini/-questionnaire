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
import com.city.javaEEdzy.factory.ConnectFactory;
import com.city.javaEEdzy.model.surveyItemModel;
import com.city.javaEEdzy.model.surveyModel;
import com.city.javaEEdzy.model.userModel;

public class surveyItemDaoImpl implements ISurveyItemDao {

	@Override
	public int create(surveyItemModel sim) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		PreparedStatement ps=null;
		String sql="insert into survey_item(sqid,type,content) values(?,?,?)";
		ps=cn.prepareStatement(sql);
		ps.setInt(1, sim.getSqid());
		ps.setInt(2, sim.getType());
		ps.setString(3, sim.getContent());
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int delete(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="delete from survey_item where id=?";
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
	public surveyItemModel getOne(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey_item where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		surveyItemModel sim=null;
		if(rs.next())
		{
			sim=new surveyItemModel();
			sim.setId(rs.getInt("id"));
			sim.setSqid(rs.getInt("sqid"));
			sim.setType(rs.getInt("type"));
			sim.setContent(rs.getString("content"));
		}
		rs.close();
		ps.close();
		cn.close();
		return sim;
	}

	@Override
	public List<surveyItemModel> getList(int sqid) throws Exception {
		List<surveyItemModel> list=new ArrayList<surveyItemModel>();
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from survey_item where sqid=?";
		PreparedStatement ps;
		ps=cn.prepareStatement(sql);
		ps.setInt(1, sqid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			surveyItemModel sim=new surveyItemModel();
			sim.setId(rs.getInt("id"));
			sim.setSqid(rs.getInt("sqid"));
			sim.setType(rs.getInt("type"));
			sim.setContent(rs.getString("content"));
			list.add(sim);
		}
		rs.close();
		ps.close();
		cn.close();
		return list;
	}

	@Override
	public int getId(HashMap<Object, Object> m) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select id from survey_item where ";
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
