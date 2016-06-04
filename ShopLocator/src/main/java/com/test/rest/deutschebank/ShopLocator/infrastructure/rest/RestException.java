package com.test.rest.deutschebank.ShopLocator.infrastructure.rest;

public class RestException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -4139740967469843903L;

	public RestException(final String msg) {
		super(msg);
	}

	public RestException(final Throwable t) {
		super(t);
	}
}