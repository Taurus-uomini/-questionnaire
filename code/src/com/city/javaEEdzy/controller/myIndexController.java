package com.city.javaEEdzy.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.XmlToJava;
import com.city.javaEEdzy.myclass.getXmlMenu;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class myIndexController
 */
@WebServlet("/my/index.do")
public class myIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		try 
		{
			IUserService ius=ServiceFactory.getIUserService();
			userModel um=ius.getUserInfo(userid);
			if(um==null)
			{
				response.sendRedirect("login.jsp");
			}
			else
			{
				JAXBContext jc=JAXBContext.newInstance(getXmlMenu.class);
				Unmarshaller us=jc.createUnmarshaller();
				getXmlMenu xml=(getXmlMenu)us.unmarshal(new File(request.getServletContext().getRealPath("/xml/menu.xml")));
				List<XmlToJava> menu=xml.getItem();
				for(XmlToJava item:menu)
				{
					if(item.getUri().equals("/javaEEdzy/my/index.do"))
					{
						item.setActive(1);
						break;
					}
				}
				request.setAttribute("menu", menu);
				request.setAttribute("userInfo", um);
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
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

	}

}
