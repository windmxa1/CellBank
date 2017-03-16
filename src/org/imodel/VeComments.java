package org.imodel;

/**
 * VeComments entity. @author MyEclipse Persistence Tools
 */

public class VeComments implements java.io.Serializable {

	// Fields

	private VeCommentsId id;

	// Constructors

	/** default constructor */
	public VeComments() {
	}

	/** full constructor */
	public VeComments(VeCommentsId id) {
		this.id = id;
	}

	// Property accessors

	public VeCommentsId getId() {
		return this.id;
	}

	public void setId(VeCommentsId id) {
		this.id = id;
	}

}