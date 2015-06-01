package com.airport.conveyor.graph;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.store.ConveyorFactory.ConveyorType;
import com.airport.conveyor.vertex.ConveyorVertex;

/**
 * Conveyor graph store updater with give provide function to update store.
 * 
 * @author Lokesh Gupta
 *
 */
public interface ConveyorGraphStoreUpdater {

	/**
	 * Add conveyor in store.
	 * 
	 * @param cost
	 * @param from
	 * @param to
	 * @param conveyorType
	 * @return
	 */
	Conveyor addConveyor(Double cost, String from, String to, ConveyorType conveyorType);
	
	/**
	 * Add vertex in store.
	 * 
	 * @param vertexName
	 * @return
	 */
	ConveyorVertex addVertex(String vertexName);
}
