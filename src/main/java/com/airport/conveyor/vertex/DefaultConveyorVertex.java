package com.airport.conveyor.vertex;

import com.airport.conveyor.Conveyor;

/**
 * Default implementaion of conveyor.
 * 
 * Just to maintain rule of uniquness in store.
 * 
 * @author logupta
 *
 */
public class DefaultConveyorVertex extends AbstractConveyorVertex{

	/**
	 * @param name
	 */
	public DefaultConveyorVertex(String name) {
		super(name);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.ConveyorVertex#addConveyor(airport.conveyor.Conveyor)
	 */
	@Override
	public boolean addConveyor(Conveyor conveyor) {
		for (Conveyor conveyor_new : converyors) {
			if(conveyor.equals(conveyor_new)){
				return false;
			}
		}
		
		return converyors.add(conveyor);
	}
}

