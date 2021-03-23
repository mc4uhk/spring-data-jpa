package hk.mc4u.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceA {
	
	@Autowired
	private SomeServiceB b;
	
	public String doSomething() {
		System.out.println("A do Something");
		b.doSomethingElse();
		return null;
	}
	
	
}
