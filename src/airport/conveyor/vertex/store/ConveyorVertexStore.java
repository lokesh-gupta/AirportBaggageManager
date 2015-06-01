package airport.conveyor.vertex.store;

import java.util.List;

import airport.conveyor.vertex.ConveyorVertex;
import airport.exception.IllegalStateException;

/**
 * Conveyor vertex store, maintain all vertex in airport.
 * 
 * @author Lokesh Gupta
 *
 */
public abstract class ConveyorVertexStore {

	/**
	 * Add airport vertex in store.
	 * 
	 * @param vertexName
	 * @return new created vertex object.
	 */
	public abstract ConveyorVertex addVertex(String vertexName);
	
	/**
	 * Add airport vertex in store.
	 * 
	 * @param vertex object
	 * @return new created vertex object.
	 */
	public abstract boolean addVertex(ConveyorVertex vertex);
	
	/**
	 * Remove airport vertex from store
	 * 
	 * validate state before remove, if not possible throw IllegalStateException exception.
	 * if force full true remove even state validation fails.
	 * 
	 * @param vertexName
	 * @param forceFull : remove with status check.
	 * @return removed vertex.
	 * @throws IllegalStateException
	 */
	public abstract ConveyorVertex removeVertex(String vertexName, boolean forceFull) throws IllegalStateException;
	
	/**
	 * Remove airport vertex from store
	 * 
	 * validate state before remove, if not possible throw IllegalStateException exception.
	 * if force full true remove even state validation fails.
	 * 
	 * @param conveyorVertex
	 * @param forceFull : remove with status check.
	 * @return status of remove
	 * @throws IllegalStateException
	 */
	public abstract boolean removeVertex(ConveyorVertex conveyorVertex, boolean forceFull) throws IllegalStateException;
	
	/**
	 * 
	 * Vertex object from store, if not exists return null
	 * 
	 * @param vertexName
	 * @return vertex object from store. 
	 */
	public abstract ConveyorVertex getConveyorVertex(String vertexName);
	
	/**
	 * Is vertex exists in store.
	 * 
	 * @param conveyorVertex
	 * @return status
	 */
	public abstract boolean isExists(ConveyorVertex conveyorVertex);
	
	/**
	 *  Is vertex exists in store.
	 *  
	 * @param vertexName
	 * @return status
	 */
	public abstract boolean isExists(String vertexName);
	
	/**
	 * Get all vertex in store.
	 * 
	 * @return
	 */
	public abstract List<ConveyorVertex> getAllVertex();
}
