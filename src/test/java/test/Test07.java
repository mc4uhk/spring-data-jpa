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
import hk.mc4u.backend.service.FormFactory;
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
		Form form = FormFactory.createForm();

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
