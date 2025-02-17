package com.jackbravo21.picpay_desafio.exceptions;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final ErrorResponse errorResponse;

    public CustomException(int status, String message) {
        super(message);
        this.errorResponse = new ErrorResponse(status, message);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

	public int getStatus() {
	    return errorResponse.getStatus();
	}
}