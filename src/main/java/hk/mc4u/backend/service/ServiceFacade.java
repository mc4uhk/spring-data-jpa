package hk.mc4u.backend.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import hk.mc4u.backend.model.Person;

@Service
public class ServiceFacade {

	public void doService(Map<String, String> params, Consumer<String> otherInputHandler) {
		String name = params.get("NAME");
		otherInputHandler.accept(name);
		System.out.println("do Service");
		
		Person person = new Person();
		params.forEach((k,v) -> {
			try {
				PropertyUtils.setProperty(person, k, v);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				System.out.println(e.getMessage());
			}
		});

		try {
			System.out.println(BeanUtils.describe(person));
			System.out.println(BeanUtils.describe(person.getPet()));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			System.out.println(e.getMessage());
		}

	}
}
