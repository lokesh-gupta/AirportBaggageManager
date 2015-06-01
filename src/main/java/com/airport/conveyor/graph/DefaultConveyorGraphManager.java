package com.airport.conveyor.graph;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.ConveyorTravelCost;
import com.airport.conveyor.path.ShortestPathAlgo;
import com.airport.conveyor.path.ShortestPathDefaultAlgo;
import com.airport.conveyor.path.ShortestPathAlgo.ShortestPath;
import com.airport.conveyor.path.ShortestPathAlgo.ShortestPathCollection;
import com.airport.conveyor.store.ConveyorStore;
import com.airport.conveyor.store.DefaultConveyorStore;
import com.airport.conveyor.store.ConveyorFactory.ConveyorType;
import com.airport.conveyor.vertex.ConveyorVertex;
import com.airport.conveyor.vertex.store.ConveyorVertexStore;
import com.airport.conveyor.vertex.store.DefaultConveyorVertexStore;
import com.airport.query.BasicConveyorQuery;

/**
 * Default implementation of airport conveyor graph.
 * 
 * Use all default implementation if not provided in constructor.
 * 
 * @author Lokesh Gupta
 *
 */
public class DefaultConveyorGraphManager implements ConveyorGraphManager, ConveyorGraphStore {

	private ConveyorStore conveyorStore;
	private ConveyorVertexStore conveyorVertexStore;
	private ShortestPathAlgo shortestPathAlgo;
	private ShortestPathCollection shortestPathCollection; 
	private ConveyorGraphStoreUpdater conveyorStoreUpdater;
	
	
	/**
	 *  Default constructor.
	 *  will give default implementation of conveyor and vertex store.
	 */
	public DefaultConveyorGraphManager() {
		this(new DefaultConveyorStore(), new DefaultConveyorVertexStore());
	}
	
	/**
	 * @param conveyorFactory
	 * @param conveyorVertexFactory
	 */
	public DefaultConveyorGraphManager(ConveyorStore conveyorFactory, ConveyorVertexStore conveyorVertexFactory) {
		this.conveyorStore = conveyorFactory;
		this.conveyorVertexStore = conveyorVertexFactory;
		this.conveyorStoreUpdater = new ConveyorStoreUpdaterImpl();
	}
	
	/**
	 * Add vertex in store.
	 * 
	 * @param vertexName
	 * @return
	 */
	private ConveyorVertex addVertex(String vertexName) {
		return conveyorVertexStore.addVertex(vertexName);
	}

	/**
	 * Responsible to update store and add conveyor and its vertex of it.
	 * 
	 * @param cost
	 * @param from
	 * @param to
	 * @param conveyorType
	 * @return
	 */
	private Conveyor addConveyor(Double cost, String from, String to, ConveyorType conveyorType) {
		ConveyorVertex fromVertex = conveyorVertexStore.addVertex(from);
		ConveyorVertex toVertex = conveyorVertexStore.addVertex(to);
		
		ConveyorTravelCost travelCost = new ConveyorTravelCost(cost);
		
		Conveyor conveyor = conveyorStore.getConveyor(travelCost, fromVertex, toVertex, conveyorType);
		
		fromVertex.addConveyor(conveyor);
		
		conveyor = conveyorStore.getConveyor(travelCost, toVertex, fromVertex, conveyorType);
		toVertex.addConveyor(conveyor);
		
		return conveyor;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphStore#getConveyorVertex(java.lang.String)
	 */
	@Override
	public ConveyorVertex getConveyorVertex(String vertexName) {
		return conveyorVertexStore.getConveyorVertex(vertexName);
	}

	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphStore#isConveyorVertexExists(airport.conveyor.vertex.ConveyorVertex)
	 */
	@Override
	public boolean isConveyorVertexExists(ConveyorVertex conveyorVertex) {
		return conveyorVertexStore.isExists(conveyorVertex);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphManager#getShortestPath(airport.conveyor.vertex.ConveyorVertex, airport.conveyor.vertex.ConveyorVertex)
	 */
	@Override
	public ShortestPath getShortestPath(ConveyorVertex from, ConveyorVertex to) {
		if(null == shortestPathCollection){
			if(null == shortestPathAlgo){
				shortestPathAlgo = new ShortestPathDefaultAlgo();
			}
			
			shortestPathCollection = shortestPathAlgo.build(conveyorVertexStore.getAllVertex());
		}
		
		
		return shortestPathCollection.getShortestPath(from, to);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphManager#getShortestPath(java.lang.String, java.lang.String)
	 */
	@Override
	public ShortestPath getShortestPath(String from, String to) {
		return getShortestPath(conveyorVertexStore.addVertex(from), conveyorVertexStore.getConveyorVertex(to));
	}

	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphManager#setShortestPathAlgo(airport.conveyor.path.ShortestPathAlgo)
	 */
	@Override
	public void setShortestPathAlgo(ShortestPathAlgo shortestPathAlgo) {
		this.shortestPathAlgo = shortestPathAlgo;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.graph.ConveyorGraphManager#addConveyorInStore(airport.query.BasicConveyorQuery)
	 */
	@Override
	public Conveyor addConveyorInStore(BasicConveyorQuery query) {
		query.setConveyorGraphStoreManager(conveyorStoreUpdater);
		
		return query.compile().execute().getResult();
	}
	
	/**
	 * Store update can update data in airport conveyor graph.
	 * 
	 * @author Lokesh Gupta
	 *
	 */
	class ConveyorStoreUpdaterImpl implements ConveyorGraphStoreUpdater{
		
		/**
		 *  
		 */
		private ConveyorStoreUpdaterImpl(){
			
		}
		
		/* (non-Javadoc)
		 * @see airport.conveyor.graph.ConveyorGraphStoreUpdater#addVertex(java.lang.String)
		 */
		@Override
		public ConveyorVertex addVertex(String vertexName) {
			return DefaultConveyorGraphManager.this.addVertex(vertexName);
		}

		/* (non-Javadoc)
		 * @see airport.conveyor.graph.ConveyorGraphStoreUpdater#addConveyor(java.lang.Double, java.lang.String, java.lang.String, airport.conveyor.store.ConveyorFactory.ConveyorType)
		 */
		@Override
		public Conveyor addConveyor(Double cost, String from, String to, ConveyorType conveyorType) {
			return DefaultConveyorGraphManager.this.addConveyor(cost, from, to, conveyorType);
		}
	}
}
