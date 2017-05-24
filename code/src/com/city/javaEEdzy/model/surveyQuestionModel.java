package com.city.javaEEdzy.model;

import java.util.List;

public class surveyQuestionModel {
	private int id=0;
	private int sid=0;
	private String question=null;
	private List<surveyItemModel> sim=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<surveyItemModel> getSim() {
		return sim;
	}
	public void setSim(List<surveyItemModel> sim) {
		this.sim = sim;
	}
	
}
