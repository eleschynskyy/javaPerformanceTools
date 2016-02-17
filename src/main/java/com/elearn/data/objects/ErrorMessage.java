package com.elearn.data.objects;

public class ErrorMessage {
	
	private String message;

	public ErrorMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return getMessage();
	}

}
