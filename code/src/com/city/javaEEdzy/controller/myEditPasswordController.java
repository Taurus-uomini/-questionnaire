package com.city.javaEEdzy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.myMD5;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class myEditPasswordController
 */
@WebServlet("/my/editpassword.do")
public class myEditPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myEditPasswordController() {
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
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String renewpassword=request.getParameter("renewpassword");
		int re=0;
		if(oldpassword==null||oldpassword.equals(""))
		{
			re=-1;
		}
		else if(newpassword==null||newpassword.equals(""))
		{
			re=-2;
		}
		else if(renewpassword==null||renewpassword.equals(""))
		{
			re=-3;
		}
		else if(!renewpassword.equals(newpassword))
		{
			re=-4;
		}
		else
		{
			IUserService ius;
			try 
			{
				ius = ServiceFactory.getIUserService();
				String password=ius.getUserInfo(userid).getPassword();
				myMD5 md5=new myMD5(oldpassword);
				oldpassword=md5.getMD5();
				if(!password.equals(oldpassword))
				{
					re=-5;
				}
				else
				{
					userModel um=new userModel();
					um.setId(userid);
					md5=new myMD5(newpassword);
					newpassword=md5.getMD5();
					um.setPassword(newpassword);
					re=ius.editPassword(um)==true?1:0;
				}
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter out=response.getWriter();
		out.println(re);
		out.flush();
		out.close();
	}

}
