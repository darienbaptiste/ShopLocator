package com.test.rest.deutschebank.ShopLocator.domain.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Address;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.GeoLocation;
import com.test.rest.deutschebank.ShopLocator.domain.entity.impl.Shop;

public class ShopLocatorStrategyTest {
	
	 private static ShopLocatorStrategy strategy ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		strategy = new ShopLocatorStrategy();
	}



	@Test
	public void testDistance() {
		GeoLocationI loc1 = new GeoLocation(37.4602,126.441);
		GeoLocationI loc2 = new GeoLocation(37.5567,126.924);
		double expected = 64.437459990522;
		double actual = strategy.haversineManhattanDistance(loc1, loc2);
		org.junit.Assert.assertEquals(expected, actual,0.0001);
	}

	
	
	@Test
	public void testClosestEmpty() {
		ShopI actual = strategy.findClosest(new ShopI[]{}, new  GeoLocation(1,2));
		org.junit.Assert.assertNull(actual);
	}

	@Test
	public void testClosestNull() {
		ShopI actual = strategy.findClosest(null, new  GeoLocation(1,2));
		org.junit.Assert.assertNull(actual);
	}
	
	@Test
	public void testClosestNulllocation() {
		ShopI actual = strategy.findClosest(new ShopI[]{}, null);
		org.junit.Assert.assertNull(actual);
	}	
	
	@Test
	public void testClosestData() {

	AddressI address = new Address("HawthorneClose", "2", "n14aw");
	GeoLocationI location = new GeoLocation(51.5469103, -0.07963630000000001);
	ShopI shop = new Shop(address, location);

	AddressI address1 = new Address("Blah Blah", "2", "n1t");
	GeoLocationI loc1 = new GeoLocation(37.4602,126.441);
	ShopI shop1 = new Shop(address1, loc1);
	AddressI address2 = new Address("Blah Blah", "2", "nq");
	GeoLocationI loc2 = new GeoLocation(37.5567,126.924);
	ShopI shop2 = new Shop(address2, loc2);
	ShopI[] stored = new ShopI[] { shop,shop1,shop2 };
	ShopI actual = strategy.findClosest(stored, new GeoLocation (51.53, -0.079601));
	org.junit.Assert.assertEquals(shop, actual);

	}
}
