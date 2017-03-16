package org.model;

/**
 * Authority entity. @author MyEclipse Persistence Tools
 */

public class Authority implements java.io.Serializable {

	// Fields

	private Long id;
	private String alias;
	private String action;

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** full constructor */
	public Authority(String alias, String action) {
		this.alias = alias;
		this.action = action;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}