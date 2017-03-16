package org.imodel;

/**
 * VeItems entity. @author MyEclipse Persistence Tools
 */

public class VeItems implements java.io.Serializable {

	// Fields

	private VeItemsId id;

	// Constructors

	/** default constructor */
	public VeItems() {
	}

	/** full constructor */
	public VeItems(VeItemsId id) {
		this.id = id;
	}

	// Property accessors

	public VeItemsId getId() {
		return this.id;
	}

	public void setId(VeItemsId id) {
		this.id = id;
	}

}