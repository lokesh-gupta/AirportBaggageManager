package airport.conveyor;

/**
 * Maintain travel cost of conveyor
 * 
 * @author Lokesh Gupta
 *
 */
public class ConveyorTravelCost {

	/**
	 *  Cost of conveyor, maintain time in millisec
	 */
	private final Double cost;
	
	/**
	 * @param cost
	 * @param in
	 */
	public ConveyorTravelCost(Double cost, TimeUnit in){
		this.cost = cost;
	}
	
	/**
	 * @param cost
	 */
	public ConveyorTravelCost(Double cost){
		this.cost = TimeUnit.convert(cost, TimeUnit.Second, TimeUnit.MilliSecond);
	}
	
	/**
	 * @return travel cost in second
	 */
	public Double getCost() {
		return TimeUnit.convert(cost, TimeUnit.MilliSecond, TimeUnit.Second);
	}
	
	/**
	 * Give feature of convert time from any unit to any
	 *
	 * @author Lokesh Gupta
	 *
	 */
	public enum TimeUnit{
		MicroSecond(10^-3),
		MilliSecond(1),
		Second(1000),
		Minute(60*1000),
		Hour(60*60*1000);
		
		/**
		 *  Convert rate from current unit to millisec.
		 */
		private double conversionRate;
		
		/**
		 * @param conversionRate
		 */
		private TimeUnit(double conversionRate){
			this.conversionRate = conversionRate;
		}
		
		/**
		 * Convert time value from one unit to another unit.
		 * @param time
		 * @param from
		 * @param to
		 * @return
		 */
		public static double convert(Double time, TimeUnit from, TimeUnit to){
			if(null == time){
				throw new IllegalArgumentException("Time can't be null");
			}
			
			double inMili = time * from.conversionRate;
			
			return inMili / to.conversionRate;
		}
	}
}
