package com.airport.query;

import com.airport.exception.QueryCompilationFailException;
import com.airport.exception.QueryExecutionFailException;
import com.airport.flight.Flight;
import com.airport.flight.FlightStore;
import com.airport.flight.Flight.FlightTime;
import com.airport.logging.LogginConstants;
import com.airport.place.Place;

/**
 * Add flight in airport.
 * 
 *  Format: <flight_id> <flight_gate> <destination> <flight_time>
 * 
 * @author Lokesh Gupta
 *
 */
public class FlightQuery extends AbstractQuery<Flight>{

	private static String SPLLITER = " ";
	
	/**
	 * Airport flight store. 
	 */
	private FlightStore flightStore;
	
	/**
	 * @param query
	 * @param flightStore
	 */
	public FlightQuery(String query) {
		super(query);
	}
	
	public void setFlightStore(FlightStore flightStore) {
		this.flightStore = flightStore;
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#compileQuerySpecific()
	 */
	@Override
	protected void compileQuerySpecific() throws QueryCompilationFailException {
		String[] words = query.split(SPLLITER);
		
		if(words.length != 4){
			throw new QueryCompilationFailException("Query is not in right format.");
		}
		
		String[] time = words[3].split(":");
		if(time.length != 2){
			throw new QueryCompilationFailException("Flight time is not in right format.");
		}
		
		try{
			Integer.valueOf(time[0]);
			Integer.valueOf(time[1]);
		}catch(NumberFormatException e){
			throw new QueryCompilationFailException("Flight time is not in right format.");
		}
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#executeQuerySpecific()
	 */
	@Override
	protected void executeQuerySpecific() throws QueryExecutionFailException {
		String[] words = query.split(SPLLITER);
		
		if(null != flightStore.getFlight(words[0])){
			genrateFailMsg("Flight id already exists.");
			
			throw new QueryExecutionFailException(failedMsg);
		}
		
		String[] time = words[3].split(":");
		
		FlightTime flightTime = new FlightTime();
		flightTime.setHour(Integer.valueOf(time[0]));
		flightTime.setMin(Integer.valueOf(time[1]));
		
		result = flightStore.addFlight(words[0], words[1], new Place(words[2]), flightTime);
		
		if(LogginConstants.isSTD_OUT_Enable()){
			System.out.println("Add flight to store : " + result.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#resultQuerySpecific()
	 */
	@Override
	protected Flight resultQuerySpecific() {
		return result;
	}
}
