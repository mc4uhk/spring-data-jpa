package hk.mc4u.backend.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private EntityManagerFactory emf;

	@Autowired
	private PersonRepository repository;

//   @Transactional
	public void add(Person person) {
		repository.save(person);
	}

//   @Transactional(readOnly = true)
	public List<Person> listPersons() {
		return repository.findAll();
	}

	public SessionFactory getSessionFactory() {
		return emf.unwrap(SessionFactory.class);
	}
	
	public void printAllClass() {
		Metamodel classMetadata = emf.getMetamodel();
	    for (EntityType entityType : classMetadata.getEntities()) {
	        String entityName = entityType.getName();
	        log.info("Entity: " + entityName);
	    }
	    
	}

	@Override
	public List<Person> listPersonsA() {
		return repository.findByLastNameStartingWith("G");
	}
}
