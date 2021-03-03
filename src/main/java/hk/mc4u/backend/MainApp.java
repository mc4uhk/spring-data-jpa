package hk.mc4u.backend;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hk.mc4u.backend.config.AppConfig;
import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.service.PersonService;


public class MainApp {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		PersonService personService = context.getBean(PersonService.class);

//		Person person01 = context.getBean(Person.class);

		SessionFactory sessionFactory = personService.getSessionFactory();
		log.info("{}", sessionFactory);
		personService.printAllClass();

		
		// Add Persons
		personService.add(new Person("Rahul", "Gupta", "rahulgupta@company.com"));
		personService.add(new Person("Akshay", "Sharma", "akshaysharma@company.com"));
		personService.add(new Person("Ankit", "Sarraf", "ankitsarraf@company.com"));

		// Get Persons
		List<Person> persons = personService.listPersons();
		for (Person person : persons) {
			log.info("Id = " + person.getId());
			log.info("First Name = " + person.getFirstName());
			log.info("Last Name = " + person.getLastName());
			log.info("Email = " + person.getEmail());
		}
		

		List<Person> personsA = personService.listPersonsA();
		for (Person person : personsA) {
			log.info("Id = " + person.getId());
			log.info("First Name = " + person.getFirstName());
			log.info("Last Name = " + person.getLastName());
			log.info("Email = " + person.getEmail());
		}

		

		context.close();
	}
}
