/** 
 * <pre>项目名称:shop-admin-maven-v2 
 * 文件名称:User.java 
 * 包名:com.fh.shop.backend.po.user 
 * 创建日期:2019年1月12日上午11:01:16 
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.user;

import com.fh.shop.backend.po.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：User    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月12日 上午11:01:16    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月12日 上午11:01:16    
 * 修改备注：       
 * @version </pre>    
 */
public class User extends Page implements Serializable{

	private static final long serialVersionUID = 8549838210597689548L;
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private Integer sex;
	private String headPortrait;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minBirthday;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxBirthday;

	private Double salary;
	private Double minSalary;
	private Double maxSalary;

	private Integer deptId;
	private String deptName;
	private String deptIds;
	private List<Integer> deptList = new ArrayList<>();


	private String imgcode;
	private String salt;
	private Date firstLoginTime;
	private Date lastLoginTime;
	private Integer loginCount;

	private Integer errorCount;
	private Integer userType;

	private String sortField;
	private String sort;


	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public List<Integer> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Integer> deptList) {
		this.deptList = deptList;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

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

	public Date getMinBirthday() {
		return minBirthday;
	}

	public void setMinBirthday(Date minBirthday) {
		this.minBirthday = minBirthday;
	}

	public Date getMaxBirthday() {
		return maxBirthday;
	}

	public void setMaxBirthday(Date maxBirthday) {
		this.maxBirthday = maxBirthday;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImgcode() {
		return imgcode;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
