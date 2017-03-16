package org.imodel;

/**
 * VeCommentsId entity. @author MyEclipse Persistence Tools
 */

public class VeCommentsId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long topicId;
	private String content;
	private String clock;
	private String username;

	// Constructors

	/** default constructor */
	public VeCommentsId() {
	}

	/** minimal constructor */
	public VeCommentsId(Long id, Long userid, Long topicId, String content,
			String username) {
		this.id = id;
		this.userid = userid;
		this.topicId = topicId;
		this.content = content;
		this.username = username;
	}

	/** full constructor */
	public VeCommentsId(Long id, Long userid, Long topicId, String content,
			String clock, String username) {
		this.id = id;
		this.userid = userid;
		this.topicId = topicId;
		this.content = content;
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

	public Long getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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
		if (!(other instanceof VeCommentsId))
			return false;
		VeCommentsId castOther = (VeCommentsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getTopicId() == castOther.getTopicId()) || (this
						.getTopicId() != null && castOther.getTopicId() != null && this
						.getTopicId().equals(castOther.getTopicId())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
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
				+ (getTopicId() == null ? 0 : this.getTopicId().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		return result;
	}

}