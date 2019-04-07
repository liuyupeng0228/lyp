/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:AspectInfo.java 
 * 包名:com.fh.shop.backend.aspect 
 * 创建日期:2019年1月20日上午7:46:28 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fh.shop.backend.po.user.User;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：AspectInfo    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月20日 上午7:46:28    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月20日 上午7:46:28    
 * 修改备注：       
 * @version </pre>    
 */
public class AspectInfo {

	//因为需要用到返回值，而返回值的类型不固定，所以用Object来囊括所有返回值
	public Object logInfo(ProceedingJoinPoint pjp){
		HttpServletRequest request  = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		//获取用户登陆信息
		User dataUser = (User) request.getSession().getAttribute("dataUser");
		if(dataUser!=null){
			//获取用户名
			String UserName=dataUser.getUsername();
			System.out.println("访问用户:"+UserName);
			//获取ip
		   String ip = getIpAddress(request);
	       System.out.println("访问的用户的IP地址:" + ip);
		}
		
		System.out.println("方法执行前记录！");
		Object result = null;
		try {
			//局部变量需要手动刷新，在try外让局部变量=null
			result = pjp.proceed();
			System.out.println("方法执行后记录！");
			//获取类名
			String typeName = pjp.getTarget().getClass().getName();
			
			System.out.println("类名："+typeName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//由此方法可以获取IP地址
	protected String getIpAddress(HttpServletRequest request ) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		        ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
}
