package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;

public class ShopTest {
	
	private static ShopI testShop ;
	private static GeoLocationI testLocation;
	private static AddressI testAddress;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 testLocation = new GeoLocation(1.0,2.0);
		testAddress = new Address("a","b","c");
		testShop = new Shop(testAddress,testLocation);
	}

	

	@Test
	public void testGetAttributes() {
		org.junit.Assert.assertEquals(testLocation,testShop.getGeoLocation());
		org.junit.Assert.assertEquals(testAddress,testShop.getAddress());
		org.junit.Assert.assertEquals("a",testShop.getName());
	}
	
	@Test
	public void testChanged() {
		GeoLocation changedG = (GeoLocation)testShop.getGeoLocation();
		changedG.setLatitude(5.0);
		org.junit.Assert.assertNotEquals(changedG,testShop.getGeoLocation());
		Address changedA = (Address)testShop.getAddress();
		changedA.setName("D");
		org.junit.Assert.assertNotEquals(changedA,testShop.getAddress());
	}

}
