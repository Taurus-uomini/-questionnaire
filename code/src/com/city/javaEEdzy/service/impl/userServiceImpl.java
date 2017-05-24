package com.city.javaEEdzy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.city.javaEEdzy.dao.IUserDao;
import com.city.javaEEdzy.factory.DaoFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.service.IUserService;

public class userServiceImpl implements IUserService {

	@Override
	public int login(String username, String password) throws Exception {
		HashMap<Object, Object> m=new HashMap<Object, Object>();
		m.put("username", username);
		m.put("password", password);
		IUserDao iud=DaoFactory.getIUserDao();
		int id=iud.getId(m);
		return id;
	}

	@Override
	public userModel getUserInfo(int id) throws Exception {
		IUserDao iud=DaoFactory.getIUserDao();
		userModel um=iud.getOne(id);
		return um;
	}

	@Override
	public int addUser(userModel um) throws Exception {
		IUserDao iud=DaoFactory.getIUserDao();
		int re=iud.create(um);
		return re;
	}

	@Override
	public List<userModel> getAllUserList() throws Exception {
		IUserDao iud=DaoFactory.getIUserDao();
		List<userModel> list=iud.getList(null);
		return list;
	}

	@Override
	public int deleteUser(int id) throws Exception {
		IUserDao iud=DaoFactory.getIUserDao();
		int re=iud.delete(id);
		return re;
	}

	@Override
	public int editUser(userModel um) throws Exception {
		HashMap<Object, Object> m=new HashMap<Object, Object>();
		IUserDao iud=DaoFactory.getIUserDao();
		userModel umold=iud.getOne(um.getId());
		m.put("id", um.getId());
		boolean haschange=false;
		if(!um.getPsignature().equals(umold.getPsignature()))
		{
			haschange=true;
			m.put("psignature", um.getPsignature());
		}
		if(um.getPhototype()!=null)
		{
			haschange=true;
			m.put("photo", um.getPhoto());
			m.put("phototype", um.getPhototype());
		}
		int rel=1;
		if(haschange)
		{
			rel=iud.update(m);
		}
		
		return rel;
	}


	@Override
	public int checkSameUser(String username) throws Exception {
		int id=-1;
		HashMap<Object, Object> m=new HashMap<Object, Object>();
		m.put("username", username);
		IUserDao iud=DaoFactory.getIUserDao();
		id=iud.getId(m);
		return id;
	}

	@Override
	public void sendMail(String from, String to, String title, String content) throws Exception {
		Properties props=new Properties();
		props.put("mail.stmp.host", "localhost");
		props.put("mail.stmp.port", 25);
		Session session=Session.getDefaultInstance(props);
		MimeMessage message=new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(to) );
		message.setSubject(title);
		message.setSentDate(new Date());
		Multipart mp=new MimeMultipart("related");
		BodyPart bodyPart=new MimeBodyPart();
		bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=utf-8"));
		mp.addBodyPart(bodyPart);
		message.setContent(mp);
		Transport.send(message);
	}

	@Override
	public int activationUser(String link) throws Exception {
		HashMap<Object, Object> m=new HashMap<Object, Object>();
		m.put("link", link);
		IUserDao iud=DaoFactory.getIUserDao();
		int id=iud.getId(m);
		if(id!=-1)
		{
			userModel um=iud.getOne(id);
			if(um.getStatus()==0)
			{
				m=new HashMap<Object, Object>();
				m.put("id", id);
				m.put("status", 1);
				iud.update(m);
			}
			else
			{
				id=0;
			}
		}
		return id;
	}

	@Override
	public boolean editPassword(userModel um) throws Exception {
		HashMap<Object, Object> m=new HashMap<Object, Object>();
		IUserDao iud=DaoFactory.getIUserDao();
		m.put("password", um.getPassword());
		m.put("id", um.getId());
		boolean rel=iud.update(m)!=0?true:false;
		return rel;
	}

}
