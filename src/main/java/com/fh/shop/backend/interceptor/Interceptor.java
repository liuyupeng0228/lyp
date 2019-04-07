/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:Interceptor.java 
 * 包名:com.fh.shop.backend.interceptor 
 * 创建日期:2019年1月12日上午11:56:04 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.util.SystemContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.po.user.User;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：Interceptor    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:56:04    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:56:04    
 * 修改备注：       
 * @version </pre>    
 */
public class Interceptor implements HandlerInterceptor{

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("项目根路径:"+request.getContextPath());
		System.out.println("方法请求路径:"+request.getRequestURI());

		String servletPath = request.getServletPath();//请求路径
		String requestURI = request.getRequestURI();//请求路径
		StringBuffer requestURL = request.getRequestURL();//http://localhost:8080:请求路径
		System.out.println(servletPath+"=="+requestURI+"=="+requestURL);

		String[] whiteList = SystemContext.WHITE_LIST.split(",");
		for (String s : whiteList) {
			//拦截放过
			if(requestURI.endsWith(s)){
				return true;
			}
		}

		//获取用户信息
		User dataUser = (User) request.getSession().getAttribute("dataUser");
		if (null != dataUser) {
			return true;
		}
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		return false;


	}
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception modelAndView)
			throws Exception {
		
	}

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)    
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
	}
}
