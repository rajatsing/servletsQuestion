package com.redhat;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/jenkins")
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
	        Enumeration<String> headerNames = req.getHeaderNames();
	        res.setContentType("text/html");
	        
	        while (headerNames.hasMoreElements()) {
	 
	            String headerName = headerNames.nextElement();
	 
	            Enumeration<String> headers = req.getHeaders(headerName);
	            while (headers.hasMoreElements()) {
	                String headerValue = headers.nextElement();
	                if (headerName.equals("youshallnotpass") && headerValue.equals("true")) {
	                //	System.out.println("Error 403!! you shall Not pas");
	                	res.sendError(HttpServletResponse.SC_FORBIDDEN, "You shall Not Pass");
	                }
	            }
	 
	        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}