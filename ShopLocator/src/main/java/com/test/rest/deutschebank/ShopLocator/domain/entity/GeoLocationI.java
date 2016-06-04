package com.test.rest.deutschebank.ShopLocator.domain.entity;

public interface GeoLocationI {

	/**
	 * 
	 * @return a double representing the longitude of the shop
	 */
	double getLongitude();
	/**
	 * 
	 * @return a double representing the latitude of the shop
	 */
	double getLatitude();
	
	GeoLocationI copy();
}
