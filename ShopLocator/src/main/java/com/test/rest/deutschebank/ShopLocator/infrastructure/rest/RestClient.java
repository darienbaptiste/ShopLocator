package com.test.rest.deutschebank.ShopLocator.infrastructure.rest;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
public class RestClient {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    private final MappingJackson2HttpMessageConverter jsn = new MappingJackson2HttpMessageConverter();
    private RestTemplate restTemplate;
    private String url;

    private boolean converterAdded;

    public static final String KEYWORD_SEARCH = "search";

    @SuppressWarnings("unchecked")
    public static Map<String, Object> executeRestCall(final String restClientName, final Map<String, String> params) {
	Map<String, Object> results = null;

	try {
	    results = (Map<String, Object>) RestClient.getClientInstance(restClientName).call(params);
	} catch (Exception e) {
	    LOGGER.info(e.getMessage(), e);
	}

	return results;
    }

    public static RestClient getClientInstance(final String instanceName) {
	return McConfig.findBean(instanceName);
    }

    public static void main(final String[] args) {

	try (ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
		new String[] { "RestClient_Spring.xml" })) {
	    RestClient restClient = (RestClient) context.getBean("restClientGetSpecials");

	    Object result = restClient.call();
	    LOGGER.info(result.toString());
	} catch (Exception e) {
	    LOGGER.error(e.getMessage(), e);
	}
    }

    public Object call() {
	return call(null);
    }

    public Object call(final Map<String, ?> args) {
	try {
	    addMessageConverter();
	    return getObject(args);
	} catch (RestClientException e1) {
	    throw new RestException(e1);
	}
    }

    public JsonObject fetch() {
	try {
	    LOGGER.info("Invoking url template: " + url);
	    ResponseEntity<String> results = restTemplate.getForEntity(url, String.class);
	    String result = HttpStatus.OK.equals(results.getStatusCode()) ? results.getBody() : null;

	    Optional<JsonObject> json = Optional.ofNullable(result).map(StringReader::new).map(Json::createReader)
		    .map(JsonReader::readObject);

	    json.<NoSuchElementException> orElseThrow(() -> {
		throw new NoSuchElementException(url);
	    });
	    return json.get();
	} catch (RestClientException e) {
	    throw new RestException(e);
	}
    }

    public JsonObject fetch(final Map<String, ?> args) {
	return fetchBatch(args).get(0);
    }

    public List<JsonObject> fetchBatch(final Map<String, ?> args) {
	try {
	    LOGGER.info(String.format("Invoking url template %s with %s", url, args));
	    ResponseEntity<String> results = restTemplate.getForEntity(url, String.class, args);
	    String result = HttpStatus.OK.equals(results.getStatusCode()) ? results.getBody() : null;
	    LOGGER.debug(result);

	    Optional<JsonArray> json = Optional.ofNullable(result).map(StringReader::new).map(Json::createReader)
		    .map(JsonReader::readObject).map(i -> i.getJsonArray("results"));

	    json.<NoSuchElementException> orElseThrow(() -> {
		throw new NoSuchElementException(args.get(RestClient.KEYWORD_SEARCH).toString());
	    });
	    return json.get().getValuesAs(JsonObject.class);
	} catch (RestClientException e) {
	    throw new RestException(e);
	}
    }

    public RestTemplate getRestTemplate() {
	return restTemplate;
    }

    public String getUrl() {
	return url;
    }

    public void setRestTemplate(final RestTemplate restTemplate) {
	this.restTemplate = restTemplate;
	this.restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }

    public void setUrl(final String url) {
	this.url = url;
    }

    private void addMessageConverter() {
	if (! converterAdded) {
	    restTemplate.getMessageConverters().add(jsn);
	    converterAdded = true;
	}
    }

    private Object getObject(final Map<String, ?> args) {
	LOGGER.info(String.format("Invoking url template %s with %s", url, args));
	return (args == null) ? restTemplate.getForObject(url, Object.class)
		: restTemplate.getForObject(url, Object.class, args);
    }
}