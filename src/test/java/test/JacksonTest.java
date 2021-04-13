package test;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import hk.mc4u.backend.model.Pet;

public class JacksonTest {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void test01()
			throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Pet pet = new Pet(1L, "Ballaw", 12);
		log.info("hi {}", pet);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		String jsonStr = objectMapper.writeValueAsString(pet);

		log.info(jsonStr);
		Pet pet2 = jsonToObject(jsonStr, Pet.class);

		log.info(BeanUtils.describe(pet2).toString());
	}

	@Test
	public void test02()
			throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String jsonStr = "[" + "{" + "  \"id\" : 1,\n" + "  \"name\" : \"Ballaw\",\n" + "  \"age\" : 12\n" + "}," + "{"
				+ "  \"id\" : 2,\n" + "  \"name\" : \"Cookie\",\n" + "  \"age\" : 14\n" + "}" + "]";

		log.info(jsonStr);
		Pet[] pet2 = jsonToObject(jsonStr, Pet[].class);
		log.info("{}", pet2);

		Arrays.asList(pet2).stream().map(p -> p.getName()).forEach(log::info);
		;
	}

	public <T> T jsonToObject(String jsonStr, Class<T> target) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonStr, target);

	}

}
