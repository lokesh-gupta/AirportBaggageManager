package airport.conveyor;

import airport.conveyor.vertex.ConveyorVertex;

/**
 * @author logupta
 *
 */
public interface Conveyor {

	/**
	 * @return ConveyorVertex, start point of the conveyor
	 */
	ConveyorVertex getStart();
	
	/**
	 * @return ConveyorVertex, end point of the conveyor
	 */
	ConveyorVertex getEnd();
	
	/**
	 * @return cost of conveyor
	 */
	ConveyorTravelCost getCost();
	
	/**
	 * Switch conveyor flow direction.
	 * Basically it will switch start and end point of conveyor
	 * this feature will available in bidirection conveyor
	 */
	void switchDirection(ConveyorTravelCost cost);
	
	/**
	 * Switch conveyor flow direction.
	 * Basically it will switch start and end point of conveyor
	 * this feature will available in bidirection conveyor
	 */
	void switchDirection(double cost);
}
