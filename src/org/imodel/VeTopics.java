package org.imodel;

/**
 * VeTopics entity. @author MyEclipse Persistence Tools
 */

public class VeTopics implements java.io.Serializable {

	// Fields

	private VeTopicsId id;

	// Constructors

	/** default constructor */
	public VeTopics() {
	}

	/** full constructor */
	public VeTopics(VeTopicsId id) {
		this.id = id;
	}

	// Property accessors

	public VeTopicsId getId() {
		return this.id;
	}

	public void setId(VeTopicsId id) {
		this.id = id;
	}

}