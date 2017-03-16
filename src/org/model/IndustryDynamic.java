package org.model;

@SuppressWarnings("serial")
public class IndustryDynamic implements java.io.Serializable{
	
	// Fields

	private Integer id;
	private Integer clock;
	private String url;
	private String title;
	private String content;
	private Integer states;
	private String picture;
	private String guide;
	public IndustryDynamic(){}
	
	public IndustryDynamic(String url,String title,String content,Integer states,String picture,Integer clock,String guide){
		this.url = url;
		this.title = title;
		this.content = content;
		this.states = states;
		this.picture = picture;
		this.clock = clock;
		this.guide = guide;
	}
	
	public Integer getId() {
		return id;
	}
	public Integer getClock() {
		return clock;
	}
	public String getUrl() {
		return url;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getStates() {
		return states;
	}
	public String getPicture() {
		return picture;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setClock(Integer clock) {
		this.clock = clock;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}
}