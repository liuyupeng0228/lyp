/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IProductMapper.java 
 * 包名:com.fh.shop.backend.mapper.product 
 * 创建日期:2019年1月15日下午2:40:02 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.product;

import java.util.List;

import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IProductMapper    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:40:02    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:40:02    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductMapper {

	/** <pre>addProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月24日 下午6:44:11    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月24日 下午6:44:11    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void addProduct(Product product);

	/** <pre>queryProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午2:04:12    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午2:04:12    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Product> queryProduct(Product product);

	/** <pre>deleteProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午2:21:29    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午2:21:29    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteProduct(Integer id);
	
	/** <pre>deleteBatchProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午9:36:43    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午9:36:43    
	 * 修改备注： 
	 * @param idList</pre>    
	 */
	public void deleteBatchProduct(List<Integer> idList);
	
	

	/** <pre>queryById(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午4:57:19    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午4:57:19    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product queryById(Integer id);

	/** <pre>updateProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午6:45:52    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午6:45:52    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateProduct(Product product);

	/** <pre>queryCount(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月26日 下午7:16:05    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月26日 下午7:16:05    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public Long queryCount(Product product);

    List<Product> findProductList(Product product);

	List<Product> findProductListByBrand(Integer id);

	Long queryTotal(Integer id);

    List<Product> findProductApiList();
}
