package hk.mc4u.backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Part {

	private Question q1 = new Question();
	private Question q2 = new Question();
	private Question q3 = new Question();
	

	public Part() {
	}


	public Question getQ1() {
		return q1;
	}


	public void setQ1(Question q1) {
		this.q1 = q1;
	}


	public Question getQ2() {
		return q2;
	}


	public void setQ2(Question q2) {
		this.q2 = q2;
	}


	public Question getQ3() {
		return q3;
	}


	public void setQ3(Question q3) {
		this.q3 = q3;
	}


}
