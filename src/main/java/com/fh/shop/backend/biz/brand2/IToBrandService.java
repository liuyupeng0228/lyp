/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IToBrandService.java 
 * 包名:com.fh.shop.backend.biz.brand2 
 * 创建日期:2019年1月16日下午5:31:28 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand2;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IToBrandService    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月16日 下午5:31:28    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月16日 下午5:31:28    
 * 修改备注：       
 * @version </pre>    
 */
public interface IToBrandService {

	/** <pre>toBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午5:46:38    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午5:46:38    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> toBrand(Brand brand);

	/** <pre>addBrand2(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午7:23:02    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午7:23:02    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addBrand2(Brand brand);

	/** <pre>updateToBand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2019年1月16日 下午9:41:02    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2019年1月16日 下午9:41:02    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updateToBrand(Brand brand);


}
