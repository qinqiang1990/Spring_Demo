package spring.mvc;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "spring.mvc")
@PropertySource("classpath:application.properties")
public class MVCApplicationContext {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public static Logger logger = Logger.getLogger(MVCApplicationContext.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				MVCApplicationContext.class);
		QQ qq = (QQ) applicationContext.getBean("QQ");
		qq.submitsync();
		System.out.println(qq.jmshost);
	}
}