/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:IProductService.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2019年1月15日下午2:38:54 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import java.util.List;

import com.fh.shop.backend.api.product.ProductApiVo;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：IProductService    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:38:54    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:38:54    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductService {

	/** <pre>addProduct(添加)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月23日 下午7:38:43    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月23日 下午7:38:43    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void addProduct(Product product);

	
	/** <pre>queryCount(这里用一句话描述这个方法的作用)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月26日 下午7:15:01    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月26日 下午7:15:01    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public Long queryCount(Product product);
	
	/** <pre>queryProduct(查询)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午2:03:06    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午2:03:06    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Product> queryProduct(Product product);

	public DataTableResult buildDataTableResult(Product product, Integer draw, Integer start, Integer length, String orderDir, String beanName);

	/** <pre>deleteProduct(单个删除)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午2:21:02    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午2:21:02    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteProduct(Integer id);
	
	/** <pre>deleteBatchProduct(批量删除)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午9:29:03    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午9:29:03    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deleteBatchProduct(String ids);
	
	

	/** <pre>queryById(回显)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午4:54:55    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午4:54:55    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product queryById(Integer id);

	/** <pre>updateProduct(修改)   
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com   
	 * 创建时间：2018年12月25日 下午6:44:55    
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com  
	 * 修改时间：2018年12月25日 下午6:44:55    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateProduct(Product product, FileInfo fileInfo, List<FileInfo> fileInfos, String rootPath);


    List<Product> findProductList(Product product);


	List<Product> findProductListByBrand(Integer id);

	Long queryTotal(Integer id);

    List<ProductApiVo> findProductApiList();
}
