package test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import hk.mc4u.backend.service.SomeServiceA;
import hk.mc4u.backend.service.SomeServiceB;

@ExtendWith(MockitoExtension.class)
public class SomeServiceATest {
	
	@Mock
	private SomeServiceB serviceB;
	
	@Test
	@DisplayName("Testing SomeServiceA")
	public void test01() throws Exception {
		String testparam = "456";
		Mockito.when(serviceB.doSomething(testparam))
			.thenReturn(testparam);

		
		SomeServiceA serviceA = new SomeServiceA(serviceB);

		String result03 = serviceA.doSomething(testparam);
		
		serviceA.doSomething();
		
		Mockito.verify(serviceB, Mockito.times(1))
			.doSomethingElse();
		
		assertThat(result03)
			.isEqualTo(testparam);
	}


}
