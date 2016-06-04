package com.test.rest.deutschebank.ShopLocator.domain.entity.impl;

import com.test.rest.deutschebank.ShopLocator.domain.entity.AddressI;

public class Address  implements AddressI{
	private String name;
	private String number;
	private String postCode;
	
	public Address(){
		
	}

	public Address(String name, String number, String postCode) {
		super();
		this.name = name;
		this.number = number;
		this.postCode = postCode;
	}

	private Address(AddressI address){
		super();
		this.name = address.getName();
		this.number = address.getNumber();
		this.postCode = address.getPostCode();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getName() {
		return  this.name;
	}

	public String getNumber() {
		return this.number;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public AddressI copy(){
		return new Address(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
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
		Address other = (Address) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", number=" + number + ", postCode=" + postCode + "]";
	}
	
	
}
