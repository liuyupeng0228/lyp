/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ServerResponse.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月12日上午11:09:20 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.common;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ServerResponse    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:09:20    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:09:20    
 * 修改备注：       
 * @version </pre>    
 */
public class ServerResponse {

	private int code;
	private String msg;
	private Object data;
	
	private ServerResponse(){}
	
	private ServerResponse(int code,String msg,Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	//与enum类结合生成静态方法
	//错误
	public static ServerResponse error(ResponseEnum responseEnum){
		return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
	}
	//成功(传参)
	public static ServerResponse successData(ResponseEnum responseEnum,Object data){
		return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),data);
	}

	//成功
	public static ServerResponse success(ResponseEnum responseEnum){
		return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
	}
	
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
}
