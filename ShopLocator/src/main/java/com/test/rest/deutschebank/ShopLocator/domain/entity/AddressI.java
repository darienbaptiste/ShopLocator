package com.test.rest.deutschebank.ShopLocator.domain.entity;

public interface  AddressI {
	
	/**
	 * 
	 * @return the Name of the Shop
	 */
	String getName();
	/**
	 * 
	 * @return the Number of the Shop
	 */
	String getNumber();
	/**
	 * 
	 * @return the PostCode of the Shop
	 */
	String getPostCode();

	AddressI copy();

}
