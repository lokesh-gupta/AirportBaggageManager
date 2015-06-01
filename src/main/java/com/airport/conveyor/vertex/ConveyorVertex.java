package com.airport.conveyor.vertex;

import java.util.List;

import com.airport.conveyor.Conveyor;

/**
 * Vertex of conveyor.
 * 
 * Unique by its name, which can't be change.
 * 
 * @author logupta
 *
 */
public interface ConveyorVertex {

	/**
	 * Get all connected conveyor.
	 * 
	 * @return
	 */
	List<Conveyor> getAllConveyor();
	
	/**
	 * Get Name of vertex.
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * Add conveyor in vertex.
	 * 
	 * @param conveyor
	 * @return : status 
	 */
	boolean addConveyor(Conveyor conveyor);
	
	/**
	 * Remove conveyor from vertex.
	 * 
	 * @param conveyor
	 * @return
	 */
	boolean removeConveyor(Conveyor conveyor);
}
