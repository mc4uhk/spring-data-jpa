package hk.mc4u.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.repository.PersonRepository;


@Service
public class PersonServiceImp implements PersonService {

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

}
