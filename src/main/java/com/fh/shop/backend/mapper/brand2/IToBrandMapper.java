/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IToBandMapper.java 
 * 包名:com.fh.shop.backend.mapper.brand 
 * 创建日期:2019年1月16日下午5:23:33 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.brand2;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IToBandMapper    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月16日 下午5:23:33    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月16日 下午5:23:33    
 * 修改备注：       
 * @version </pre>    
 */
public interface IToBrandMapper {

	/** <pre>toBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午5:48:07    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午5:48:07    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> toBrand(Brand brand);

	/** <pre>addBrand2(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午7:23:39    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午7:23:39    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addBrand2(Brand brand);

	/** <pre>updateToBand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午9:41:36    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午9:41:36    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updateToBrand(Brand brand);



}
