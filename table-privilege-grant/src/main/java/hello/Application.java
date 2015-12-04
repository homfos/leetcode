package hello;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class Application {
	static Logger logger = LoggerFactory.getLogger("log");

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		Controller controller = ctx.getBean(Controller.class);
		controller.execute();
		logger.info("finish");
	}

	@Bean
	@Primary
	public DataSource dataSourceTest() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@10.25.79.176:1521:orcl");
		dataSource.setUsername("system");
		dataSource.setPassword("Baosight123");
		return dataSource;
	}
	
	@Bean
	public DataSource dataSourceRelease() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@10.101.107.30:1521:orcl");
		dataSource.setUsername("iplat");
		dataSource.setPassword("iplat");
		return dataSource;
	}
}
