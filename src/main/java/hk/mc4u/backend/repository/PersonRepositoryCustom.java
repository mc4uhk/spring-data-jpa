package hk.mc4u.backend.repository;

import java.util.List;
import java.util.Set;

import hk.mc4u.backend.model.Person;

public interface PersonRepositoryCustom  {

	List<Person> findPersonByCustomSQL(Set<String> emails);
	
	List<Object[]> findPersonByNatvieSQL(Set<String> emails);
	
	
}
