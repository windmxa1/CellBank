package org.imodel;

/**
 * VeCells entity. @author MyEclipse Persistence Tools
 */

public class VeCells implements java.io.Serializable {

	// Fields

	private VeCellsId id;

	// Constructors

	/** default constructor */
	public VeCells() {
	}

	/** full constructor */
	public VeCells(VeCellsId id) {
		this.id = id;
	}

	// Property accessors

	public VeCellsId getId() {
		return this.id;
	}

	public void setId(VeCellsId id) {
		this.id = id;
	}

}