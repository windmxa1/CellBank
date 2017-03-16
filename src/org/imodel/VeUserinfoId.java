package org.imodel;

/**
 * VeUserinfoId entity. @author MyEclipse Persistence Tools
 */

public class VeUserinfoId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long phone;
	private String clock;
	private String username;
	private String address;
	private String email;

	// Constructors

	/** default constructor */
	public VeUserinfoId() {
	}

	/** minimal constructor */
	public VeUserinfoId(Long id, Long phone, String clock, String username) {
		this.id = id;
		this.phone = phone;
		this.clock = clock;
		this.username = username;
	}

	/** full constructor */
	public VeUserinfoId(Long id, Long phone, String clock,
			String username, String address, String email) {
		this.id = id;
		this.phone = phone;
		this.clock = clock;
		this.username = username;
		this.address = address;
		this.email = email;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getClock() {
		return clock;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VeUserinfoId))
			return false;
		VeUserinfoId castOther = (VeUserinfoId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null && castOther.getEmail() != null && this
						.getEmail().equals(castOther.getEmail())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		return result;
	}

}