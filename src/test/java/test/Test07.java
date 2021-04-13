package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hk.mc4u.backend.model.Form;
import hk.mc4u.backend.model.Part;
import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.model.Pet;
import hk.mc4u.backend.model.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class Test07 {

	@BeforeEach
	public void init() {
	}

	@Test
	public void test01() throws JAXBException, IOException {
		marshal();
	}

	@Test
	public void test02() throws JAXBException, IOException {
		Form form = unmarshall();
		log.info("{}", form);
	}

	@AfterEach
	public void cleanup() throws InterruptedException {
	}

	public void marshal() throws JAXBException, IOException {
		Form form = new Form();

		form.setName("My Name");
		form.getPerson().setEmail("123@abc.com");
		form.getPerson().setId(123456789L);

		form.getPets().add(new Pet(123L, "A", 1));
		form.getPets().add(new Pet(456L, "B", 2));
		form.getPets().add(new Pet(789L, "C", 3));
		form.getPets().add(new Pet(777L, "D", 4));
		form.getPets().add(new Pet(999L, "E", 5));

		List<Question> questions = new ArrayList<>();
		questions.add(new Question("What's This?", "apple"));
		questions.add(new Question("Where r we going?", "home"));

		form.getPartA().setQ1(new Question("What's This?", "apple"));
  		form.getPartB().setQ1(new Question("Where r we going?", "home"));

		JAXBContext context = JAXBContext.newInstance(Form.class);
		Marshaller mar = context.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(form, new File("./form.xml"));
	}

	public Form unmarshall() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Form.class);
		Form form = (Form) context.createUnmarshaller().unmarshal(new FileReader("./form.xml"));
		return form;
	}
}
