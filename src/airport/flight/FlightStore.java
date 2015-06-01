package airport.flight;

import airport.Place;
import airport.flight.Flight.FlightTime;

/**
 * Maintain all Flight in airport.
 * 
 * @author logupta
 *
 */
public interface FlightStore {

	/**
	 * Add new flight in store.
	 * 
	 * @param flightId
	 * @param flightGate
	 * @param destination
	 * @param time
	 * @return
	 */
	public Flight addFlight(String flightId, String flightGate, Place destination, FlightTime time);
	
	/**
	 * Get flight from store by flight id
	 * 
	 * @param flightId
	 * @return : Flight
	 */
	public Flight getFlight(String flightId);
}
