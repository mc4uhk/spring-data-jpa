package hk.mc4u.backend.model;

public class Question {
	int	id;
	String question;
	String ans;
	
	
	
	public Question() {
	}

	public Question(int id, String question, String ans) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
