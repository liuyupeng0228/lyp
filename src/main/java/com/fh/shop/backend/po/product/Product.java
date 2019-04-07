/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:Product.java 
 * 包名:com.fh.shop.backend.po 
 * 创建日期:2018年12月23日下午5:25:38 
 * Copyright (c) 2018, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.shop.backend.po.Page;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：Product    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2018年12月23日 下午5:25:38    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2018年12月23日 下午5:25:38    
 * 修改备注：实体类
 * @version </pre>    
 */
public class Product extends Page implements Serializable{
	
	private static final long serialVersionUID = 4043204239976504179L;
	private Integer id;
	private String productName;
	private Double productPrice;
	private Double minPrice;
	private Double maxPrice;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date minCreateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxCreateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date minUpdateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxUpdateTime;
	
	private Brand brand = new Brand();

	private Integer brandid;
	
	private Integer orderBy=2;

	private String imgurl;

	private String ids;

	private String sortField;

	private String sort;

	@Override
	public String getSortField() {
		return sortField;
	}

	@Override
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
	public String getSort() {
		return sort;
	}

	@Override
	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getMinCreateTime() {
		return minCreateTime;
	}
	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	public Date getMaxCreateTime() {
		return maxCreateTime;
	}
	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}
	public Date getMinUpdateTime() {
		return minUpdateTime;
	}
	public void setMinUpdateTime(Date minUpdateTime) {
		this.minUpdateTime = minUpdateTime;
	}
	public Date getMaxUpdateTime() {
		return maxUpdateTime;
	}
	public void setMaxUpdateTime(Date maxUpdateTime) {
		this.maxUpdateTime = maxUpdateTime;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}
