/**
 * <pre>项目名称:shop-admin-maven-v2
 * 文件名称:BrandController.java
 * 包名:com.fh.shop.backend.controller.brand
 * 创建日期:2019年1月15日下午2:22:02
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre>
 */
package com.fh.shop.backend.controller.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.annotation.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.po.brand.Brand;

/**
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：BrandController    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:22:02    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:22:02    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class BrandController {

	@Autowired
	private IBrandService brandService;

	@RequestMapping("queryBrandList")
	public String queryBrandList(){
		return "brand/brandList";
	}

	@RequestMapping("queryBrand")
	@ResponseBody
	public Map queryBrand(Brand brand,Integer draw,Integer start,Integer length){
		//查询总条数
		Long totalCount = brandService.queryCount(brand);
		brand.setStartPos(start);
		brand.setPageSize(length);
		//进行分页查询
		List<Brand> brandList = brandService.queryBrand(brand);

		Map map = new HashMap();

		//将数据整合
		map.put("draw",draw);
		map.put("recordsTotal",totalCount);
		map.put("recordsFiltered",totalCount);
		map.put("data",brandList);
		return map;
	}

	@RequestMapping("toAddBrand")
	public String toAddBrand(){
		return "brand/addBrand";
	}
	@RequestMapping("addBrand")
	@LogAnnotation("品牌添加")
	public String addBrand(Brand brand){
		brandService.addBrand(brand);
		return "redirect:queryBrand.jhtml";
	}


	@RequestMapping("deleteBrand")
	@ResponseBody
	@LogAnnotation("品牌单个删除")
	public Map<String, Object> deleteBrand(Integer id,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
			brandService.deleteBrand(id);
			map.put("code", 200);
			map.put("message", "删除成功");
		return map;
	}

	@RequestMapping("deleteBatchBrand")
	@ResponseBody
	@LogAnnotation("品牌批量删除")
	public Map<String, Object> deleteBatchBrand(String ids,HttpServletResponse response){
		Map<String, Object> bmap = new HashMap<String, Object>();
			brandService.deleteBatchBrand(ids);
			bmap.put("code", 200);
			bmap.put("message", "删除成功");
 		return bmap;
	}

	@RequestMapping("queryByBrandId")
	public ModelAndView queryByBrandId(Integer id){
		Brand brand = brandService.queryByBrandId(id);
		ModelAndView mav = new ModelAndView("brand/updateBrand");
		mav.addObject("brand", brand);
		return mav;
	}
	@RequestMapping("updateBrand")
	@LogAnnotation("品牌修改")
	public String updateBrand(Brand brand){
		brandService.updateBrand(brand);
		return "redirect:queryBrand.jhtml";
	}



	//商品的品牌下拉框的查询
	@RequestMapping("findBrand")
	@ResponseBody
	public Map<String,Object> findBrand(){
		Map<String,Object> brandMap = new HashMap<String,Object>();
		List<Brand> brandList = brandService.findBrand();
		brandMap.put("code", 200);
		brandMap.put("message", "success");
		brandMap.put("data", brandList);
		return brandMap;
	}

}
