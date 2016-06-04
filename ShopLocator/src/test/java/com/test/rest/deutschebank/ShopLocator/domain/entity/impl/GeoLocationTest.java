package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;

public class GeoLocationTest {

	private static GeoLocationI testLocation;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testLocation = new GeoLocation(1.0,2.0);
	}

	

	@Test
	public void testGetAttributes() {
		org.junit.Assert.assertEquals(1.0, testLocation.getLatitude(),0.001);
		org.junit.Assert.assertEquals(2.0, testLocation.getLongitude(),0.001);
	}
	
	

	@Test
	public void testCopy() {
		org.junit.Assert.assertEquals(testLocation.copy(), testLocation);
	}

	

	@Test
	public void testChanged() {
		GeoLocation changed = (GeoLocation)testLocation.copy();
		changed.setLatitude(5.0);
		org.junit.Assert.assertNotEquals(changed, testLocation);
	}

	

}
