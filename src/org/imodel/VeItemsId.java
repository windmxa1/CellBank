package org.imodel;

/**
 * VeItemsId entity. @author MyEclipse Persistence Tools
 */

public class VeItemsId implements java.io.Serializable {

	// Fields

	private Long id;
	private String LLowAlarm;
	private String LHighAlarm;
	private String THAlarm;
	private String THhAlarm;
	private String level;
	private String temperature1;
	private String temperature2;
	private String temperature3;
	private String temperature4;
	private String vesselTemp;
	private String sampleTemp;
	private String serial;
	private String clock;
	private String address;

	// Constructors

	/** default constructor */
	public VeItemsId() {
	}

	/** minimal constructor */
	public VeItemsId(Long id, String LLowAlarm, String LHighAlarm,
			String THAlarm, String THhAlarm, String level, String temperature1,
			String temperature2, String temperature3, String temperature4,
			String vesselTemp, String sampleTemp, String serial) {
		this.id = id;
		this.LLowAlarm = LLowAlarm;
		this.LHighAlarm = LHighAlarm;
		this.THAlarm = THAlarm;
		this.THhAlarm = THhAlarm;
		this.level = level;
		this.temperature1 = temperature1;
		this.temperature2 = temperature2;
		this.temperature3 = temperature3;
		this.temperature4 = temperature4;
		this.vesselTemp = vesselTemp;
		this.sampleTemp = sampleTemp;
		this.serial = serial;
	}

	/** full constructor */
	public VeItemsId(Long id, String LLowAlarm, String LHighAlarm,
			String THAlarm, String THhAlarm, String level, String temperature1,
			String temperature2, String temperature3, String temperature4,
			String vesselTemp, String sampleTemp, String serial, String clock,
			String address) {
		this.id = id;
		this.LLowAlarm = LLowAlarm;
		this.LHighAlarm = LHighAlarm;
		this.THAlarm = THAlarm;
		this.THhAlarm = THhAlarm;
		this.level = level;
		this.temperature1 = temperature1;
		this.temperature2 = temperature2;
		this.temperature3 = temperature3;
		this.temperature4 = temperature4;
		this.vesselTemp = vesselTemp;
		this.sampleTemp = sampleTemp;
		this.serial = serial;
		this.clock = clock;
		this.address = address;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLLowAlarm() {
		return this.LLowAlarm;
	}

	public void setLLowAlarm(String LLowAlarm) {
		this.LLowAlarm = LLowAlarm;
	}

	public String getLHighAlarm() {
		return this.LHighAlarm;
	}

	public void setLHighAlarm(String LHighAlarm) {
		this.LHighAlarm = LHighAlarm;
	}

	public String getTHAlarm() {
		return this.THAlarm;
	}

	public void setTHAlarm(String THAlarm) {
		this.THAlarm = THAlarm;
	}

	public String getTHhAlarm() {
		return this.THhAlarm;
	}

	public void setTHhAlarm(String THhAlarm) {
		this.THhAlarm = THhAlarm;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTemperature1() {
		return this.temperature1;
	}

	public void setTemperature1(String temperature1) {
		this.temperature1 = temperature1;
	}

	public String getTemperature2() {
		return this.temperature2;
	}

	public void setTemperature2(String temperature2) {
		this.temperature2 = temperature2;
	}

	public String getTemperature3() {
		return this.temperature3;
	}

	public void setTemperature3(String temperature3) {
		this.temperature3 = temperature3;
	}

	public String getTemperature4() {
		return this.temperature4;
	}

	public void setTemperature4(String temperature4) {
		this.temperature4 = temperature4;
	}

	public String getVesselTemp() {
		return this.vesselTemp;
	}

	public void setVesselTemp(String vesselTemp) {
		this.vesselTemp = vesselTemp;
	}

	public String getSampleTemp() {
		return this.sampleTemp;
	}

	public void setSampleTemp(String sampleTemp) {
		this.sampleTemp = sampleTemp;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VeItemsId))
			return false;
		VeItemsId castOther = (VeItemsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getLLowAlarm() == castOther.getLLowAlarm()) || (this
						.getLLowAlarm() != null
						&& castOther.getLLowAlarm() != null && this
						.getLLowAlarm().equals(castOther.getLLowAlarm())))
				&& ((this.getLHighAlarm() == castOther.getLHighAlarm()) || (this
						.getLHighAlarm() != null
						&& castOther.getLHighAlarm() != null && this
						.getLHighAlarm().equals(castOther.getLHighAlarm())))
				&& ((this.getTHAlarm() == castOther.getTHAlarm()) || (this
						.getTHAlarm() != null && castOther.getTHAlarm() != null && this
						.getTHAlarm().equals(castOther.getTHAlarm())))
				&& ((this.getTHhAlarm() == castOther.getTHhAlarm()) || (this
						.getTHhAlarm() != null
						&& castOther.getTHhAlarm() != null && this
						.getTHhAlarm().equals(castOther.getTHhAlarm())))
				&& ((this.getLevel() == castOther.getLevel()) || (this
						.getLevel() != null && castOther.getLevel() != null && this
						.getLevel().equals(castOther.getLevel())))
				&& ((this.getTemperature1() == castOther.getTemperature1()) || (this
						.getTemperature1() != null
						&& castOther.getTemperature1() != null && this
						.getTemperature1().equals(castOther.getTemperature1())))
				&& ((this.getTemperature2() == castOther.getTemperature2()) || (this
						.getTemperature2() != null
						&& castOther.getTemperature2() != null && this
						.getTemperature2().equals(castOther.getTemperature2())))
				&& ((this.getTemperature3() == castOther.getTemperature3()) || (this
						.getTemperature3() != null
						&& castOther.getTemperature3() != null && this
						.getTemperature3().equals(castOther.getTemperature3())))
				&& ((this.getTemperature4() == castOther.getTemperature4()) || (this
						.getTemperature4() != null
						&& castOther.getTemperature4() != null && this
						.getTemperature4().equals(castOther.getTemperature4())))
				&& ((this.getVesselTemp() == castOther.getVesselTemp()) || (this
						.getVesselTemp() != null
						&& castOther.getVesselTemp() != null && this
						.getVesselTemp().equals(castOther.getVesselTemp())))
				&& ((this.getSampleTemp() == castOther.getSampleTemp()) || (this
						.getSampleTemp() != null
						&& castOther.getSampleTemp() != null && this
						.getSampleTemp().equals(castOther.getSampleTemp())))
				&& ((this.getSerial() == castOther.getSerial()) || (this
						.getSerial() != null && castOther.getSerial() != null && this
						.getSerial().equals(castOther.getSerial())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getLLowAlarm() == null ? 0 : this.getLLowAlarm().hashCode());
		result = 37
				* result
				+ (getLHighAlarm() == null ? 0 : this.getLHighAlarm()
						.hashCode());
		result = 37 * result
				+ (getTHAlarm() == null ? 0 : this.getTHAlarm().hashCode());
		result = 37 * result
				+ (getTHhAlarm() == null ? 0 : this.getTHhAlarm().hashCode());
		result = 37 * result
				+ (getLevel() == null ? 0 : this.getLevel().hashCode());
		result = 37
				* result
				+ (getTemperature1() == null ? 0 : this.getTemperature1()
						.hashCode());
		result = 37
				* result
				+ (getTemperature2() == null ? 0 : this.getTemperature2()
						.hashCode());
		result = 37
				* result
				+ (getTemperature3() == null ? 0 : this.getTemperature3()
						.hashCode());
		result = 37
				* result
				+ (getTemperature4() == null ? 0 : this.getTemperature4()
						.hashCode());
		result = 37
				* result
				+ (getVesselTemp() == null ? 0 : this.getVesselTemp()
						.hashCode());
		result = 37
				* result
				+ (getSampleTemp() == null ? 0 : this.getSampleTemp()
						.hashCode());
		result = 37 * result
				+ (getSerial() == null ? 0 : this.getSerial().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		return result;
	}

}