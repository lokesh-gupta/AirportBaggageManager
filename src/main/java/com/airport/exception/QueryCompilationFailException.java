package com.airport.exception;

public class QueryCompilationFailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public QueryCompilationFailException(String msg) {
		super(msg);
	}
	
	public QueryCompilationFailException(String msg, Throwable e) {
		super(msg, e);
	}
}
