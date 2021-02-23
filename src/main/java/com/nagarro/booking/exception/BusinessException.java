package com.nagarro.booking.exception;

public class BusinessException extends Exception {
	protected static final String TYPE = "BusinessException";

	private static final long serialVersionUID = Long.MAX_VALUE;

	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
	
}
