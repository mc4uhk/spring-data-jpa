package hk.mc4u.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hk.mc4u.backend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
