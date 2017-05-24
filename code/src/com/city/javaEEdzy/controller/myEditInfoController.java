package com.city.javaEEdzy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.List;

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
 * Servlet implementation class myEditInfoController
 */
@WebServlet("/my/editinfo.do")
public class myEditInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myEditInfoController() {
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
		SmartUpload upload=new SmartUpload();
		upload.initialize(config, request, response);
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		try 
		{
			upload.upload();
			IUserService ius=ServiceFactory.getIUserService();
			userModel um=ius.getUserInfo(userid);
			if(um==null)
			{
				response.sendRedirect("login.jsp");
			}
			else
			{
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
				umnew.setId(userid);
				umnew.setPsignature(psignature);
				int re=ius.editUser(umnew);
				PrintWriter out=response.getWriter();
				if(re!=0)
				{
					out.write("{'ret':true}");
				}
				else
				{
					out.write("{'ret':false}");
				}
				out.flush();
				out.close();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
