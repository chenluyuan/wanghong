package com.qingtengzanya.wanghong.dao.entity;
public class DictionaryEty extends com.ac.base.dao.BaseEntity {
	private Long id;	//主键
	private String code;	//字典code
	private String name;	//字典CN
	private String value;	//字典EN
	private Boolean status;	//状态(活跃和不活跃)
	private String description;	//描述

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
	* 得到 字典code
	* @return String
	*/
	public String getCode() {
		return this.code;
	}
	/**
	 * 设置 字典code
	 * @param code,  : String
	*/
	public void setCode(String code) {
		this.code = code;
	}

	/**
	* 得到 字典CN
	* @return String
	*/
	public String getName() {
		return this.name;
	}
	/**
	 * 设置 字典CN
	 * @param name,  : String
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* 得到 字典EN
	* @return String
	*/
	public String getValue() {
		return this.value;
	}
	/**
	 * 设置 字典EN
	 * @param value,  : String
	*/
	public void setValue(String value) {
		this.value = value;
	}

	/**
	* 得到 状态(活跃和不活跃)
	* @return 
	*/
	public Boolean getStatus() {
		return this.status;
	}
	/**
	 * 设置 状态(活跃和不活跃)
	 * @param status,  : 
	*/
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	* 得到 描述
	* @return String
	*/
	public String getDescription() {
		return this.description;
	}
	/**
	 * 设置 描述
	 * @param description,  : String
	*/
	public void setDescription(String description) {
		this.description = description;
	}

}