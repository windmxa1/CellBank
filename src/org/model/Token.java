package org.model;

import java.sql.Timestamp;

/**
 * Token entity. @author MyEclipse Persistence Tools
 */

public class Token implements java.io.Serializable {

	// Fields

	private Integer id;
	private String token;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Token() {
	}

	/** full constructor */
	public Token(String token, Timestamp time) {
		this.token = token;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}