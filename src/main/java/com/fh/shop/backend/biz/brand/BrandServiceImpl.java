/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:BrandServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.brand 
 * 创建日期:2019年1月15日下午2:20:30 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.brand.IBrandMapper;
import com.fh.shop.backend.po.brand.Brand;
/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：BrandServiceImpl    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:20:30    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:20:30    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandMapper brandMapper;
	
	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#queryCount(com.fh.shop.backend.po.Brand)    
	 */
	@Override
	public Long queryCount(Brand brand) {
		return brandMapper.queryCount(brand);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#queryBrand(com.fh.shop.backend.po.Brand)    
	 */
	@Override
	public List<Brand> queryBrand(Brand brand) {
		return brandMapper.queryBrand(brand);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#addBrand(com.fh.shop.backend.po.Brand)    
	 */
	@Override
	public void addBrand(Brand brand) {
		brandMapper.addBrand(brand);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#deleteBrand(java.lang.Integer)    
	 */
	@Override
	public void deleteBrand(Integer id) {
		brandMapper.deleteBrand(id);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#deleteBatchBrand(java.lang.String)    
	 */
	@Override
	public void deleteBatchBrand(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			List<Integer> idList = new ArrayList<Integer>();
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				idList.add(Integer.parseInt(id));
			}
			brandMapper.deleteBatchBrand(idList);
		}
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#queryById(java.lang.Integer)    
	 */
	@Override
	public Brand queryByBrandId(Integer id) {
		return brandMapper.queryByBrandId(id);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#updateBrand(com.fh.shop.backend.po.Brand)    
	 */
	@Override
	public void updateBrand(Brand brand) {
		brandMapper.updateBrand(brand);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#queryBrand()    
	 */
	@Override
	public List<Brand> findBrand() {
		return brandMapper.findBrand();
	}

}

