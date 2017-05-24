package com.city.javaEEdzy.model;

import java.util.List;

public class surveyModel {
	private int id=0;
	private int uid=0;
	private String title=null;
	private String icheckcolor=null;
	private String ichecktype=null;
	private int hasdelect=0;
	private List<surveyQuestionModel> sqm=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<surveyQuestionModel> getSqm() {
		return sqm;
	}
	public void setSqm(List<surveyQuestionModel> sqm) {
		this.sqm = sqm;
	}
	public String getIcheckcolor() {
		return icheckcolor;
	}
	public void setIcheckcolor(String icheckcolor) {
		this.icheckcolor = icheckcolor;
	}
	public String getIchecktype() {
		return ichecktype;
	}
	public void setIchecktype(String ichecktype) {
		this.ichecktype = ichecktype;
	}
	public int getHasdelect() {
		return hasdelect;
	}
	public void setHasdelect(int hasdelect) {
		this.hasdelect = hasdelect;
	}
	
}
