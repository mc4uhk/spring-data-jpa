package hk.mc4u.backend.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import hk.mc4u.backend.model.Person;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Person> findPersonByCustomSQL(Set<String> emails) {
		String sql = "select * from PERSONS where id < 10 order by first_Name";
		List<Person> persons = entityManager.createNativeQuery(sql,Person.class).getResultList();
		return 	persons;	
 
	}

}
