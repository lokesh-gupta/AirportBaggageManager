package airport.flight;

import airport.Place;
import airport.conveyor.vertex.AbstractConveyorVertex;

/**
 * Flight maintian its info 
 * flight id  will be unique in airport and will be final in object.
 * 
 * @author logupta
 *
 */
public class Flight {
	/**
	 * flight id will be unique in airport can can't be change.
	 * object will be define unique by this id only.
	 */
	private final String flightId;
	
	/**
	 * Gate of flight for dep/arrival
	 */
	private String flightGate;
	
	/**
	 *  Destination of flight
	 */
	private Place destination;
	
	/**
	 *  time to departure.
	 */
	private FlightTime time;
	
	/**
	 * @param flightId
	 */
	public Flight(String flightId){
		this.flightId = flightId;
	}
	
	public Place getDestination() {
		return destination;
	}
	
	public String getFlightGate() {
		return flightGate;
	}
	
	public String getFlightId() {
		return flightId;
	}
	
	public FlightTime getTime() {
		return time;
	}
	
	public String getTimeInString() {
		return time.getInFormat();
	}
	
	public void setDestination(Place destination) {
		this.destination = destination;
	}
	
	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}
	
	public void setTime(FlightTime time) {
		this.time = time;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractConveyorVertex)) return false;

        Flight that = (Flight) o;

        if (!flightId.equals(that.flightId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return flightId.hashCode();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                "flightGate='" + flightGate + '\'' +
                "destination='" + destination.getName() + '\'' +
                "time='" + getTimeInString() + '\'' +
                '}';
    }
	
	/**
	 * Maintain time of flight.
	 * 
	 * @author Lokesh Gupta
	 */
	public static class FlightTime{
		private int hour;
		private int min;
		
		public int getHour() {
			return hour;
		}
		
		public int getMin() {
			return min;
		}
		
		public String getInFormat(){
			return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
		}
		
		public void setHour(int hour) {
			this.hour = hour;
		}
		
		public void setMin(int min) {
			this.min = min;
		}
	}
}
