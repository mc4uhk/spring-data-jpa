package hk.mc4u.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hk.mc4u.backend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT u FROM Person u WHERE u.firstName like 'A%' ")
	List<Person> findAllA();
	
	List<Person> findByLastNameStartingWith(String prefix);
}
