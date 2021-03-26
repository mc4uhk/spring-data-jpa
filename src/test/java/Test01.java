import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import hk.mc4u.backend.model.Pet;


public class Test01 {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void test01() throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Pet pet = new Pet(1L, "Ballaw", 12);
		log.info("hi {}",pet);
		ObjectMapper objectMapper =new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		String jsonStr = objectMapper.writeValueAsString(pet);
		
		log.info(jsonStr);
		Pet pet2 = jsonToObject(jsonStr, Pet.class);
		
		log.info(BeanUtils.describe(pet2).toString());
	}
	
	public <T> T jsonToObject(String jsonStr, Class<T> target) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper =new ObjectMapper();
		return objectMapper.readValue(jsonStr, target);
		
	}
}
