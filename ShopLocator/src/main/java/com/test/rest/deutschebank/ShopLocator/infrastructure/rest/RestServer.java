package com.test.rest.deutschebank.ShopLocator.infrastructure.rest;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.test.rest.deutschebank.ShopLocator.application.rest.ShopLocatorController;

/*
 * This is running using the spring frameworks embedded tomcat server on port 8080 to change the port
 * pass -Dserver.port=9090 on the command line when starting
 */

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		JmxAutoConfiguration.class })
@ComponentScan(basePackageClasses = { ShopLocatorController.class })
//@ImportResource("classpath:security.xml")
public class RestServer {

	private static final Log LOGGER = LogFactory.getLog(RestServer.class);
	@Autowired
	Environment environment;

	/**
	 * @param args
	 */
	public static void main(final String... args) {
		RestServer server = new RestServer();
		server.init(args);
	}

	private void init(final String[] args) {
		LOGGER.info("Initialising the Deutsche coding test Rest Service");
		ApplicationContext ctx = SpringApplication.run(RestServer.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			LOGGER.debug(beanName);
		}

	}

}