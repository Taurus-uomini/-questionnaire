package com.city.javaEEdzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class getPhotoController
 */
@WebServlet("/user/getphoto.do")
public class getUserPhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserPhotoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		userModel um=new userModel();
		try 
		{
			IUserService ius=ServiceFactory.getIUserService();
			um=ius.getUserInfo(id);
			if(um.getPhototype()!=null)
			{
				response.setContentType(um.getPhototype());
				OutputStream out=response.getOutputStream();
				InputStream in=um.getPhoto().getBinaryStream();
				int data=0;
				while((data=in.read())!=-1)
				{
					out.write(data);
				}
				out.flush();
				out.close();
				in.close();
			}
			else
			{
				response.setContentType(um.getPhototype());
				OutputStream out=response.getOutputStream();
				InputStream in=new FileInputStream(new File(request.getServletContext().getRealPath("/uploads/default.jpg")));
				int data=0;
				while((data=in.read())!=-1)
				{
					out.write(data);
				}
				out.flush();
				out.close();
				in.close();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
