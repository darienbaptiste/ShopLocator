package com.test.rest.deutschebank.ShopLocator.infrastructure.rest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class McConfig {
	private static final String REST_CLIENT_GOOGLE_CONFIG_BEAN = "restClientGoogle";

	private static final ConfigurableApplicationContext CONTEXT = new ClassPathXmlApplicationContext(
			new String[] { McConfig.REST_CLIENT_SPRING_XML });

	public static final String REST_CLIENT_SPRING_XML = "RestClient_Spring.xml";

	public static <T> T findBean(final String name) {
		return (T) CONTEXT.getBean(name);
	}

	public static RestClient getGoogleClient() {
		return (RestClient) CONTEXT.getBean(REST_CLIENT_GOOGLE_CONFIG_BEAN);
	}
}
