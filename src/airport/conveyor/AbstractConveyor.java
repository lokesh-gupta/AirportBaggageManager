package airport.conveyor;

import airport.conveyor.vertex.ConveyorVertex;


/**
 * 
 * Give basic feature implementation of conveyor.
 * Also maintain data for conveyor like cost, from and to vertex.
 * 
 * @author Lokesh Gupta
 *
 */
public abstract class AbstractConveyor implements Conveyor{
	
	/**
	 *  Cost of traveling on conveyor from start point to end point.
	 */
	protected ConveyorTravelCost cost;
	
	/**
	 *  Conveyor start vertex.
	 */
	protected ConveyorVertex verticesFrom;
	
	
	/**
	 * Conveyor end vertex.
	 */
	protected ConveyorVertex verticesTo;
	
	/**
	 * @param cost
	 * @param from
	 * @param to
	 */
	public AbstractConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to){
		this.cost = cost;
		this.verticesFrom = from;
		this.verticesTo = to;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.Conveyor#getCost()
	 */
	@Override
	public ConveyorTravelCost getCost() {
		return cost;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.Conveyor#getEnd()
	 */
	@Override
	public ConveyorVertex getEnd() {
		return verticesTo;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.Conveyor#getStart()
	 */
	@Override
	public ConveyorVertex getStart() {
		return verticesFrom;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.Conveyor#switchDirection(double)
	 */
	@Override
	public void switchDirection(double cost){
		switchConveyorDirection(new ConveyorTravelCost(cost));
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.Conveyor#switchDirection(airport.conveyor.ConveyorTravelCost)
	 */
	@Override
	public void switchDirection(ConveyorTravelCost cost){
		switchConveyorDirection(cost);
	}
	
	/**
	 * will do main switching of conveyor flow.
	 * If conveyor not support this feature will throw exception.
	 * 
	 * @param cost
	 */
	protected void switchConveyorDirection(ConveyorTravelCost cost){
		ConveyorVertex tmp = verticesFrom;
		verticesFrom = verticesTo;
		verticesTo = tmp;
		this.cost = cost;
	}
}
