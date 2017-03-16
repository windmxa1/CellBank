package org.model;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Long id;
	private String role;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String role) {
		this.role = role;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}