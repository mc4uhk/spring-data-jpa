package hk.mc4u.backend;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import hk.mc4u.backend.config.AppConfig;
import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.service.OtherInputHandler;
import hk.mc4u.backend.service.PersonService;
import hk.mc4u.backend.service.ServiceFacade;
import hk.mc4u.backend.service.SomeServiceA;
import hk.mc4u.backend.service.SomeServiceB;

public class MainApp {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(new PropertiesConfiguration());

		log.info("{}", "開始");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		EntityManager em = context.getBean(EntityManager.class);
		
		SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
		
		ComboPooledDataSource ds = context.getBean(ComboPooledDataSource.class);

		int i=0;
		boolean debug = true;
		while(debug) {
			log.info("{}", i++);
			Session session = sessionFactory.openSession();
//			Session session = sessionFactory.getCurrentSession();
			try {
				//Session session = sessionFactory.getCurrentSession();
				log.info("DS1: {}", ds.getNumBusyConnectionsAllUsers());
				Transaction tx = session.beginTransaction();
				log.info("DS2: {}", ds.getNumBusyConnectionsAllUsers());
				Thread.sleep(1000);
				Query q = session.createNativeQuery("select * from PERSONS");
				List resultList = q.getResultList();
				log.info(resultList.toString());
				tx.commit();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
		}
		

		PersonService personService = context.getBean(PersonService.class);
		SomeServiceA serviceA = context.getBean(SomeServiceA.class);
		SomeServiceB serviceB = context.getBean(SomeServiceB.class);
		ServiceFacade facade = context.getBean(ServiceFacade.class);

		personService.printAllEntity();
		personService.printAllBean();

//		Person person01 = context.getBean(Person.class);

		//SessionFactory sessionFactory = personService.getSessionFactory();
		log.info("{}", sessionFactory);
		personService.printAllEntity();
		personService.printAllBean();

		serviceA.doSomething();
		serviceB.doSomethingElse();
		serviceA.doSomethingElse();

		Map<String, String> params = new HashMap<>();
		params.put("firstName", "Evie");
		params.put("lastName", "Yau");
		params.put("pet.name", "ball law");
		params.put("pet.height", "30cm");

		facade.doService(params, new OtherInputHandler());

		boolean enable = false;
		if (enable) {
			// Add Persons
			personService.add(new Person("Rahul", "Gupta", "rahulgupta@company.com", LocalDate.now()));
			personService.add(new Person("Akshay", "Sharma", "akshaysharma@company.com", LocalDate.now()));
			personService.add(new Person("Ankit", "Sarraf", "ankitsarraf@company.com", LocalDate.now()));
		}

		try {
			personService.testTransaction();
		} catch (Exception e) {
			log.error("rollback transaction", e);
		}

		enable = false;
		if (enable) {

		// Get Persons
			List<Person> persons = personService.listPersons();
			for (Person person : persons) {
				log.info("Id = " + person.getId());
				log.info("First Name = " + person.getFirstName());
				log.info("Last Name = " + person.getLastName());
				log.info("Email = " + person.getEmail());
				log.info("Day Of Birth = " + person.getDayOfBirth());
			}

			log.info("------listSomePersons---------------------------------------------------");
			List<Person> personsA = personService.listSomePersons();
			for (Person person : personsA) {
				log.info("Person: {} {} {} {} {}", person.getId(), person.getFirstName(), person.getLastName(),
						person.getEmail(), person.getDayOfBirth());
			}
		}

		enable = false;
		if (enable) {
			log.info("------listSomePersonsByEmails---------------------------------------------------");
			List<Person> personsA = personService.listSomePersonsByEmails();
			for (Person person : personsA) {
				log.info("Person: {} {} {} {} {}", person.getId(), person.getFirstName(), person.getLastName(),
						person.getEmail(), person.getDayOfBirth());
			}
		}

		enable = false;
		if (enable) {
			log.info("------listSomePersonByNatvieSQL---------------------------------------------------");
			List<Object[]> objects = personService.listSomePersonByNatvieSQL();
			for (Object[] obj : objects) {
				log.info("Obj: {} {} {} {} {}", obj[0], obj[1], obj[2], obj[3]);
			}
		}

		enable = true;
		if (enable) {
			log.info("------listSomePersonByNatvieSQLB---------------------------------------------------");
			List<Object[]> objects = personService.listSomePersonByNatvieSQLB(160);
			for (Object[] obj : objects) {
				log.info("Obj: {} {} {} {} {}", obj[0], obj[1], obj[2], obj[3]);
			}
		}

		context.close();
	}
}
