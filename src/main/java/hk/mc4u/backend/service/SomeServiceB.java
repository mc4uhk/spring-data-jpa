package hk.mc4u.backend.service;

import org.springframework.stereotype.Service;

@Service
public class SomeServiceB {
	
	public String doSomething() {
		System.out.println("B do Something");
		return null;
	}
	
	public String doSomethingElse() {
		System.out.println("B do Something Else!!");
		return null;
	}
	
	
}
