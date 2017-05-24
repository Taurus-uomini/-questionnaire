package com.city.javaEEdzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.XmlToJava;
import com.city.javaEEdzy.myclass.getXmlMenu;
import com.city.javaEEdzy.service.IUserService;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class userEditController
 */
@WebServlet("/user/edit.do")
public class userEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	this.config=config;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		try 
		{
			IUserService ius=ServiceFactory.getIUserService();
			userModel userum=ius.getUserInfo(id);
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
					if(item.getUri().equals("/javaEEdzy/user/index.do"))
					{
						item.setActive(1);
						break;
					}
				}
				request.setAttribute("menu", menu);
				request.setAttribute("user", userum);
				request.setAttribute("userInfo", um);
				RequestDispatcher rd=request.getRequestDispatcher("edit.jsp");
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
		SmartUpload upload=new SmartUpload();
		upload.initialize(config, request, response);
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		try 
		{
			upload.upload();
			int id=Integer.parseInt(upload.getRequest().getParameter("uid"));
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
					if(item.getUri().equals("/javaEEdzy/user/index.do"))
					{
						item.setActive(1);
						break;
					}
				}
				request.setAttribute("menu", menu);
				request.setAttribute("userInfo", um);
				String psignature=upload.getRequest().getParameter("psignature").trim();
				userModel umnew=new userModel();
				int hasfile=Integer.parseInt(upload.getRequest().getParameter("hasfile"));
				if(hasfile==1)
				{
					String phototype=upload.getFiles().getFile(0).getContentType();
					String filename=upload.getFiles().getFile(0).getFileName();
					upload.getFiles().getFile(0).saveAs("/uploads/"+filename);
					String filerealpath=request.getServletContext().getRealPath("/uploads/"+filename);
					InputStream in=new FileInputStream(new File(filerealpath));
					byte[] b=new byte[upload.getFiles().getFile(0).getSize()]; 
					b=IOUtils.toByteArray(in);
					int i=0;
					int data=0;
					while((data=in.read())!=-1)
					{
						b[i++]=(byte)data;
					}
					Blob photo=new SerialBlob(b);
					umnew.setPhoto(photo);
					umnew.setPhototype(phototype);
				}
				umnew.setId(id);
				umnew.setPsignature(psignature);
				int re=ius.editUser(umnew);
				if(re!=0)
				{
					userModel userum=ius.getUserInfo(id);
					request.setAttribute("user", userum);
					RequestDispatcher rd=request.getRequestDispatcher("edit.jsp");
					rd.forward(request, response);
				}
				else
				{
					userModel userum=ius.getUserInfo(id);
					request.setAttribute("user", userum);
					request.setAttribute("err", 0);
					RequestDispatcher rd=request.getRequestDispatcher("edit.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
