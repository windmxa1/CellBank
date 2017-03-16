package org.imodel;

/**
 * VeTopicsId entity. @author MyEclipse Persistence Tools
 */

public class VeTopicsId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String title;
	private String content;
	private String type;
	private String clock;
	private String username;

	// Constructors

	/** default constructor */
	public VeTopicsId() {
	}

	/** minimal constructor */
	public VeTopicsId(Long id, Long userid, String title, String content,
			String username) {
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.username = username;
	}

	/** full constructor */
	public VeTopicsId(Long id, Long userid, String title, String content,
			String type, String clock, String username) {
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.type = type;
		this.clock = clock;
		this.username = username;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VeTopicsId))
			return false;
		VeTopicsId castOther = (VeTopicsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		return result;
	}

}