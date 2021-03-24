package hk.mc4u.backend.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.mc4u.backend.model.Person;

@Service
public class SomeServiceA {

	@Autowired
	private SomeServiceB b;

	public String doSomething() {
		System.out.println("A do Something");
		b.doSomethingElse();
		return null;
	}

	public void doSomethingElse() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException  {
		Person person = new Person();
		PropertyUtils.setProperty(person, "firstName", "Evie");
		PropertyUtils.setProperty(person, "pet.name", "balllawyau");
		System.out.println("Hi 123: "+person.getPet().getName());
	}

}
