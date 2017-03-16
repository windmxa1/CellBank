package org.imodel;

/**
 * VeUserinfo entity. @author MyEclipse Persistence Tools
 */

public class VeUserinfo implements java.io.Serializable {

	// Fields

	private VeUserinfoId id;

	// Constructors

	/** default constructor */
	public VeUserinfo() {
	}

	/** full constructor */
	public VeUserinfo(VeUserinfoId id) {
		this.id = id;
	}

	// Property accessors

	public VeUserinfoId getId() {
		return this.id;
	}

	public void setId(VeUserinfoId id) {
		this.id = id;
	}

}