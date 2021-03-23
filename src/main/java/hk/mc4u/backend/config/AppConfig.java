package hk.mc4u.backend.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("hk.mc4u.backend.repository")
@EnableTransactionManagement
@ComponentScan(basePackages = "hk.mc4u.backend")
@EnableAspectJAutoProxy
public class AppConfig {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");
		//factoryBean.setDataSource(dataSource());

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

	
	public DataSource dataSource() {
		String password = System.getenv("PASSWORD");

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/demo?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword(password);

		// Connection pooling properties
		dataSource.setInitialSize(0);
		dataSource.setMaxIdle(5);
		dataSource.setMaxTotal(5);
		dataSource.setMinIdle(0);

		return dataSource;
	}
}
