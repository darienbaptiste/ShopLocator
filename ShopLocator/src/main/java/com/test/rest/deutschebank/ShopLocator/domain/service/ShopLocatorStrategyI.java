package com.test.rest.deutschebank.ShopLocator.domain.service;

import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;

public interface  ShopLocatorStrategyI {
	
	ShopI findClosest(ShopI[] shops,GeoLocationI location);

}
