package org.imodel;

/**
 * VeCellsId entity. @author MyEclipse Persistence Tools
 */

public class VeCellsId implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private Long phone;
	private String name;
	private Integer num;
	private String serial;
	private String clock;

	// Constructors

	/** default constructor */
	public VeCellsId() {
	}

	/** minimal constructor */
	public VeCellsId(Long id, String username, Long phone, String name,
			Integer num, String serial) {
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.name = name;
		this.num = num;
		this.serial = serial;
	}

	/** full constructor */
	public VeCellsId(Long id, String username, Long phone, String name,
			Integer num, String serial, String clock) {
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.name = name;
		this.num = num;
		this.serial = serial;
		this.clock = clock;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VeCellsId))
			return false;
		VeCellsId castOther = (VeCellsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null
						&& castOther.getNum() != null && this.getNum().equals(
						castOther.getNum())))
				&& ((this.getSerial() == castOther.getSerial()) || (this
						.getSerial() != null && castOther.getSerial() != null && this
						.getSerial().equals(castOther.getSerial())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getNum() == null ? 0 : this.getNum().hashCode());
		result = 37 * result
				+ (getSerial() == null ? 0 : this.getSerial().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		return result;
	}

}