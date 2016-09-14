package org.nextprot.commons.exception;

public class NextProtCommonException extends RuntimeException{

	private static final long serialVersionUID = 6739181672834760607L;

	public NextProtCommonException(String message) {
		super(message);
	}
	
	public NextProtCommonException(Throwable t) {
		super(t);
	}
	
}
