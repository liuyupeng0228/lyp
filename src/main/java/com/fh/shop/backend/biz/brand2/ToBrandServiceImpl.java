/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ToBrandServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.brand2 
 * 创建日期:2019年1月16日下午5:32:17 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.brand2.IToBrandMapper;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ToBrandServiceImpl    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月16日 下午5:32:17    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月16日 下午5:32:17    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class ToBrandServiceImpl implements IToBrandService {

	@Autowired
	private IToBrandMapper brandMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand2.IToBrandService#toBrand(com.fh.shop.backend.po.brand.Brand)    
	 */
	@Override
	public List<Brand> toBrand(Brand brand) {
		
		return brandMapper.toBrand(brand);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand2.IToBrandService#addBrand2(com.fh.shop.backend.po.brand.Brand)    
	 */
	@Override
	public void addBrand2(Brand brand) {
		brandMapper.addBrand2(brand);
		
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand2.IToBrandService#updateToBand(com.fh.shop.backend.po.brand.Brand)    
	 */
	@Override
	public void updateToBrand(Brand brand) {
		brandMapper.updateToBrand(brand);
		
	}
	

}
