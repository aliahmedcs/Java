package com.magdsoft.sindbad.ws.forms;

public class NewHelp {
	String question;
	String questionTitle;
	Integer userId;
	
	public String getQuestion() {
		return question;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
