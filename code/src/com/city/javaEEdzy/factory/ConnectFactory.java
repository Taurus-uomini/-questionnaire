package com.city.javaEEdzy.factory;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectFactory {
	public static Connection getConnect() throws Exception
	{
		Context ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("java:comp/env/mydb");
		Connection cn=ds.getConnection();
		ctx.close();
		return cn;
	}
}
