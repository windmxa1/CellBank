package org.model;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */

public class UserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long userId;
	private Long roleId;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}