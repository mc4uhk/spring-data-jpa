package hk.mc4u.backend.service;

import java.util.function.Consumer;

public class OtherInputHandler implements Consumer<String> {

	@Override
	public void accept(String t) {
		System.out.println("Hi Hi " + t);

	}

}
