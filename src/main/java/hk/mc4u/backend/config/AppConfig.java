package hk.mc4u.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("hk.mc4u.backend.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "hk.mc4u.backend")
public class AppConfig {

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");

		String password = System.getenv("PASSWORD");
		
		Map<String, String> result = new HashMap<String, String>();

		// Read the properties from a file instead of hard-coding it here.
		// Or pass the password in from the command-line.
		result.put("javax.persistence.jdbc.password", password);
		factoryBean.setJpaPropertyMap(result);
		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
