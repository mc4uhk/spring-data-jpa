package hk.mc4u.backend.service;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.repository.PersonRepository;

/*
Reference:
https://www.baeldung.com/spring-data-derived-queries
https://www.baeldung.com/spring-data-jpa-query
*/
@Service
public class PersonServiceImp implements PersonService {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private ApplicationContext context;

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private EntityManager em;

	@Autowired
	private PersonRepository repository;

   @Transactional
	public void add(Person person) {
		repository.save(person);
	}

//	@Transactional // (readOnly = true)
	public List<Person> listPersons() {
		return repository.findAll();
	}

	public SessionFactory getSessionFactory() {
		return emf.unwrap(SessionFactory.class);
	}

	public void printAllEntity() {
		Metamodel classMetadata = emf.getMetamodel();
		for (EntityType<?> entityType : classMetadata.getEntities()) {
			String entityName = entityType.getName();
			log.info("Entity: " + entityName);
		}
	}

	public void printAllBean() {
		String[] names = context.getBeanDefinitionNames();
		Arrays.asList(names).forEach(s -> log.info("Bean: {}", s));
	}

	public List<Person> listPersonsA() {
		return repository.findByLastNameStartingWith("G");
	}

	public List<Person> listSomePersons() {
		return repository.findSomeUsersNative();
	}

	public List<Person> listSomePersonsByEmails() {
		Set<String> emails = new HashSet<>();
		return repository.findPersonByCustomSQL(emails);
	}

	public List<Object[]> listSomePersonByNatvieSQL() {
		Set<String> emails = new HashSet<>();
		return repository.findPersonByNatvieSQL(emails);
	}

	@Override
	public List<Object[]> listSomePersonByNatvieSQLB(Integer id) {
		return repository.findPersonByNatvieSQLB(id);
	}

	@Transactional(rollbackFor = { Exception.class })
	public void testTransaction() throws Exception {

		Person p = new Person();
		p.setFirstName("A new Person that should not be saved");
		repository.save(p);

		throw new Exception("Should rollback");
	}

	
	
}
