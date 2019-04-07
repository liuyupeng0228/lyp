/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:BaseController.java 
 * 包名:com.fh.shop.backend.controller 
 * 创建日期:2018年12月27日下午8:45:12 
 * Copyright (c) 2018, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller;

import com.fh.shop.backend.common.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：BaseController    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2018年12月27日 下午8:45:12    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2018年12月27日 下午8:45:12    
 * 修改备注：       
 * @version </pre>    
 */
public class BaseController {

	protected String getRootPath(HttpServletRequest request){
		String rootPath = request.getServletContext().getRealPath("/");
		return rootPath;
	}

	public FileInfo convertFileInfo(MultipartFile productImg) throws IOException {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(productImg.getOriginalFilename());
		fileInfo.setIs(productImg.getInputStream());
		fileInfo.setSize(productImg.getSize());
		return fileInfo;
	}

	protected List<FileInfo> convertFileInfoList(MultipartFile[] productChildImages) throws IOException {
		List<FileInfo> fileInfos =new ArrayList<>();
		//将判断放在循环中，防止第一个值为空而后面有值时造成的无法添加
		//若第一个会为空，而且判断在外，就会因值为空而无法进入循环进行添加
		for (MultipartFile productChildImage : productChildImages) {
			if (productChildImage.getSize()>0){
				fileInfos.add(convertFileInfo(productChildImage));
			}
		}
		return fileInfos;
	}

	public void outJson(String json,HttpServletResponse response){
		PrintWriter writer = null;
		try {
			//设置可以影响内容的类型，并且配置编码格式
			response.setContentType("application/json;charset=utf-8");
			//通过response获取writer
			writer = response.getWriter();
			//通过writer将字符串响应给客户端（目前为浏览器）
			writer.write(json);
			//刷新
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (null!=writer) {
				//关流
				writer.close();
				//使writer快速释放
				writer=null;
			}
		}
	}

}
