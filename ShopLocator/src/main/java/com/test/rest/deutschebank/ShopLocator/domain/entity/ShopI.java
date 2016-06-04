package com.test.rest.deutschebank.ShopLocator.domain.entity;

import java.util.UUID;

/**
 * 
 * @author DBaptiste
 *
 */
public interface  ShopI {
	
	/*
	 * 
	 */
	String getName();

	/**
	 * 
	 * @return the Address of the shop
	 */
	AddressI getAddress();
	
	/**
	 * 
	 * @return the GeoLocation of the shop
	 */

	GeoLocationI getGeoLocation();
	
	
	/**
	 * 
	 * @return a unique identifier for the shop
	 */
	UUID getId();
}
