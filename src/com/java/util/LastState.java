package com.java.util;

public class LastState {

	private String lastUri;

	public String getLastUri() {
		return lastUri;
	}

	public void setLastUri(String lastUri) {
		this.lastUri = lastUri;
	}

	@Override
	public String toString() {
		return "LastState [" + lastUri + "]";
	}

}
