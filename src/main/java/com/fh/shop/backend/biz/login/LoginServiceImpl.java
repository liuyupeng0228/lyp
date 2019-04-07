/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:LoginServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.login 
 * 创建日期:2019年1月12日上午11:04:32 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.login.ILoginMapper;
import com.fh.shop.backend.po.user.User;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：LoginServiceImpl    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:04:32    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:04:32    
 * 修改备注：       
 * @version </pre>    
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginMapper loginMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.login.ILoginService#findUserByUserName(java.lang.String)    
	 */
	@Override
	public User findUserByUserName(String username) {
		
		return loginMapper.findUserByUserName(username);
	}

	@Override
	public String findSaltByName(String username) {
		return loginMapper.findSaltByName(username);
	}

	@Override
	public void updateUserInfo(User user) {
		loginMapper.updateUserInfo(user);
	}

	@Override
	public void addErrorCount(String username) {
		loginMapper.addErrorCount(username);
	}

	@Override
	public void updateType(String username) {
		loginMapper.updateType(username);
	}

	@Override
	public void updateErrorCount(String username) {
		loginMapper.updateErrorCount(username);
	}

}
