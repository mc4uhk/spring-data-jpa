package test;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Java8StreamTest {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void test01() {
		List<Map<String, String>> mapList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			Map<String, String> map1 = new HashMap<>();
			Random rand = new Random();
			map1.put("value", String.valueOf(rand.nextInt(100)));
			mapList.add(map1);
		}

			mapList.stream()
				.map(this::colorize)
				.map(m -> m.get("colorCode"))
				.forEach(log::info);
			
			log.info("\u20BF");
			log.info("😄");

	}

	public Map<String, String> colorize(Map<String, String> m) {
		Integer value = Integer.valueOf(m.get("value"));
		if (value < 30)
			m.put("colorCode", "R");
		else if (30 <= value && value < 70)
			m.put("colorCode", "Y");
		else
			m.put("colorCode", "G");

		return m;
	}

}
