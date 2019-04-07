/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ToBrandController.java 
 * 包名:com.fh.shop.backend.controller.brand 
 * 创建日期:2019年1月16日下午5:23:04 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.brand2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.shop.backend.biz.brand2.IToBrandService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ToBrandController    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月16日 下午5:23:04    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月16日 下午5:23:04    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class ToBrandController {

	@Autowired
	private IToBrandService brandService;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ToBrandController.class);
	
	@RequestMapping("toBrand")
	public String toBrand(Brand brand,ModelMap map,Integer flag){
			List<Brand> brandList = brandService.toBrand(brand);
			map.put("brandList", brandList);
			map.put("code", 200);
			map.put("msg", "查询成功！");
		if (null == flag) {
			return "brand2/brandList";
		}
		else{
			return "brand2/brandPage";
		}
	}
	
	@RequestMapping("addBrand2")
	@ResponseBody
	public ServerResponse addBrand2(Brand brand){
		brandService.addBrand2(brand);
		LOG.info("执行com.fh.shop.backend.controller.brand2.ToBrandController的addBrand2方法成功");
		return ServerResponse.success(ResponseEnum.SUCCESS);
	}
	
	@RequestMapping("updateToBrand")
	@ResponseBody
	public ServerResponse updateToBrand(Brand brand){
		brandService.updateToBrand(brand);
		return ServerResponse.success(ResponseEnum.SUCCESS);
	}
}
