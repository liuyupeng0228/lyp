/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ILoginMapper.java 
 * 包名:com.fh.shop.backend.mapper.login 
 * 创建日期:2019年1月12日上午11:04:56 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.login;

import com.fh.shop.backend.po.user.User;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ILoginMapper    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:04:56    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:04:56    
 * 修改备注：       
 * @version </pre>    
 */
public interface ILoginMapper {

	/** <pre>findUserByUserName(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月12日 上午11:34:26    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月12日 上午11:34:26    
	 * 修改备注： 
	 * @param username
	 * @return</pre>    
	 */
	public User findUserByUserName(String username);

    String findSaltByName(String username);

    void updateUserInfo(User user);

    void addErrorCount(String username);

	void updateType(String username);

	void updateErrorCount(String username);
}