/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IBrandMapper.java 
 * 包名:com.fh.shop.backend.mapper.brand 
 * 创建日期:2019年1月15日下午2:22:30 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IBrandMapper    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:22:30    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:22:30    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandMapper {

	/** <pre>queryCount(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:30:54    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:30:54    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public Long queryCount(Brand brand);

	/** <pre>queryBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:31:09    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:31:09    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> queryBrand(Brand brand);

	/** <pre>addBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:38:41    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:38:41    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addBrand(Brand brand);

	/** <pre>deleteBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午8:57:18    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午8:57:18    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteBrand(Integer id);

	/** <pre>deleteBatchBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:05:40    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:05:40    
	 * 修改备注： 
	 * @param idList</pre>    
	 */
	public void deleteBatchBrand(List<Integer> idList);

	/** <pre>queryById(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:10:41    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:10:41    
	 * 修改备注： 
	 * @param id</pre>    
	 * @return 
	 */
	public Brand queryByBrandId(Integer id);

	/** <pre>updateBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月27日 下午9:13:06    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月27日 下午9:13:06    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updateBrand(Brand brand);

	/** <pre>findBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月29日 下午2:10:51    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月29日 下午2:10:51    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Brand> findBrand();

}
