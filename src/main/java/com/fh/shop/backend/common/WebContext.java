/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:WebContext.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月31日下午9:34:04 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.common;

import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：WebContext    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月31日 下午9:34:04    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月31日 下午9:34:04    
 * 修改备注：       
 * @version </pre>    
 */
public class WebContext {

	private static ThreadLocal<HttpServletRequest> requestContext = new ThreadLocal<>();
	
	//以当前请求所对应的线程作为key，以request作为value进行存储；将当前请求所对应线程与request进行绑定
	public static void setRequest(HttpServletRequest request){
		requestContext.set(request);
	}
	
	//以当前请求所对应的线程作为key，并通过key获取对应value，即value,因为是当前的线程所以key不用写入到方法中
	public static HttpServletRequest getRequest(){
		return requestContext.get();
	}
	
	//从ThreadLocal中删除key对应的value，即request；解除绑定；
	public static void remove(){
		requestContext.remove();
	}
	
}
