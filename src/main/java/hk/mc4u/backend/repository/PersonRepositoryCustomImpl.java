package hk.mc4u.backend.repository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hk.mc4u.backend.model.Person;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Person> findPersonByCustomSQL(Set<String> emails) {
		String sql = "select * from PERSONS where id < 10 order by first_Name";
		@SuppressWarnings("unchecked")
		List<Person> persons = entityManager.createNativeQuery(sql,Person.class).getResultList();
		return 	persons;	
 
	}

	@Override
	public List<Object[]> findPersonByNatvieSQL(Set<String> emails) {
		String sql = "select * from PERSONS where id < 10 order by LAST_Name";
		Query q = entityManager.createNativeQuery(sql);
		 List<Object[]> resultList = q.getResultList();
		 resultList.forEach(o -> log.info("{}",o));
		return 	resultList;	
 
	}

	

}
