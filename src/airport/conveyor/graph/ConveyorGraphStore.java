package airport.conveyor.graph;

import airport.conveyor.vertex.ConveyorVertex;


/**
 * Conveyor graph create by vertex and conveyor
 * This class maintain its store.
 * 
 * @author logupta
 *
 */
public interface ConveyorGraphStore {

	/**
	 * Get vertex from vertex name.
	 * return null if not exists in store.
	 * 
	 * @param vertexName
	 * @return vertex object
	 */
	ConveyorVertex getConveyorVertex(String vertexName);
	
	/**
	 * Give status of vertex in store.
	 * 
	 * @param conveyorVertex
	 * @return status
	 */
	boolean isConveyorVertexExists(ConveyorVertex conveyorVertex);
}
