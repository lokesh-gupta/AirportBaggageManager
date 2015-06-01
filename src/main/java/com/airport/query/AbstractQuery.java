package com.airport.query;

import com.airport.exception.QueryCompilationFailException;
import com.airport.exception.QueryExecutionFailException;
import com.airport.logging.LogginConstants;

/**
 * Compile and execute query with basic rules use query specific abstract method to validation query on 
 * query type specific rules.
 * 
 * @author Lokesh
 *
 */
public abstract class AbstractQuery<R> implements Query<R>{

	/**
	 *  Basic string of query.
	 */
	protected final String query;
	/**
	 * Status of compiled.
	 */
	private boolean isSuccessfullyCompiled = false;
	/**
	 * Status of Executed.
	 */
	private boolean isSuccessfullyExecuted = false;
	/**
	 * Failed message if get any error.
	 */
	protected String failedMsg;
	/**
	 * Query result.
	 */
	protected R result;
	
	private final static String NOT_EXECUTED_RESULT_MSG = "Query not executed";
	
	/**
	 * @param query
	 */
	public AbstractQuery(String query){
		this.query = query;
	}
	
	/**
	 *  Clear state of query.
	 */
	protected void prepForCompile(){
		result = null;
		isSuccessfullyCompiled = false;
		isSuccessfullyExecuted = false;
		failedMsg = null;
	}
	
	/* (non-Javadoc)
	 * @see airport.query.Query#compile()
	 */
	@Override
	public Query<R> compile() throws QueryCompilationFailException{
		if(null == query || query.trim().equals("")){
			genrateFailMsg("Query string is null or empty");
			
			throw new QueryCompilationFailException(failedMsg);
		}
		
		prepForCompile();
		
		try{
			compileQuerySpecific();
		}catch(Exception e){
			genrateFailMsg(e.getMessage());
		
			throw new QueryCompilationFailException(failedMsg, e);
		}
		

		isSuccessfullyCompiled = true;
		failedMsg = null;
		return this;
	}
	
	/**
	 * Give specific rules for compilation.
	 * @throws QueryCompilationFailException
	 */
	protected abstract void compileQuerySpecific() throws QueryCompilationFailException;
	
	/**
	 * Give specific rules for execution.
	 * @throws QueryExecutionFailException
	 */
	protected abstract void executeQuerySpecific() throws QueryExecutionFailException;
	
	/**
	 * Give specific text for result.
	 * @return
	 */
	protected abstract R resultQuerySpecific();
	
	/* (non-Javadoc)
	 * @see airport.query.Query#execute()
	 */
	@Override
	public Query<R> execute() throws QueryExecutionFailException{
		if(!isSuccessfullyCompiled){
			genrateFailMsg("Query string is null or empty");
			
			isSuccessfullyExecuted = false;
			throw new QueryExecutionFailException(failedMsg);
		}
		
		try{
			executeQuerySpecific();
		}catch(Exception e){
			genrateFailMsg(e.getMessage());
		
			isSuccessfullyExecuted = false;
			throw new QueryExecutionFailException(failedMsg, e);
		}
		
		
		isSuccessfullyExecuted = true;

		return this;
	}
	
	/* (non-Javadoc)
	 * @see airport.query.Query#getQueryString()
	 */
	@Override
	public String getQueryString() {
		return query;
	}
	
	/* (non-Javadoc)
	 * @see airport.query.Query#getResult()
	 */
	@Override
	public R getResult() {
		if(!isSuccessfullyExecuted){
			genrateFailMsg(NOT_EXECUTED_RESULT_MSG);
			
			isSuccessfullyExecuted = false;
			throw new QueryExecutionFailException(failedMsg);
		}
		
		
		return resultQuerySpecific();
	}
	
	/**
	 * Temp method for generate fail message, in future can be replace or update for logging and error handlers.
	 * 
	 * @param message
	 */
	protected void genrateFailMsg(String message){
		failedMsg = "Query \"" + getQueryString() + "\" failed in " +
				 (isSuccessfullyCompiled ? "compilation " : "execution ") +
				"because of : " + message;
		
		if(LogginConstants.isSTD_OUT_Enable()){
			System.out.println(failedMsg);
		}
	}
}
