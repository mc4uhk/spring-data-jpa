package hk.mc4u.backend.service;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.model.Pet;

@Service
public class SomeServiceA {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


	@Autowired
	private SomeServiceB b;

	public String doSomething() {
		System.out.println("A do Something");
		b.doSomethingElse();
		return null;
	}

	public void doSomethingElse() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		Person person = new Person();
		person.setPet(new Pet(1L,"ballawyau",12));
		PropertyUtils.setProperty(person, "firstName", "Evie");
		
		//PropertyUtils.setProperty(person, "pet.name", "ballawyau");
		
		//person.getPet().ifPresent(Pet::getName);
		String result = person.getPet().map(Pet::getName).orElse("Unkown");
		log.info("{}",result);
	}

}
