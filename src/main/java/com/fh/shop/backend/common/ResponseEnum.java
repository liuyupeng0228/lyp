/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ResponseEnum.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月12日上午11:11:56 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.common;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ResponseEnum    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:11:56    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:11:56    
 * 修改备注：       
 * @version </pre>    
 */
public enum ResponseEnum {

	ERROR_NAME_PASSWORD_IMGCODE_NULL(500,"用户名或密码或验证码不可为空！"),
	ERROR_USER_NULL(510,"用户不存在，请先注册！"),
	ERROR_PASSWORD(520,"密码错误，请重新输入密码！"),
	ERROR_PASSWORD_ONE(521,"密码错误一次，您还有两次机会！"),
	ERROR_PASSWORD_TWO(522,"密码错误两次，您还有一次机会！"),
	ERROT_PASSWORD_THREE(523,"密码错误三次，您的账号被锁定！"),
	ERROR_IMGCODE(530,"验证码错误！"),
	ERROR(-1,"系统错误，请联系管理员！"),
	ERROR_ADD(400,"已有此用户，请重新创建用户名！"),
	SUCCESS_ADD(300,"恭喜，添加成功！"),
	SUCCESS(200,"恭喜，操作成功！");

	
	private int code;
	private String msg;
	
	private ResponseEnum(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
