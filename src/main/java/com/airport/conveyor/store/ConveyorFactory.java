package com.airport.conveyor.store;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.ConveyorTravelCost;
import com.airport.conveyor.SingleDirectionConveyor;
import com.airport.conveyor.vertex.ConveyorVertex;
import com.airport.exception.MethodNotImplementedException;

/**
 * Conveyor factory to create conveyor by its type.
 * 
 * In case of new type have to update conveyorType.
 * 
 * @author logupta
 *
 */
public class ConveyorFactory {

	/**
	 * Define all type of conveyor in airport with its feature and creation method.
	 * 
	 * @author logupta
	 *
	 */
	public enum ConveyorType {

		SingleDirectionConstantSpeed{
			/* (non-Javadoc)
			 * @see airport.conveyor.store.ConveyorFactory.ConveyorType#createConveyor(airport.conveyor.ConveyorTravelCost, airport.conveyor.vertex.ConveyorVertex, airport.conveyor.vertex.ConveyorVertex)
			 */
			@Override
			Conveyor createConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to) {
				return new SingleDirectionConveyor(cost, from, to);
			}
		},
		SingleDirection,
		BiDirectionConstantSpeed,
		BiDirection;
		
		
		/**
		 * Create conveyor object according to its type.
		 * 
		 * @param cost
		 * @param from
		 * @param to
		 * @return
		 */
		Conveyor createConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to){
			throw new MethodNotImplementedException();
		}
	}
	
	
	/**
	 * 
	 * Create conveyor object according to its type.
	 * 
	 * @param cost
	 * @param from
	 * @param to
	 * @param conveyorType
	 * @return
	 */
	public static Conveyor createConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to, ConveyorType conveyorType){
		return conveyorType.createConveyor(cost, from, to);
	}
}
