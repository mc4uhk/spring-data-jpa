package hk.mc4u.backend.service;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

@Service
public class ServiceFacade {

	public void doService(Map<String, String> params, Consumer<String> otherInputHandler) {
		String name = params.get("NAME");
		otherInputHandler.accept(name);
		System.out.println("do Service");
	}
}
