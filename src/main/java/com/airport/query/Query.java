package com.airport.query;

import com.airport.exception.QueryCompilationFailException;
import com.airport.exception.QueryExecutionFailException;

/**
 * 
 * @author Lokesh Gupta
 *
 */
public interface Query<R> {

	/**
	 * Compile query on specific rules.
	 * 
	 * @return "this"
	 * @throws QueryCompilationFailException
	 */
	public Query<R> compile() throws QueryCompilationFailException;
	
	/**
	 * Execute query and update query state.
	 * 
	 * @return "this"
	 * @throws QueryExecutionFailException
	 */
	public Query<R> execute() throws QueryExecutionFailException;

	/**
	 * @return queryString for which query object created 
	 */
	public String getQueryString();
	
	
	/**
	 * @return final result from query.
	 */
	public R getResult();
}
