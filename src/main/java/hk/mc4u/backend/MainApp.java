package hk.mc4u.backend;

import java.lang.invoke.MethodHandles;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hk.mc4u.backend.config.AppConfig;
import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.service.PersonService;

public class MainApp {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(new PropertiesConfiguration());

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		PersonService personService = context.getBean(PersonService.class);

//		Person person01 = context.getBean(Person.class);

		SessionFactory sessionFactory = personService.getSessionFactory();
		log.info("{}", sessionFactory);
		personService.printAllEntity();
		personService.printAllBean();
		
		boolean enable = true;
		if (enable) {
		// Add Persons
			personService.add(new Person("Rahul", "Gupta", "rahulgupta@company.com", LocalDate.now()));
			personService.add(new Person("Akshay", "Sharma", "akshaysharma@company.com", LocalDate.now()));
			personService.add(new Person("Ankit", "Sarraf", "ankitsarraf@company.com", LocalDate.now()));
		}

		try {
			personService.testTransaction();
		} catch (Exception e) {
			log.error("rollback transaction",e);
		}

		
		// Get Persons
		List<Person> persons = personService.listPersons();
		for (Person person : persons) {
			log.info("Id = " + person.getId());
			log.info("First Name = " + person.getFirstName());
			log.info("Last Name = " + person.getLastName());
			log.info("Email = " + person.getEmail());
			log.info("Day Of Birth = " + person.getDayOfBirth());
		}


		enable = true;
		if (enable) {
			log.info("------listSomePersons---------------------------------------------------");
			List<Person> personsA = personService.listSomePersons();
			for (Person person : personsA) {
				log.info("Person: {} {} {} {} {}" , person.getId(), person.getFirstName(), person.getLastName() ,person.getEmail() ,person.getDayOfBirth());
			}
		}


		enable = true;
		if (enable) {
			log.info("------listSomePersonsByEmails---------------------------------------------------");
			List<Person> personsA = personService.listSomePersonsByEmails();
			for (Person person : personsA) {
				log.info("Person: {} {} {} {} {}" , person.getId(), person.getFirstName(), person.getLastName() ,person.getEmail() ,person.getDayOfBirth());
			}
		}

		context.close();
	}
}
