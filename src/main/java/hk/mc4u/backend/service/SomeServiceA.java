package hk.mc4u.backend.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.mc4u.backend.model.Person;
import hk.mc4u.backend.model.Pet;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class SomeServiceA {

	@Autowired
	private SomeServiceB b;

	public SomeServiceA(SomeServiceB b) {
		this.b = b;
	}

	public String doSomething(String str) {
		log.info("A do Something");
		return b.doSomething(str);
	}

	public String doSomething() {
		log.info("A do Something");
		return b.doSomethingElse();
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
