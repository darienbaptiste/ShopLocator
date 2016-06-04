package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;

public class GeoLocation implements GeoLocationI {

	public final static String LAT = "lat";
	public final static String LNG = "lng";

	private double longitude;
	private double latitude;
	
	public GeoLocation(){
		super();
	}

	public GeoLocation(double latitude, double longitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	private GeoLocation(GeoLocation location) {
		super();
		this.longitude = location.getLongitude();
		this.latitude = location.getLatitude();
	}

	public GeoLocationI copy() {
		return new GeoLocation(this);
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {

		return this.longitude;
	}

	public double getLatitude() {

		return this.latitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoLocation other = (GeoLocation) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GeoLocation [longitude=" + longitude + ", latitude=" + latitude + "]";
	}

}
