package com.test.rest.deutschebank.ShopLocator.domain.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.json.JsonObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.GeoLocation;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Shop;
import com.test.rest.deutschebank.ShopLocator.domain.service.ShopLocatorServiceI;
import com.test.rest.deutschebank.ShopLocator.domain.service.ShopLocatorStrategyI;
import com.test.rest.deutschebank.ShopLocator.infrastructure.rest.McConfig;
import com.test.rest.deutschebank.ShopLocator.infrastructure.rest.RestClient;
import com.test.rest.deutschebank.ShopLocator.infrastructure.rest.RestException;

public class ShopLocatorService implements ShopLocatorServiceI {

	private final List<ShopI> shops;

	private static Log logger = LogFactory.getLog(ShopLocatorService.class);
	private final RestClient googleRestClient;
	private ShopLocatorStrategyI  strategy;

	public ShopLocatorService() {
		super();
		this.shops = new ArrayList<>();
		this.googleRestClient = McConfig.getGoogleClient();
		this.strategy = new ShopLocatorStrategy();
	}

	
	/**
	 * for large data sets allow the use to use another strategy
	 * perhaps one based on Kd trees etc.
	 * @param strategy
	 */
	@Override
	public void setStrategy(ShopLocatorStrategyI  strategy){
		this.strategy = strategy;
	}
	
	
	public ShopI[] getShopList() {
		return shops.toArray(new ShopI[] {});
	}

	public void setShops(ShopI[] shops) {
		this.shops.addAll(Arrays.asList(shops));
	}

	/**
	 * Create a shop object at the address and add it to the in memory array of
	 * Shops that the service tracks
	 * 
	 * @param address
	 *            of the shop to add
	 * @return the shop that has been added
	 */
	@Override
	public ShopI addShop(AddressI address) {
		ShopI result = null;
		final GeoLocationI location = getGeoLocation(address, googleRestClient);
		result = new Shop(address, location);
		if(!shops.contains(result)){
			shops.add(result);
		}
		return result;
	}

	/**
	 * Use an address and a rest Client to query Google maps api for the
	 * GeoLocation
	 * 
	 * @param address
	 * @param googleRestClient
	 * @return the GeoLocation of the given address
	 */
	private GeoLocationI getGeoLocation(final AddressI address, final RestClient googleRestClient) {

		Optional<GeoLocationI> location = Optional.of(new GeoLocation(0, 0));
		final JsonObject googleData = getGoogleData(address, googleRestClient).orElse(null);
		if (googleData != null) {
			location = retrieveGeoLocation(googleData);
		}
		return location.get();
	}

	/**
	 * Use an address and a rest Client to query Google maps api for the
	 * JsonObject that contains the GeoLocation data
	 * 
	 * @param address
	 * @param googleRestClient
	 * @return a JsonObject that holds the data
	 */
	protected Optional<JsonObject> getGoogleData(final AddressI address, final RestClient googleRestClient) {
		JsonObject googleData = null;
		final Map<String, String> params = new HashMap<>();
		String number = address.getNumber();
		String name = address.getName().trim().replace(' ', '+');
		String postCode = address.getPostCode();
		params.put("address", String.format("%s+%s+%s", number, name, postCode));
		try {
			googleData = googleRestClient.fetch(params);
		} catch (Exception t) {
			logger.warn(t.getMessage(), t);
			String url = (googleRestClient != null) ? googleRestClient.getUrl() : "Empty URL?";
			logger.info("Rest call failed for " + url, t);
			throw new RestException(t);
		}
		return Optional.ofNullable(googleData);
	}

	/**
	 * Extract the GeoLocation data from a jsonObject
	 * 
	 * @param googleData
	 * @return a GeoLocation extracted from the json payload
	 */
	private Optional<GeoLocationI> retrieveGeoLocation(final JsonObject googleData) {
		GeoLocationI result = null;
		logger.info(String.format("about to parse %s", googleData.toString()));
		try {
			final JsonObject jsonLocation = googleData.getJsonObject("geometry").getJsonObject("location");
			logger.info(String.format("Able to retrieve %s", jsonLocation.toString()));
			double lng = Double.parseDouble(jsonLocation.getJsonNumber(GeoLocation.LNG).toString());
			double lat = Double.parseDouble(jsonLocation.getJsonNumber(GeoLocation.LAT).toString());
			result = new GeoLocation(lat, lng);
		} catch (Exception t) {
			logger.warn(t.getMessage(), t);
			//if we can go ahead with a bad geo location then we should remove the throw exception
			throw new RestException(t);

		}
		return Optional.ofNullable(result);
	}

	public ShopI selectClosestShop(GeoLocationI location) {
		return strategy.findClosest(shops.toArray(new ShopI[]{}), location);
	}
	

}
