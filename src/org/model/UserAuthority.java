package org.model;

/**
 * UserAuthority entity. @author MyEclipse Persistence Tools
 */

public class UserAuthority implements java.io.Serializable {

	// Fields

	private Long id;
	private Long authorityId;
	private Long userId;

	// Constructors

	/** default constructor */
	public UserAuthority() {
	}

	/** full constructor */
	public UserAuthority(Long authorityId, Long userId) {
		this.authorityId = authorityId;
		this.userId = userId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}