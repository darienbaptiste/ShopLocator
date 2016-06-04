package com.test.rest.deutschebank.ShopLocator.infrastructure;

import java.util.Arrays;

public class RestResult {
	private final long id;
	// hold the data
	private final Object[] content;
	public static final String NOT_FOUND = "NOT FOUND";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";

	public RestResult(final long id, final Object[] content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public Object[] getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = (59 * hash) + (int) (id ^ (id >>> 32));
		hash = (59 * hash) + Arrays.hashCode(content);
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RestResult other = (RestResult) obj;
		if (id != other.id) {
			return false;
		}
		return !((content == null) ? (other.content != null) : !Arrays.equals(content, other.content));
	}

	@Override
	public String toString() {
		return "RestResult{" + "id=" + id + ", content=" + Arrays.deepToString(content) + '}';
	}

}
