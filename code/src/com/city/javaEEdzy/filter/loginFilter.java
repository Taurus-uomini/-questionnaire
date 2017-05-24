package com.city.javaEEdzy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter("/*")
public class loginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public loginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		String uri=req.getRequestURI();
		boolean go_next=false;
		if(uri.equals("/javaEEdzy/login.jsp")||uri.equals("/javaEEdzy/login.do")||uri.equals("/javaEEdzy/register.jsp")||uri.equals("/javaEEdzy/register.do")||uri.equals("/javaEEdzy/user/activation.do")||uri.endsWith(".css")||uri.endsWith(".js"))
		{
			go_next=true;
		}
		else
		{
			HttpSession session=req.getSession();
			Integer userid=(Integer)session.getAttribute("userid");
			if(userid!=null)
			{
				go_next=true;
			}
			else
			{
				go_next=false;
			}
		}
		if(go_next)
		{
			chain.doFilter(request, response);
		}
		else
		{
			res.sendRedirect("/javaEEdzy/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
