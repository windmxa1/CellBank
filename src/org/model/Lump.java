package org.model;

import java.sql.Time;
import java.util.Date;


/**
 * Lump entity. @author MyEclipse Persistence Tools
 */

public class Lump implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String iconUrl;
	private String url;
	private Integer categoryId;
	private Integer sort;
	private String createTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public Lump() {
	}

	/** minimal constructor */
	public Lump(String createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public Lump(String name, String iconUrl, String url, Integer categoryId,
			Integer sort, String createTime, Integer state) {
		this.name = name;
		this.iconUrl = iconUrl;
		this.url = url;
		this.categoryId = categoryId;
		this.sort = sort;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}