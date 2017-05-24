package com.city.javaEEdzy.myclass;

import java.math.BigInteger;
import java.security.MessageDigest;

public class myMD5 {
	private String value=null;
	public myMD5(String value) 
	{
		this.value=value;
	}
	public String getMD5()
	{
		String MD5=null;
		try 
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(this.value.getBytes());
			MD5=new BigInteger(1, md.digest()).toString(16)+"Taurus";
			md.update(MD5.getBytes());
			MD5=new BigInteger(1,md.digest()).toString(16);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return MD5;
	}
}
