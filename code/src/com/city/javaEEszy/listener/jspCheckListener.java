package com.city.javaEEszy.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class jspCheckListener
 *
 */
@WebListener
public class jspCheckListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public jspCheckListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent event)  { 
    	HttpServletRequest req=(HttpServletRequest)event.getServletRequest();
    	String uri=req.getRequestURI();
    	if(uri.endsWith(".jsp"))
    	{
    		ServletContext application=event.getServletContext();
        	int check=(int)application.getAttribute("jsp");
        	application.setAttribute("jsp", ++check);
    	}
    }
	
}
