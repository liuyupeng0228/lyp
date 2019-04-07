package com.fh.shop.backend.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CopyFile {

	/**
	 * <pre>
	 * copyFile(这里用一句话描述这个方法的作用)   
	 * 创建人：onlyShangfeng 1916015158@qq.com   
	 * 创建时间：2018年11月21日 上午9:40:48    
	 * 修改人：onlyShangfeng 1916015158@qq.com      
	 * 修改时间：2018年11月21日 上午9:40:48    
	 * 修改备注： 
	 * &#64;param request  用于获取项目的绝对路径
	 * &#64;param mkName  保存文件的文件夹名称
	 * &#64;param fileData 要复制的文本内容
	 * &#64;return
	 * </pre>
	 */
	public static String copyFile(HttpServletRequest request, String mkName, CommonsMultipartFile fileData) {

		// 文件重命名
		String filename = fileData.getOriginalFilename();
		String suffix = filename.substring(filename.lastIndexOf("."));
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String newFileName = uuid + suffix;

		// 获取项目的绝对路径
		String realPath = request.getSession().getServletContext().getRealPath("/");

		// 创建目录
		File mkFile = new File(realPath + mkName);
		if (!mkFile.exists()) {
			mkFile.mkdirs();
		}

		// 完成文件的复制功能
		try {
			fileData.transferTo(new File(mkFile + "//" + newFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回图片的相对路径
		return mkName + "/" + newFileName;

	}
}
