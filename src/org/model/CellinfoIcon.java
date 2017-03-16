package org.model;

/**
 * CellinfoIcon entity. @author MyEclipse Persistence Tools
 */

public class CellinfoIcon implements java.io.Serializable {

	// Fields

	private Long id;
	private String iconUrl;
	private String name;
	private Boolean isIos;
	private String createTime;

	// Constructors

	/** default constructor */
	public CellinfoIcon() {
	}

	/** minimal constructor */
	public CellinfoIcon(String name, Boolean isIos, String createTime) {
		this.name = name;
		this.isIos = isIos;
		this.createTime = createTime;
	}

	/** full constructor */
	public CellinfoIcon(String iconUrl, String name, Boolean isIos,
			String createTime) {
		this.iconUrl = iconUrl;
		this.name = name;
		this.isIos = isIos;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsIos() {
		return this.isIos;
	}

	public void setIsIos(Boolean isIos) {
		this.isIos = isIos;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}