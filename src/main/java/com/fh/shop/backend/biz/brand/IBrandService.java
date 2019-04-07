/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IBrandService.java 
 * 包名:com.fh.shop.backend.biz.brand 
 * 创建日期:2019年1月15日下午2:20:04 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IBrandService    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:20:04    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:20:04    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandService {

	/** <pre>queryCount(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:29:02    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:29:02    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public Long queryCount(Brand brand);

	/** <pre>queryBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:29:19    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:29:19    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> queryBrand(Brand brand);

	/** <pre>addBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:38:07    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:38:07    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addBrand(Brand brand);

	/** <pre>deleteBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:56:50    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:56:50    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteBrand(Integer id);

	/** <pre>deleteBatchBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:01:34    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:01:34    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deleteBatchBrand(String ids);

	/** <pre>queryById(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:10:16    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:10:16    
	 * 修改备注： 
	 * @param id</pre>    
	 * @return 
	 */
	public Brand queryByBrandId(Integer id);

	/** <pre>updateBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:12:42    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:12:42    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updateBrand(Brand brand);


	/** <pre>findBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月29日 下午2:10:30    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月29日 下午2:10:30    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Brand> findBrand();

}

