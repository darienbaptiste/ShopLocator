package com.test.rest.deutschebank.ShopLocator.domain.service;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;

/**
 * 
 * @author DBaptiste here we define the api for the domain service
 *
 */
public interface ShopLocatorServiceI {

	/**
	 * 
	 * @return a list of Shops
	 */
	ShopI[] getShopList();

	/**
	 * 
	 * @param shops
	 *            the array of shops that the service will return
	 */
	void setShops(ShopI[] shops);

	/**
	 * 
	 * @param address
	 * @return the shop that was added at the given address
	 */
	ShopI addShop(AddressI address);

	ShopI selectClosestShop(GeoLocationI location);
	
	/**
	 * Allow new strategy for searching for the nearest Neighbour 
	 * to be used
	 * @param strategy
	 */
	void setStrategy(ShopLocatorStrategyI  strategy);

}
