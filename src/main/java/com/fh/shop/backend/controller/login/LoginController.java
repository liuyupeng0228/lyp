/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:LoginController.java 
 * 包名:com.fh.shop.backend.controller.login 
 * 创建日期:2019年1月12日上午11:03:40 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fh.shop.backend.annotation.LogAnnotation;
import com.fh.shop.backend.util.MD5Util;
import com.fh.shop.backend.util.SystemContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.shop.backend.biz.login.ILoginService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：LoginController    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:03:40    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:03:40    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {

	@Resource(name="loginService")
	private ILoginService loginService;
	
	@RequestMapping("login")
	@ResponseBody
	@LogAnnotation("登录")
	public ServerResponse login(User user,HttpServletRequest request) throws ParseException {

		User dataUser = null;
		try {
			String username = user.getUsername();
			String password = user.getPassword();

			//从数据库中获取盐，与前台传过来的值进行结合，之后再次进行MD5加密
			String s = loginService.findSaltByName(username);
			String passwordMD5 = MD5Util.getStringMD5(password + s);

			String imgcode = (String) request.getSession().getAttribute(SystemContext.IMGCODE);

			//判断用户名，密码，验证码是否为空
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(user.getImgcode())) {
				return ServerResponse.error(ResponseEnum.ERROR_NAME_PASSWORD_IMGCODE_NULL);
			}

			//判断验证码是否正确
			if (!imgcode.equals(user.getImgcode())){
				return ServerResponse.error(ResponseEnum.ERROR_IMGCODE);
			}

			//根据前台传来的用户名，进行数据的查询
			dataUser = loginService.findUserByUserName(username);

			//如果信息为空，则用户名不存在
			if (null == dataUser) {
				return ServerResponse.error(ResponseEnum.ERROR_USER_NULL);
			}

			//若用户名存在，就进行密码的判断
			if (!passwordMD5.equals(dataUser.getPassword())) {
				loginService.addErrorCount(dataUser.getUsername());
				//密码错误时错误次数就会+1
				User erorCount = loginService.findUserByUserName(username);
				if (erorCount.getErrorCount() == 1){
					return ServerResponse.error(ResponseEnum.ERROR_PASSWORD_ONE);
				}
				if(erorCount.getErrorCount() == 2){
					return ServerResponse.error(ResponseEnum.ERROR_PASSWORD_TWO);
				}
				if (erorCount.getErrorCount() == 3){
					//若密码错误三次，就将用户状态改为1，使用户被锁定
					loginService.updateType(erorCount.getUsername());
					return ServerResponse.error(ResponseEnum.ERROT_PASSWORD_THREE);
				}
				return ServerResponse.error(ResponseEnum.ERROR_PASSWORD);
			}

			//不是同一天，就修改错误次数
			if (dataUser.getUserType() == SystemContext.USER_TYPE){
				if(dataUser.getLastLoginTime() != null){
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String loginFormat = simpleDateFormat.format(dataUser.getLastLoginTime());
					long logTime = simpleDateFormat.parse(loginFormat).getTime();
					Date date = new Date();
					String datsFormat = simpleDateFormat.format(date);
					long dataTime = simpleDateFormat.parse(datsFormat).getTime();
					if (logTime != dataTime){
						loginService.updateErrorCount(dataUser.getUsername());
					}
				}
				return ServerResponse.error(ResponseEnum.ERROT_PASSWORD_THREE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.error(ResponseEnum.ERROR);
		}

		//登录时间的替换
		if (dataUser.getLastLoginTime() == null){
			user.setLoginCount(dataUser.getLoginCount()+1);
			user.setFirstLoginTime(new Date());
			user.setLastLoginTime(new Date());
		}
		else {
			user.setLoginCount(dataUser.getLoginCount()+1);
			user.setFirstLoginTime(dataUser.getLastLoginTime());
			user.setLastLoginTime(new Date());
		}
		//一天后进行次数的归零
		if(dataUser.getLastLoginTime() != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String loginFormat = simpleDateFormat.format(dataUser.getLastLoginTime());
            long logTime = simpleDateFormat.parse(loginFormat).getTime();
            Date date = new Date();
            String datsFormat = simpleDateFormat.format(date);
            long dataTime = simpleDateFormat.parse(datsFormat).getTime();
            if (logTime != dataTime){

                user.setLoginCount(1);
				loginService.updateUserInfo(user);

				dataUser.setFirstLoginTime(user.getFirstLoginTime());
				dataUser.setLoginCount(1);
				request.getSession().setAttribute("dataUser", dataUser);
				return ServerResponse.success(ResponseEnum.SUCCESS);
            }
        }

        //修改密码错误次数
		loginService.updateErrorCount(dataUser.getUsername());

        loginService.updateUserInfo(user);

		dataUser.setFirstLoginTime(user.getFirstLoginTime());
		dataUser.setLoginCount(user.getLoginCount());
		request.getSession().setAttribute("dataUser", dataUser);
		return ServerResponse.success(ResponseEnum.SUCCESS);
	}
}
