/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:Brand.java 
 * 包名:com.fh.shop.backend.po 
 * 创建日期:2018年12月27日下午8:03:54 
 * Copyright (c) 2018, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.brand;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.shop.backend.po.Page;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：Brand    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2018年12月27日 下午8:03:54    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2018年12月27日 下午8:03:54    
 * 修改备注：       
 * @version </pre>    
 */
public class Brand extends Page implements Serializable{

	private static final long serialVersionUID = 6152471815419412348L;
	private Integer id;
	private String brandName;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	
}
