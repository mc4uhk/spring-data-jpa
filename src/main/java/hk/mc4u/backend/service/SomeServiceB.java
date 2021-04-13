package hk.mc4u.backend.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SomeServiceB {

	public String doSomething(String msg) {
		log.info(msg);
		return msg;
	}

	public String doSomething() {
		return doSomething("B do Something");
	}
	
	public String doSomethingElse() {
		return doSomething("B do Something Else!!");
	}
	
	
}
