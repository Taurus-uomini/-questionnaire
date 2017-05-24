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
import com.city.javaEEdzy.model.surveyItemModel;
import com.city.javaEEdzy.model.surveyModel;
import com.city.javaEEdzy.model.surveyQuestionModel;
import com.city.javaEEdzy.model.userModel;
import com.city.javaEEdzy.myclass.XmlToJava;
import com.city.javaEEdzy.myclass.getXmlMenu;
import com.city.javaEEdzy.service.IUserService;

/**
 * Servlet implementation class surveyAddController
 */
@WebServlet("/survey/toadd.do")
public class surveytoAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public surveytoAddController() {
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
					if(item.getUri().equals("/javaEEdzy/survey/index.do"))
					{
						item.setActive(1);
						break;
					}
				}
				request.setAttribute("menu", menu);
				request.setAttribute("userInfo", um);
				RequestDispatcher rd=request.getRequestDispatcher("add.jsp");
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
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		int userid=(int)session.getAttribute("userid");
		String title=request.getParameter("title");
		String icheckcolor=request.getParameter("icheckcolor");
		String ichecktype=request.getParameter("ichecktype");
		surveyModel sm=new surveyModel();
		sm.setTitle(title);
		sm.setUid(userid);
		sm.setIcheckcolor(icheckcolor);
		sm.setIchecktype(ichecktype);
		try {
			int sid=ServiceFactory.getISurveyService().addSurvey(sm);
			int qn=Integer.parseInt(request.getParameter("qn"));
			for(int i=1;i<=qn;++i)
			{
				String question=request.getParameter("question"+i);
				surveyQuestionModel sqm=new surveyQuestionModel();
				sqm.setSid(sid);
				sqm.setQuestion(question);
				int sqid=ServiceFactory.getISurveyQuestionService().addSurveyQuestion(sqm);
				int ic=Integer.parseInt(request.getParameter("q"+i+"checkbox"));
				int ir=Integer.parseInt(request.getParameter("q"+i+"radio"));
				if(ic!=0)
				{
					for(int j=1;j<=ic;++j)
					{
						String qcheckbox=request.getParameter("q"+i+"checkbox"+j);
						surveyItemModel sim=new surveyItemModel();
						sim.setSqid(sqid);
						sim.setType(1);
						sim.setContent(qcheckbox);
						ServiceFactory.getISurveyItemService().addSurveyItem(sim);
					}
				}
				else if(ir!=0)
				{
					for(int j=1;j<=ir;++j)
					{
						String qradio=request.getParameter("q"+i+"radio"+j);
						surveyItemModel sim=new surveyItemModel();
						sim.setSqid(sqid);
						sim.setType(2);
						sim.setContent(qradio);
						ServiceFactory.getISurveyItemService().addSurveyItem(sim);
					}
				}
			}
			response.sendRedirect("index.do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
