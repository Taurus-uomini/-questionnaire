package com.city.javaEEdzy.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.myMD5;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(
		urlPatterns = { "/login.do" })
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username").trim();
		String pas=request.getParameter("pas").trim();
		int saveme=request.getParameter("saveme")==null?0:Integer.valueOf(request.getParameter("saveme"));
		if(username.equals("")||username==null||pas.equals("")||pas==null)
		{
			response.sendRedirect("login.jsp?err=1");
		}
		else
		{
			
			try {
				myMD5 md5=new myMD5(pas);
				pas=md5.getMD5();
				IUserService ius=ServiceFactory.getIUserService();
				int id=ius.login(username, pas);
				if(id!=-1)
				{
					if(ius.getUserInfo(id).getStatus()==1)
					{
						HttpSession session=request.getSession();
						session.setAttribute("userid", id);	
						if(saveme==1)
						{
							session.setMaxInactiveInterval(7*24*60*60);
							Cookie cookie=new Cookie("JSESSIONID", session.getId());
							cookie.setMaxAge(7*24*60*60);
							response.addCookie(cookie);
						}
						
						response.sendRedirect("index.do");
					}
					else
					{
						response.sendRedirect("login.jsp?err=3");
					}
				}
				else
				{
					response.sendRedirect("login.jsp?err=2");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
