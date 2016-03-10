package com.qingtengzanya.wanghong.dao.entity;

import java.util.Date;

public class WangHongInfoEty extends com.ac.base.dao.BaseEntity {
	private Long id;	//主键
	private String name;	//网红姓名
	private String wxNo;	//微信号
	private Integer wxFriendNo;	//微信好友数
	private Integer wbFriendNo;	//微博好友数
	private String wbLink;	//微博链接
	private String area;	//地区
	private String school;
	private String level;	//等级
	private String remark;	//备注
	private String type;	//类别
	private Date createDate;
	private Date updateDate;
	private Integer wxFriendNoSec;
	private Integer wbFriendNoSec;
	private String wxFriendNoStr;
	private String wbFriendNoStr;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	* 得到 主键
	* @return Integer
	*/
	public Long getId() {
		return this.id;
	}
	/**
	 * 设置 主键
	 * @param id,  : Integer
	*/
	public void setId(Long id) {
		this.id = id;
	}

	/**
	* 得到 网红姓名
	* @return String
	*/
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 网红姓名
	 * @param name,  : String
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* 得到 微信号
	* @return String
	*/
	public String getWxNo() {
		return this.wxNo;
	}
	/**
	 * 设置 微信号
	 * @param wxNo,  : String
	*/
	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}

	/**
	* 得到 微信好友数
	* @return Integer
	*/
	public Integer getWxFriendNo() {
		return this.wxFriendNo;
	}
	/**
	 * 设置 微信好友数
	 * @param wxFriendNo,  : Integer
	*/
	public void setWxFriendNo(Integer wxFriendNo) {
		this.wxFriendNo = wxFriendNo;
	}

	/**
	* 得到 微博好友数
	* @return Integer
	*/
	public Integer getWbFriendNo() {
		return this.wbFriendNo;
	}
	/**
	 * 设置 微博好友数
	 * @param wbFriendNo,  : Integer
	*/
	public void setWbFriendNo(Integer wbFriendNo) {
		this.wbFriendNo = wbFriendNo;
	}

	/**
	* 得到 微博链接
	* @return String
	*/
	public String getWbLink() {
		return this.wbLink;
	}
	/**
	 * 设置 微博链接
	 * @param wbLink,  : String
	*/
	public void setWbLink(String wbLink) {
		this.wbLink = wbLink;
	}

	/**
	* 得到 地区
	* @return String
	*/
	public String getArea() {
		return this.area;
	}
	/**
	 * 设置 地区
	 * @param area,  : String
	*/
	public void setArea(String area) {
		this.area = area;
	}

	public String getSchool() {
		return this.school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	* 得到 等级
	* @return String
	*/
	public String getLevel() {
		return this.level;
	}
	/**
	 * 设置 等级
	 * @param level,  : String
	*/
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	* 得到 备注
	* @return String
	*/
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 设置 备注
	 * @param remark,  : String
	*/
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	* 得到 类别
	* @return String
	*/
	public String getType() {
		return this.type;
	}
	/**
	 * 设置 类别
	 * @param type,  : String
	*/
	public void setType(String type) {
		this.type = type;
	}

	public Integer getWxFriendNoSec() {
		return wxFriendNoSec;
	}

	public void setWxFriendNoSec(Integer wxFriendNoSec) {
		this.wxFriendNoSec = wxFriendNoSec;
	}

	public Integer getWbFriendNoSec() {
		return wbFriendNoSec;
	}

	public void setWbFriendNoSec(Integer wbFriendNoSec) {
		this.wbFriendNoSec = wbFriendNoSec;
	}

	public String getWxFriendNoStr() {
		return wxFriendNoStr;
	}

	public void setWxFriendNoStr(String wxFriendNoStr) {
		this.wxFriendNoStr = wxFriendNoStr;
	}

	public String getWbFriendNoStr() {
		return wbFriendNoStr;
	}

	public void setWbFriendNoStr(String wbFriendNoStr) {
		this.wbFriendNoStr = wbFriendNoStr;
	}
}