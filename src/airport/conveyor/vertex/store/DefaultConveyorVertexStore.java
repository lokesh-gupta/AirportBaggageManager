package airport.conveyor.vertex.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import airport.conveyor.vertex.ConveyorVertex;
import airport.conveyor.vertex.DefaultConveyorVertex;
import airport.exception.IllegalStateException;
import airport.exception.MethodNotImplementedException;

/**
 * Default implementation of conveyor vertex store
 * 
 * maintain unique vertex only.
 * 
 * @author logupta
 *
 */
public class DefaultConveyorVertexStore extends ConveyorVertexStore{

	/**
	 * Collection for store.
	 */
	private List<ConveyorVertex> conveyorVertexs = new LinkedList<ConveyorVertex>();
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#getAllVertex()
	 */
	@Override
	public List<ConveyorVertex> getAllVertex() {
		return Collections.unmodifiableList(conveyorVertexs);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#addVertex(java.lang.String)
	 */
	@Override
	public ConveyorVertex addVertex(String vertexName){
		ConveyorVertex vertex_new = new DefaultConveyorVertex(vertexName);
		
		for (ConveyorVertex vertex_old : conveyorVertexs) {
			if(vertex_new.equals(vertex_old)){
				return vertex_old;
			}
		}
		
		conveyorVertexs.add(vertex_new);
		
		return vertex_new;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#addVertex(airport.conveyor.vertex.ConveyorVertex)
	 */
	@Override
	public boolean addVertex(ConveyorVertex vertex) {
		return conveyorVertexs.contains(vertex) ? false : conveyorVertexs.add(vertex);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#removeVertex(java.lang.String, boolean)
	 */
	@Override
	public ConveyorVertex removeVertex(String vertexName, boolean forceFull) throws IllegalStateException{
		throw new MethodNotImplementedException();
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#removeVertex(airport.conveyor.vertex.ConveyorVertex, boolean)
	 */
	@Override
	public boolean removeVertex(ConveyorVertex conveyorVertex, boolean forceFull) throws IllegalStateException{
		throw new MethodNotImplementedException();	
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#getConveyorVertex(java.lang.String)
	 */
	@Override
	public ConveyorVertex getConveyorVertex(String vertexName){
		for (ConveyorVertex vertex_old : conveyorVertexs) {
			if(vertex_old.getName().equals(vertexName)){
				return vertex_old;
			}
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#isExists(airport.conveyor.vertex.ConveyorVertex)
	 */
	@Override
	public boolean isExists(ConveyorVertex conveyorVertex){
		return conveyorVertexs.contains(conveyorVertex);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.store.ConveyorVertexStore#isExists(java.lang.String)
	 */
	@Override
	public boolean isExists(String vertexName) {
		return null != getConveyorVertex(vertexName);
	}
}
