package hk.mc4u.backend.service;

import java.util.List;

import org.hibernate.SessionFactory;

import hk.mc4u.backend.model.Person;


public interface PersonService {
    void add(Person person);
    List<Person> listPersons();
    List<Person> listPersonsA();

	public void printAllEntity();
	public void printAllBean();
	public void testTransaction() throws Exception;

    
	public SessionFactory getSessionFactory();
	public List<Person> listSomePersons();
	public List<Person> listSomePersonsByEmails() ;
	public List<Object[]> listSomePersonByNatvieSQL(); 


}
