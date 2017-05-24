package com.city.javaEEszy.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.city.javaEEdzy.dao.impl.webCheckSumDaoImpl;
import com.city.javaEEdzy.factory.ServiceFactory;
import com.city.javaEEdzy.model.webCheckSumModel;
import com.city.javaEEdzy.service.IWebCheckSumService;

/**
 * Application Lifecycle Listener implementation class servletContextListener
 *
 */
@WebListener
public class servletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public servletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
		try {
			IWebCheckSumService iwcss = ServiceFactory.getIWebCheckSumService();
			webCheckSumModel wcsmjsp=new webCheckSumModel();
			wcsmjsp.setType("jsp");
			wcsmjsp.setSum((int)event.getServletContext().getAttribute("jsp"));
			iwcss.editUser(wcsmjsp);
			webCheckSumModel wcsmservlet=new webCheckSumModel();
			wcsmservlet.setType("servlet");
			wcsmservlet.setSum((int)event.getServletContext().getAttribute("servlet"));
			iwcss.editUser(wcsmservlet);
			webCheckSumModel wcsmloginnum=new webCheckSumModel();
			wcsmloginnum.setType("loginnum");
			wcsmloginnum.setSum((int)event.getServletContext().getAttribute("loginnum"));
			iwcss.editUser(wcsmloginnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
        	try {
				IWebCheckSumService iwcss=ServiceFactory.getIWebCheckSumService();
				webCheckSumModel wcsmjsp=iwcss.getwebCheckSum("jsp");
				webCheckSumModel wcsmservlet=iwcss.getwebCheckSum("servlet");
				webCheckSumModel wcsmloginnum=iwcss.getwebCheckSum("loginnum");
				ServletContext application=event.getServletContext();
				application.setAttribute("jsp", wcsmjsp.getSum());
				application.setAttribute("servlet", wcsmservlet.getSum());
				application.setAttribute("loginnum", wcsmloginnum.getSum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
    }
	
}
