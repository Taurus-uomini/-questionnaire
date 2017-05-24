package com.city.javaEEdzy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.city.javaEEdzy.dao.IUserDao;
import com.city.javaEEdzy.factory.ConnectFactory;
import com.city.javaEEdzy.model.userModel;

public class userDaoImpl implements IUserDao {

	@Override
	public int create(userModel um) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		PreparedStatement ps=null;
		if(um.getPhototype()!=null)
		{
			String sql="insert into users(username,password,email,photo,phototype,type,status,link) values(?,?,?,?,?,?,?,?)";
			ps=cn.prepareStatement(sql);
			ps.setString(1, um.getUsername());
			ps.setString(2, um.getPassword());
			ps.setString(3, um.getEmail());
			ps.setBlob(4, um.getPhoto());
			ps.setString(5, um.getPhototype());
			ps.setInt(6, um.getType());
			ps.setInt(7, um.getStatus());
			ps.setString(8, um.getLink());
		}
		else
		{
			String sql="insert into users(username,password,email,type,status,link) values(?,?,?,?,?,?)";
			ps=cn.prepareStatement(sql);
			ps.setString(1, um.getUsername());
			ps.setString(2, um.getPassword());
			ps.setString(3, um.getEmail());
			ps.setInt(4, um.getType());
			ps.setInt(5, um.getStatus());
			ps.setString(6, um.getLink());
		}
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int delete(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="delete from users where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public int update(HashMap<Object, Object> m) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="update users set ";
		Iterator iter=m.entrySet().iterator();
		int size=m.size();
		int i=0;
		while(iter.hasNext())
		{
			Map.Entry entry=(Map.Entry)iter.next();
			if(entry.getKey()!="id")
			{
				if(i!=0)
				{
					sql+=" , ";
				}
				sql+=(entry.getKey()+"=?");
				++i;
			}
		}
		sql+=" where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		iter=m.entrySet().iterator();
		i=1;
		while(iter.hasNext())
		{
			Map.Entry entry=(Map.Entry) iter.next();
			if(entry.getKey()!="id")
			{
				ps.setObject(i++, entry.getValue());
			}
		}
		ps.setInt(i, (int)m.get("id"));
		int re=ps.executeUpdate();
		ps.close();
		cn.close();
		return re;
	}

	@Override
	public userModel getOne(int id) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from users where id=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		userModel um=null;
		if(rs.next())
		{
			um=new userModel();
			um.setId(rs.getInt("id"));
			um.setUsername(rs.getString("username"));
			um.setPassword(rs.getString("password"));
			um.setEmail(rs.getString("email"));
			um.setPhoto(rs.getBlob("photo"));
			um.setPhototype(rs.getString("phototype"));
			um.setType(rs.getInt("type"));
			um.setStatus(rs.getInt("status"));
			um.setLink(rs.getString("link"));
			um.setPsignature(rs.getString("psignature"));
		}
		rs.close();
		ps.close();
		cn.close();
		return um;
	}

	@Override
	public int getId(HashMap<Object, Object> m) throws Exception {
		Connection cn=ConnectFactory.getConnect();
		String sql="select id from users where ";
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

	@Override
	public List<userModel> getList(HashMap<Object, Object> m) throws Exception {
		List<userModel> list=new ArrayList<userModel>();
		Connection cn=ConnectFactory.getConnect();
		String sql="select * from users";
		PreparedStatement ps;
		if(m!=null)
		{
			sql+=" where ";
			int size=m.size();
			int i=0;
			Iterator iter=m.keySet().iterator();
			while(iter.hasNext())
			{
				Map.Entry entry=(Map.Entry)iter.next();
				sql+=entry.getKey()+"=?";
				if(i!=size-1)
				{
					sql+=" and ";
				}
				++i;
			}
			i=1;
			iter=m.keySet().iterator();
			ps=cn.prepareStatement(sql);
			while(iter.hasNext())
			{
				Map.Entry entry=(Map.Entry)iter.next();
				ps.setObject(i++, entry.getValue());
			}
		}
		else
		{
			ps=cn.prepareStatement(sql);
		}
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			userModel um=new userModel();
			um.setId(rs.getInt("id"));
			um.setUsername(rs.getString("username"));
			um.setPassword(rs.getString("password"));
			um.setEmail(rs.getString("email"));
			um.setPhoto(rs.getBlob("photo"));
			um.setPhototype(rs.getString("phototype"));
			um.setType(rs.getInt("type"));
			um.setStatus(rs.getInt("status"));
			um.setLink(rs.getString("link"));
			um.setPsignature(rs.getString("psignature"));
			list.add(um);
		}
		rs.close();
		ps.close();
		cn.close();
		return list;
	}

}
