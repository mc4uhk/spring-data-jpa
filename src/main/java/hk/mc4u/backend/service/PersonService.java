package hk.mc4u.backend.service;

import java.util.List;

import org.hibernate.SessionFactory;

import hk.mc4u.backend.model.Person;


public interface PersonService {
    void add(Person person);
    List<Person> listPersons();
    
	public SessionFactory getSessionFactory();
	public void printAllClass();

}
