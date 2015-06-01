package com.airport.flight;

import java.util.HashMap;
import java.util.Map;

import com.airport.flight.Flight.FlightTime;
import com.airport.place.Place;

/**
 * Maintain all flight in airport.
 * 
 * @author Lokesh Gupta
 *
 */
public class DefaultFlightStore implements FlightStore{

	/**
	 *  Collection to store all flight with flight id.
	 */
	private Map<String, Flight> map = new HashMap<String, Flight>();
	
	/* (non-Javadoc)
	 * @see airport.flight.FlightStore#addFlight(java.lang.String, java.lang.String, airport.Place, airport.flight.Flight.FlightTime)
	 */
	@Override
	public Flight addFlight(String flightId, String flightGate, Place destination, FlightTime time) {
		Flight flight = map.get(flightId);
		
		if(null == flight){
			flight = new Flight(flightId);
			flight.setDestination(destination);
			flight.setFlightGate(flightGate);
			flight.setTime(time);
			map.put(flightId, flight);
		}
		
		return flight;
	}
	
	/* (non-Javadoc)
	 * @see airport.flight.FlightStore#getFlight(java.lang.String)
	 */
	@Override
	public Flight getFlight(String flightId) {
		return map.get(flightId);
	}
}
