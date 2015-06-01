package airport.conveyor.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import airport.conveyor.Conveyor;
import airport.conveyor.ConveyorTravelCost;
import airport.conveyor.store.ConveyorFactory.ConveyorType;
import airport.conveyor.vertex.ConveyorVertex;

/**
 * Default implementation of conveyor.
 * 
 * @author Lokesh Gupta
 *
 */
public class DefaultConveyorStore extends ConveyorStore{

	/**
	 *  Collection for conveyors.
	 */
	private List<Conveyor> conveyors = new LinkedList<Conveyor>();
	
	/* (non-Javadoc)
	 * @see airport.conveyor.store.ConveyorStore#getAllConveyor()
	 */
	@Override
	public List<Conveyor> getAllConveyor() {
		return Collections.unmodifiableList(conveyors);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.store.ConveyorStore#getConveyor(airport.conveyor.ConveyorTravelCost, airport.conveyor.vertex.ConveyorVertex, airport.conveyor.vertex.ConveyorVertex, airport.conveyor.store.ConveyorFactory.ConveyorType)
	 */
	@Override
	public Conveyor getConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to, ConveyorType conveyorType) {
		Conveyor conveyor_new = getConveyorObject(cost, from, to, conveyorType);
		
		for (Conveyor conveyor_old : conveyors) {
			if(conveyor_new.equals(conveyor_old)){
				return conveyor_old;
			}
		}
		
		conveyors.add(conveyor_new);
		
		return conveyor_new;
	}
}
