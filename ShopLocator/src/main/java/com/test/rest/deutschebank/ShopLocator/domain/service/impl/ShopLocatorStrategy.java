package com.test.rest.deutschebank.ShopLocator.domain.service.impl;

import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;
import com.test.rest.deutschebank.ShopLocator.domain.service.ShopLocatorStrategyI;

public class ShopLocatorStrategy implements ShopLocatorStrategyI {

	private static final long RADIUS = 6371;

	/*
	 * naive O(n) solution (non-Javadoc)
	 * 
	 * @see com.test.rest.deutschebank.ShopLocator.domain.service.
	 * ShopLocatorStrategyI#findClosest(com.test.rest.deutschebank.ShopLocator.
	 * domain.entity.ShopI[],
	 * com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI)
	 */
	@Override
	public ShopI findClosest(ShopI[] shops, GeoLocationI location) {
		ShopI closest = null;
		if (shops != null && shops.length > 0 && location != null) {
			closest = shops[0];
			double min = Double.MAX_VALUE;
			for (ShopI shop : shops) {
				double distance = haversineManhattanDistance(shop.getGeoLocation(), location);
				if (distance < min) {
					closest = shop;
					min = distance;
				}
			}
		}
		return closest;
	}

	protected double haversineManhattanDistance(GeoLocationI location1, GeoLocationI location2) {
		double latDiff = Math.toRadians(Math.abs(location1.getLatitude() - location2.getLatitude()));
		double lonDiff = Math.toRadians(Math.abs(location1.getLongitude() - location2.getLongitude()));
		double latDistance = Math.abs(getDistance(latDiff));
		double lonDistance = Math.abs(getDistance(lonDiff));
		return latDistance + lonDistance;
	}

	protected double getDistance(double angularDifference) {
		double a = Math.sin(angularDifference / 2) * Math.sin(angularDifference / 2);
		double c = 2 * Math.atan2(Math.pow(a, 0.5), Math.pow((1 - a), 0.5));
		double latDistance = RADIUS * c;
		return latDistance;
	}

}
