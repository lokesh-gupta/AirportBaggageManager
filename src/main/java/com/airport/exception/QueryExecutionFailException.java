package com.airport.exception;

public class QueryExecutionFailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QueryExecutionFailException(String msg) {
		super(msg);
	}
	
	public QueryExecutionFailException(String msg, Throwable e) {
		super(msg, e);
	}

}
