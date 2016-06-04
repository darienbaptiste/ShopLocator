package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import java.util.UUID;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.GeoLocationI;
import com.test.rest.deutschebank.ShopLocator.domain.entity.ShopI;

/**
 * 
 * @author DBaptiste
 *
 */

public class Shop implements ShopI {

	private final AddressI address;
	private final GeoLocationI location;

	private final UUID id;

	public Shop(final AddressI address, final GeoLocationI location) {
		super();
		this.address = address.copy();
		this.location = location.copy();
		this.id = java.util.UUID.randomUUID();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		Shop other = (Shop) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	public UUID getId() {
		return this.id;
	}
	@Override
	public String getName(){
		return this.getAddress().getName();
	}

	@Override
	public AddressI getAddress() {
		return this.address.copy();
	}

	@Override
	public GeoLocationI getGeoLocation() {
		return this.location.copy();
	}

	@Override
	public String toString() {
		return "Shop [address=" + address + ", location=" + location + ", id=" + id + "]";
	}

}
