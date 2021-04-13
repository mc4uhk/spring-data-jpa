package hk.mc4u.backend.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "question", "ans" })
public class Question {
	String question;
	String ans;
	
	public Question() {
	}

	public Question(String question, String ans) {
		this.question = question;
		this.ans = ans;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}

	
}
