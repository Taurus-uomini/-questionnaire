package com.city.javaEEdzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;

import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.myMD5;
import com.city.javaEEdzy.service.IUserService;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class registerController
 */
@WebServlet("/register.do")
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerController() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SmartUpload upload=new SmartUpload();
		upload.initialize(config, request, response);
		try 
		{
			upload.upload();
			int hasfile=Integer.parseInt(upload.getRequest().getParameter("hasfile"));
			String username=upload.getRequest().getParameter("username").trim();
			String password=upload.getRequest().getParameter("pas").trim();
			String pasconfirm=upload.getRequest().getParameter("pasconfirm").trim();
			String email=upload.getRequest().getParameter("email").trim();
			Pattern p=Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			Matcher matcher=p.matcher(email);
			if(username.equals("")||username==null)
			{
				response.sendRedirect("register.jsp?err=1");
			}
			else if(password.equals("")||password==null)
			{
				response.sendRedirect("register.jsp?err=2");
			}
			else if(password.equals(pasconfirm)!=true)
			{
				response.sendRedirect("register.jsp?err=3");
			}
			else if(username.length()>=19)
			{
				response.sendRedirect("register.jsp?err=4");
			}
			else if(password.length()>=19)
			{
				response.sendRedirect("register.jsp?err=5");
			}
			else if(matcher.find() == false)
			{
				response.sendRedirect("register.jsp?err=6");
			}
			else
			{
				myMD5 md5=new myMD5(password);
				password=md5.getMD5();
				userModel um=new userModel();
				IUserService ius=ServiceFactory.getIUserService();
				int res=ius.checkSameUser(username);
				if(res!=-1)
				{
					response.sendRedirect("register.jsp?err=7");
				}
				else
				{
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
						um.setPhoto(photo);
						um.setPhototype(phototype);
					}
					um.setUsername(username);
					um.setPassword(password);
					um.setEmail(email);
					um.setType(2);
					um.setStatus(0);
					String link=new myMD5(username+new Date()).getMD5();
					um.setLink(link);
					String url="http://localhost:8080/javaEEdzy/user/activation.do?user="+link;
					int re=ius.addUser(um);
					String content="<p>尊敬的"+username+",我们已收到你的注册请求，请点击链接激活你的帐号</p>"+"<a href='"+url+"'>链接</a>";
					ius.sendMail("taurus-uomini@taurus.cn", email, "激活你的账户", content);
					if(re!=0)
					{
						response.sendRedirect("login.jsp");
					}
					else
					{
						response.sendRedirect("register.jsp?err=0");
					}
				}				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
