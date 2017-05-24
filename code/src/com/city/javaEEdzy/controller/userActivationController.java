package com.city.javaEEdzy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class userActivationController
 */
@WebServlet("/user/activation.do")
public class userActivationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userActivationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link=request.getParameter("user");
		try 
		{
			IUserService ius=ServiceFactory.getIUserService();
			int id=ius.activationUser(link);
			if(id>0)
			{
				response.sendRedirect("../login.jsp?act=1");
			}
			else if(id==0)
			{
				response.sendRedirect("../login.jsp?act=2");
			}
			else
			{
				response.sendRedirect("../login.jsp?act=0");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
