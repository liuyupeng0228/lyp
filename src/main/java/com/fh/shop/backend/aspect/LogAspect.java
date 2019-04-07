/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:LoginAspect.java 
 * 包名:com.fh.shop.backend.aspect 
 * 创建日期:2019年1月21日下午2:06:28 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.aspect;

import com.fh.shop.backend.annotation.LogAnnotation;
import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.po.log.LogInfo;
import com.fh.shop.backend.util.SystemContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.common.WebContext;
import com.fh.shop.backend.controller.product.ProductController;
import com.fh.shop.backend.po.user.User;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：LoginAspect    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月21日 下午2:06:28    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月21日 下午2:06:28    
 * 修改备注：       
 * @version </pre>    
 */
public class LogAspect {

	@Resource(name = "logService")
	private ILogService logService;

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	public Object logInfo(ProceedingJoinPoint pjp){
		//动态获取类名
		String className = pjp.getTarget().getClass().getCanonicalName();
		//动态获取方法名
		String methodName = pjp.getSignature().getName();


		/*自定义注解*/
		//动态获取返回值类型
		//1.由连接点的签名Signature获取方法签名MethodSignature
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		//2.由方法签名获取返回值的类型
		String simpleName = ms.getReturnType().getSimpleName();
		//由方法签名获取方法
		Method method = ms.getMethod();
		//判断方法上是否有自定义注解
		String content = "";
		if (method.isAnnotationPresent(LogAnnotation.class)){
			//获取自定义注解上的信息
			LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
			//将信息的值赋给属性content
			content = annotation.value();
		}


		LogInfo logInfo = new LogInfo();

		Object result = null;
		User userInfo = null;
		LOG.info("进入{}的{}()",className,methodName);
		try {
			//返回当前的毫秒值
			long start = System.currentTimeMillis();

			//执行核心业务逻辑
			//result是方法执行后的返回值
			//局部变量需要手动刷新，在try外让局部变量=null
			result = pjp.proceed();

			//返回后的毫秒值
			long end = System.currentTimeMillis();

			userInfo = (User)WebContext.getRequest().getSession().getAttribute("dataUser");
			if (null != userInfo){
				LOG.info("{}执行{}的{}()成功",userInfo.getUsername(),className,methodName);

				logInfo.setUserName(userInfo.getUsername());
				logInfo.setLogInfo("执行"+className+"的"+methodName+"()成功");
				logInfo.setExcuteTime((int) (end-start));
				logInfo.setStatus(SystemContext.STATUS_SUCCESS);
				logInfo.setContent(content+"成功");
				logService.addLog(logInfo);
			}


		} catch (Throwable e) {
			e.printStackTrace();
			userInfo = (User)WebContext.getRequest().getSession().getAttribute("dataUser");
			if (null != userInfo){
				//进行错误的统一管理
				LOG.error("{}执行{}的{}()失败:{}",userInfo.getUsername(),className,methodName,e.getMessage());

				logInfo.setUserName(userInfo.getUsername());
				logInfo.setLogInfo("执行"+className+"的"+methodName+"()失败"+e.getMessage());
				logInfo.setExcuteTime(SystemContext.EXCUTETIME_ERROR);
				logInfo.setStatus(SystemContext.STATUS_ERROR);
				logInfo.setContent(content+"失败");
				logService.addLog(logInfo);
			}

			//*.equalsIgnoreCase()对比字符串不区分大小写
			//*.equal()区分大小写
			if (simpleName.equalsIgnoreCase("string")){
				return "/error";
			}
			else {
				return ServerResponse.error(ResponseEnum.ERROR);
			}
		}
		return result;
	}
}
