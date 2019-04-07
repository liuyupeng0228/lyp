/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:ProductServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2019年1月15日下午2:39:45 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fh.shop.backend.biz.images.IImagesService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.mapper.images.IImagesMapper;
import com.fh.shop.backend.po.images.Images;
import com.fh.shop.backend.util.FileUtil;
import com.fh.shop.backend.util.SystemContext;
import com.fh.shop.backend.api.product.ProductApiVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.product.IProductMapper;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ProductServiceImpl    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:39:45    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:39:45    
 * 修改备注：       
 * @version </pre>    
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductMapper productMapper;

	@Autowired
	private IImagesMapper imagesMapper;

	@Autowired
	private IImagesService imagesService;
	
	@Override
	public void addProduct(Product product) {
		
		productMapper.addProduct(product);
		//配置事务后，在此处放上异常，进行添加方法时会报错，并且进行回滚，数据库不会增加新数据
		/*throw new RuntimeException("你好啊");*/
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#queryProduct()    
	 */
	@Override
	public List<Product> queryProduct(Product product) {
	
		return productMapper.queryProduct(product);
	}

	public DataTableResult buildDataTableResult(Product product, Integer draw, Integer start, Integer length, String orderDir, String beanName) {
		Map map = new HashMap();

		//查询总条数
		Long totalCount = queryCount(product);
		//将前台传来的分页条件，赋给
		product.setStartPos(start);
		product.setPageSize(length);
		//赋值商品id
		product.getBrand().setId(product.getBrandid());
		//排序属性
		//正序或反序
		product.setSort(orderDir);
		//以哪个属性排序
		product.setSortField(beanName);
		//获取分页列表
		List<Product> productList = queryProduct(product);

		return DataTableResult.build(draw, totalCount, totalCount, productList);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#deleteProduct(java.lang.Integer)    
	 */
	@Override
	public void deleteProduct(Integer id) {
		productMapper.deleteProduct(id);
		
	}
	
	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#deleteBatchProduct(java.lang.String)    
	 */
	@Override
	public void deleteBatchProduct(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			List<Integer> idList = new ArrayList<>(); 
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				idList.add(Integer.parseInt(id));
			}
			productMapper.deleteBatchProduct(idList);
		}
		
	}
	

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#queryById(java.lang.Integer)    
	 */
	@Override
	public Product queryById(Integer id) {
		
		return productMapper.queryById(id);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#updateProduct(com.fh.shop.backend.po.Product)    
	 */
	@Override
	public void updateProduct(Product product, FileInfo fileInfo, List<FileInfo> fileInfos, String rootPath) {
		//更新产品主图
		updateMainImage(product, fileInfo, rootPath);
		//更新产品子图
		String ids = product.getIds();
		if (ids.length()>0){
			//先删除旧子图信息
			//根据id找到对应的子图信息
			ids = ids.substring(1);
			String[] idsArr = ids.split(",");
			List<Integer> idList = new ArrayList<>();
			for (String s : idsArr) {
				idList.add(Integer.parseInt(s));
			}
			List<Images> imagesPath = imagesMapper.findImagesPath(idList);
			for (Images images : imagesPath) {
				FileUtil.deleteFile(rootPath+images.getImagePath());
			}
			imagesMapper.deleteImagesById(idList);
		}

		List<Images> imagesList = new ArrayList<>();
		//在增加新子图信息
		for (FileInfo info : fileInfos) {
			//1.创建文件的输入流
			InputStream inputStream = info.getIs();
			//2.获取文件的原始名称
			String originalFilename = info.getFileName();
			//3.获取指定文件的路径
			//参数是相对于编译部署后的webapp文件来说的
			//因为编译后source类型文件不再存在，webapp也不存在，同时webapp下的文件会放在编译后的项目的根目录中，所以可以直接写文件名
			String realPath = rootPath+SystemContext.IMAGES_PATH;
			//4.利用工具类进行文件的拷贝，就是通过原始路径获取文件通过输入流输入到指定的文件目录中
			//得到拷贝后文件的名字
			String uploadFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
			//将文件的路径赋给数据库中对应的字段，保存数据用以回显使用

			Images images = new Images();
			images.setImagePath(SystemContext.IMAGES_PATH+uploadFileName);
			images.setProductId(product.getId());
			//需要与数据库交互多次
			imagesService.addImages(images);
			//只与数据库交互一次
			/*imagesList.add(images);*/
		}
		//与数据库只交互一次
		/*imagesService.addBatchImages(imagesList);*/

		//更新产品信息
		productMapper.updateProduct(product);
		
	}

	private void updateMainImage(Product product, FileInfo fileInfo, String rootPath) {
		if (fileInfo.getSize()>0) {
			//删除指定文件中旧的图片
			deleteOldImage(product, rootPath);
			//增加新图片
			addNewImage(product, fileInfo, rootPath);
		}
	}

	private void addNewImage(Product product, FileInfo fileInfo, String rootPath) {
		String imageRealPath = rootPath+SystemContext.IMAGES_PATH;
		//进行重新上传
		String uploadFileName = FileUtil.copyFile(fileInfo.getIs(), fileInfo.getFileName(), imageRealPath);
		//对数据库中的图片路径进行重新赋值
		product.setImgurl(SystemContext.IMAGES_PATH + uploadFileName);
	}

	private void deleteOldImage(Product product, String rootPath) {
		//获取数据库中的图片路径
		String imgurl = product.getImgurl();
		//获取文件路径
		String realPath = rootPath+imgurl;
		//通过文件名创建一个文件对象
		FileUtil.deleteFile(realPath);
	}

	@Override
	public List<Product> findProductList(Product product) {
		return productMapper.findProductList(product);
	}


	@Override
	public List<Product> findProductListByBrand(Integer id) {
		return productMapper.findProductListByBrand(id);
	}

	@Override
	public Long queryTotal(Integer id) {
		return productMapper.queryTotal(id);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#queryCount()    
	 */
	@Override
	public Long queryCount(Product product) {
		return productMapper.queryCount(product);
	}

	@Override
	public List<ProductApiVo> findProductApiList() {
		List<Product> productApiList = productMapper.findProductApiList();
		List<ProductApiVo> productApiVoList = new ArrayList<>();
		for (Product productApi : productApiList) {
			ProductApiVo productApiVo = new ProductApiVo();
			productApiVo.setId(productApi.getId());
			productApiVo.setProductName(productApi.getProductName());
			productApiVo.setProductPrice(productApi.getProductPrice());
			productApiVoList.add(productApiVo);
		}
		return productApiVoList;
	}

}
