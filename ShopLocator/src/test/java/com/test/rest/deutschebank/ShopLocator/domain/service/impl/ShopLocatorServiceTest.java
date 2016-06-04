package com.test.rest.deutschebank.ShopLocator.domain.service.impl;

import static org.mockito.Matchers.any;

import java.io.StringReader;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Address;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.GeoLocation;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Shop;
import com.test.rest.deutschebank.ShopLocator.domain.service.ShopLocatorServiceI;
import com.test.rest.deutschebank.ShopLocator.infrastructure.rest.RestClient;

public class ShopLocatorServiceTest {

	private static ShopLocatorService testLocatorService;
	private static final String JSON_STRING = "{   \"results\" : [      {         \"address_components\" : [            {               \"long_name\" : \"2\",               \"short_name\" : \"2\",               \"types\" : [ \"street_number\" ]            },            {               \"long_name\" : \"Hawthorne Close\",               \"short_name\" : \"Hawthorne Cl\",               \"types\" : [ \"route\" ]            },            {               \"long_name\" : \"Mildmay Ward\",               \"short_name\" : \"Mildmay Ward\",               \"types\" : [ \"neighborhood\", \"political\" ]            },            {               \"long_name\" : \"London\",               \"short_name\" : \"London\",               \"types\" : [ \"locality\", \"political\" ]            },            {               \"long_name\" : \"London\",               \"short_name\" : \"London\",               \"types\" : [ \"postal_town\" ]            },            {               \"long_name\" : \"Greater London\",               \"short_name\" : \"Greater London\",               \"types\" : [ \"administrative_area_level_2\", \"political\" ]            },            {               \"long_name\" : \"United Kingdom\",               \"short_name\" : \"GB\",               \"types\" : [ \"country\", \"political\" ]            },            {               \"long_name\" : \"N1 4AW\",               \"short_name\" : \"N1 4AW\",               \"types\" : [ \"postal_code\" ]            }         ],         \"formatted_address\" : \"2 Hawthorne Cl, Mildmay Ward, London N1 4AW, UK\",         \"geometry\" : {            \"location\" : {               \"lat\" : 51.5469103,               \"lng\" : -0.07963630000000001            },            \"location_type\" : \"ROOFTOP\",            \"viewport\" : {               \"northeast\" : {                  \"lat\" : 51.5482592802915,                  \"lng\" : -0.07828731970849798               },               \"southwest\" : {                  \"lat\" : 51.5455613197085,                  \"lng\" : -0.08098528029150205               }            }         },         \"partial_match\" : true,         \"place_id\" : \"ChIJKaKj0I8cdkgRU0Ud42vAH9o\",         \"types\" : [ \"street_address\" ]      }   ],   \"status\" : \"OK\"}";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		JsonObject value = Json.createReader(new StringReader(JSON_STRING)).readObject().getJsonArray("results")
				.getJsonObject(0);
		testLocatorService = org.mockito.Mockito.spy(new ShopLocatorService());
		try {
			Mockito.doReturn(Optional.of(value)).when(testLocatorService).getGoogleData(any(AddressI.class), any(RestClient.class));

		} catch (final Exception e1) {

			e1.printStackTrace();
		}

	}

	@Test
	public void testAddShop() {
		AddressI address = new Address("HawthorneClose", "2", "n14aw");
		GeoLocationI location = new GeoLocation(51.5469103, -0.07963630000000001);
		ShopI expected = new Shop(address, location);
		ShopI actual = testLocatorService.addShop(address);
		org.junit.Assert.assertEquals(expected, actual);
		ShopI[] stored = new ShopI[] { expected };
		org.junit.Assert.assertArrayEquals(stored, testLocatorService.getShopList());
	}

	@Test
	public void testsetShop() {
		ShopLocatorServiceI locatorService = new ShopLocatorService();
		AddressI address = new Address("HawthorneClose", "2", "n14aw");
		GeoLocationI location = new GeoLocation(51.5469103, -0.07963630000000001);
		ShopI shop = new Shop(address, location);
		ShopI[] stored = new ShopI[] { shop };
		locatorService.setShops(stored);
		org.junit.Assert.assertArrayEquals(stored, locatorService.getShopList());
	}
}
