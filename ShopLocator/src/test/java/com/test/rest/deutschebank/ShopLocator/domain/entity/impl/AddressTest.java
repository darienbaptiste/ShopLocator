package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;

public class AddressTest {

	private static AddressI testAddress;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testAddress = new Address("a","b","c") ;
	}


	@Test
	public void testGetAttributes() {
		org.junit.Assert.assertEquals("a",testAddress.getName());
		org.junit.Assert.assertEquals("b",testAddress.getNumber());
		org.junit.Assert.assertEquals("c",testAddress.getPostCode());
	}
	
	@Test
	public void testCopy() {
		org.junit.Assert.assertEquals(testAddress.copy(),testAddress);
	}

	@Test
	public void testChanged() {
		Address changed = (Address)testAddress.copy();
		changed.setName("D");
		org.junit.Assert.assertNotEquals(changed,testAddress);
	}
	
}
