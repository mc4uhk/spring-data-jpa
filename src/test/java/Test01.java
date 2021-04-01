

import java.io.StringWriter;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import hk.mc4u.backend.model.Pet;

public class Test01 {

	@Test
	public void test01() {
		//Reference:
		//https://github.com/thymeleaf/thymeleaf/issues/395
		String templateName = "helloTemplate";
		TemplateEngine templateEngine = new TemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("template/");
		templateResolver.setSuffix(".txt");
		templateResolver.setTemplateMode(TemplateMode.TEXT);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(0);
	    
		templateResolver.setTemplateMode("TEXT");
		templateEngine.setTemplateResolver(templateResolver);
		Context context = new Context();
		Pet pet = new Pet();
		LocalDate today = LocalDate.now();
		
		pet.setName("Cookie");
		context.setVariable("name", "<World>");
		context.setVariable("o", pet);
		context.setVariable("today", today);
		StringWriter stringWriter = new StringWriter();
		templateEngine.process(templateName, context, stringWriter);
		String result =stringWriter.toString();
		System.out.println(result);
	}
}
