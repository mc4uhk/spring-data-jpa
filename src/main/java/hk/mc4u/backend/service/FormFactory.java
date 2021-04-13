package hk.mc4u.backend.service;

import hk.mc4u.backend.model.Form;
import hk.mc4u.backend.model.Pet;
import hk.mc4u.backend.model.Question;

public class FormFactory {

	public static Form createForm() {
		Form form = new Form();

		form.setName("My Name");
		form.getPerson().setEmail("123@abc.com");
		form.getPerson().setId(123456789L);

		form.getPets().add(new Pet(123L, "A", 1));
		form.getPets().add(new Pet(456L, "B", 2));
		form.getPets().add(new Pet(789L, "C", 3));
		form.getPets().add(new Pet(777L, "D", 4));
		form.getPets().add(new Pet(999L, "E", 5));

		form.getPartA().setQ1(new Question("What's This?", "apple"));
		form.getPartA().setQ2(new Question("What time is it?", "5pm"));
		form.getPartB().setQ1(new Question("Where r we going?", "home"));
		return form;
	}
}
