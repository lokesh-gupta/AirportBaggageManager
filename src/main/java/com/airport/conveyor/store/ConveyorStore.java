package com.airport.conveyor.store;

import java.util.List;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.ConveyorTravelCost;
import com.airport.conveyor.store.ConveyorFactory.ConveyorType;
import com.airport.conveyor.vertex.ConveyorVertex;

/**
 * Maintain all aiport conveyors.
 * 
 * Support feature like add and get particular conveyor.
 * 
 * @author Lokesh Gupta
 *
 */
public abstract class ConveyorStore {

	/**
	 * Get conveyor if not exists add in store.
	 * 
	 * @param cost
	 * @param from
	 * @param to
	 * @param conveyorType
	 * @return object of convery.
	 */
	public abstract Conveyor getConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to, ConveyorType conveyorType);

	/**
	 * Get all conveyor in airport.
	 * 
	 * @return
	 */
	public abstract List<Conveyor> getAllConveyor();
	
	/**
	 * Default implementation to create new conveyor object from factory.
	 * 
	 * @param cost
	 * @param from
	 * @param to
	 * @param conveyorType
	 * @return
	 */
	protected Conveyor getConveyorObject(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to, ConveyorType conveyorType){
		return ConveyorFactory.createConveyor(cost, from, to, conveyorType);
	}
}
