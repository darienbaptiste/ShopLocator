package com.test.rest.deutschebank.ShopLocator.application.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Address;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.GeoLocation;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Shop;
import com.test.rest.deutschebank.ShopLocator.domain.service.ShopLocatorServiceI;
import com.test.rest.deutschebank.ShopLocator.domain.service.impl.ShopLocatorService;
import com.test.rest.deutschebank.ShopLocator.infrastructure.RestResult;


@RestController
public class ShopLocatorController {

	private static final String TEMPLATE = "The application  is , %s!";
	private static Log logger = LogFactory.getLog(ShopLocatorController.class);
	private final AtomicLong counter = new AtomicLong();
	private final ShopLocatorServiceI locatorService;
	// curl -H "Content-Type: application/json" -X POST -d '{"name":"HawthorneClose","number":"2","postCode":"n14aw"}' http://localhost:8080/rest/ShopLocatorService/addShop

	
	public ShopLocatorController(){
		this.locatorService = new ShopLocatorService();
	}
	
	public ShopLocatorServiceI getLocatorService(){
		return this.locatorService;
	}

	/**
	 * @param id
	 *            A very simple request that can be used to verify that the
	 *            service is running, useful for smoke tests etc.
	 * @return the result wrapped in the RestResult entity
	 */


	@RequestMapping("/rest/ShopLocatorService/test")
	public ResponseEntity<RestResult> test(
			@RequestParam(value = "test", defaultValue = "responding") final String test) {
		logger.info("testing the rest service");
		final RestResult result = new RestResult(counter.incrementAndGet(),
				new String[] { String.format(TEMPLATE, test) });
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * add jsonp return type so I can use the browser to test and avoid
	 * CrossSiteScripting [XXS] problems
	 */
	@ControllerAdvice
	static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

		public JsonpAdvice() {
			super("callback");
		}
	}
	
	//@Secured("ROLE_USER")
	@RequestMapping(value ="rest/ShopLocatorService/getShopList", method = RequestMethod.GET)
	public ResponseEntity<RestResult> getShopList() {
		final RestResult result = new RestResult(counter.incrementAndGet(),locatorService.getShopList());
		return new ResponseEntity<>(result, HttpStatus.FOUND);
	}
	
	

	@RequestMapping(value ="rest/ShopLocatorService/addShop",method = RequestMethod.POST)
	public ResponseEntity<RestResult> addShop(@RequestBody Address input) {
		logger.info(String.format("adding a shop at %s",input));
		ShopI  shop = locatorService.addShop(input);
		final RestResult result = new RestResult(counter.incrementAndGet(),
				new ShopI[]{shop});
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}	
	
	
	@RequestMapping(value ="rest/ShopLocatorService/selectClosestShop",method = RequestMethod.GET)
	public ResponseEntity<RestResult> selectClosestShop(@RequestBody GeoLocation input) {
		logger.info(String.format("selecting the shop closest to  %s",input));
		ShopI  shop = locatorService.selectClosestShop(input);
		final RestResult result = new RestResult(counter.incrementAndGet(),
				new ShopI[]{shop});
		return new ResponseEntity<>(result, HttpStatus.FOUND);
	}		
	
}
