package com.biyeseng.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理员登录过滤器
 */
public class CheckAdminFilter implements Filter {

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        //获取admin session,如果登录则可以进入后台访问.如果没有登录直接跳转到后台登录页面.
        Object admin =  httpServletRequest.getSession().getAttribute("admin");
        if(admin==null){
        	httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
        }else{
        	chain.doFilter(request, response);
        }

	}

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
