package application;

import java.io.File;

public class User {
	private String email;
	private String password;
	private String Question;
	private String questionAnswer;
	private File PFPimage;
	
	public User() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public File getPFPimage() {
		return PFPimage;
	}

	public void setPFPimage(File pFPimage) {
		PFPimage = pFPimage;
	}
}