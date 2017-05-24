package com.city.javaEEdzy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Saber on 2016/8/2.
 */
@WebFilter(filterName="EncodeFilter",urlPatterns={"/*"})
public class EncodeFilter implements Filter {
//    private FilterConfig config=null;
//    private ServletContext context=null;
    private String encode="utf-8";
    private boolean isNotEncode=true;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //--响应乱码解决
//        this.config=filterConfig;
//        this.context=filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding(encode);
        servletResponse.setContentType("text/html;charset="+encode);
        filterChain.doFilter(new MyHttpServletRequest((HttpServletRequest) servletRequest),servletResponse);
    }

    @Override
    public void destroy() {

    }

    private class MyHttpServletRequest extends HttpServletRequestWrapper{
        private HttpServletRequest request=null;
        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request=request;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
                try {
                    if(request.getMethod().equalsIgnoreCase("POST")){
                        request.setCharacterEncoding(encode);
                        request.getParameterMap();
                    }else if(request.getMethod().equalsIgnoreCase("GET")){
                        Map<String,String[]> map=request.getParameterMap();
                        if(isNotEncode) {
                            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                                String[] vs = entry.getValue();
                                for (int i = 0; i < vs.length; i++) {
                                    vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
                                }
                            }
                            isNotEncode=false;
                        }
                        return map;
                    }else {
                        return request.getParameterMap();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            return super.getParameterMap();
        }

        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }

        @Override
        public String getParameter(String name) {
            return getParameterValues(name)==null?null:getParameterValues(name)[0];
        }
    }
}
