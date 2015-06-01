package airport.query;

import airport.conveyor.graph.ConveyorGraphManager;
import airport.conveyor.graph.ConveyorGraphStore;
import airport.conveyor.path.ShortestPathAlgo.ShortestPath;
import airport.conveyor.vertex.ConveyorVertex;
import airport.exception.QueryCompilationFailException;
import airport.exception.QueryExecutionFailException;
import airport.flight.FlightStore;
import airport.logging.LogginConstants;
import airport.query.BagPathQuery.BagRequestInfo;

/**
 * Give result to find bag path from one location to another location.
 * Format: <bag_number> <entry_point> <flight_id/VertexId>
 * 
 * @author Lokesh Gupta
 *
 */
public class BagPathQuery extends AbstractQuery<BagRequestInfo>{

	private static final String SPLLITER = " ";
	/**
	 *  Flight store, contains all flight list.
	 */
	private FlightStore flightStore;
	
	/**
	 * Conveyor graph store manager
	 */
	private ConveyorGraphStore conveyorGraphStore;
	
	/**
	 * Conveyor graph manager
	 */
	private ConveyorGraphManager conveyorGraphManager;
	
	public BagPathQuery(String query) {
		super(query);
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#compileQuerySpecific()
	 */
	@Override
	protected void compileQuerySpecific() throws QueryCompilationFailException {
		String[] words = query.split(SPLLITER);
		
		if(words.length != 3){
			throw new QueryCompilationFailException("Query is not in right format.");
		}
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#executeQuerySpecific()
	 */
	@Override
	protected void executeQuerySpecific() throws QueryExecutionFailException {
		String[] words = query.split(SPLLITER);
		String from = words[1];
		
		String to;
		if(null == flightStore.getFlight(words[2])){
			to = words[2];
		}else{
			to = flightStore.getFlight(words[2]).getFlightGate();
		}
		
		ConveyorVertex vertex_from = conveyorGraphStore.getConveyorVertex(from);
		ConveyorVertex vertex_to = conveyorGraphStore.getConveyorVertex(to);
		
		if(null == vertex_from || null == vertex_to){
			genrateFailMsg("Given flight/gate not exists in conveyor store.");
			
			throw new QueryExecutionFailException(failedMsg);
		}
		
		ShortestPath shortestPath = conveyorGraphManager.getShortestPath(vertex_from, vertex_to);
		
		if(null == shortestPath){
			genrateFailMsg("Error in path collection.");
			
			throw new QueryExecutionFailException(failedMsg);
		}
		
		result = new BagRequestInfo(words[0], shortestPath) ;
		
		if(LogginConstants.isSTD_OUT_Enable()){
			System.out.println("Bag move path : " + result.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#resultQuerySpecific()
	 */
	@Override
	protected BagRequestInfo resultQuerySpecific() {
		return result;
	}
	
	/**
	 * @param flightStore
	 */
	public void setFlightStore(FlightStore flightStore) {
		this.flightStore = flightStore;
	}
	
	/**
	 * @param conveyorStoreManager
	 */
	public void setConveyorGraphStore(ConveyorGraphStore conveyorGraphStore) {
		this.conveyorGraphStore = conveyorGraphStore;
	}
	
	/**
	 * @param conveyorGraphManager
	 */
	public void setConveyorGraphManager(ConveyorGraphManager conveyorGraphManager) {
		this.conveyorGraphManager = conveyorGraphManager;
	}
	
	/**
	 * Maintain bag moving request result with Possible path.
	 * 
	 * @author logupta
	 *
	 */
	public static class BagRequestInfo{
		/**
		 *  Id of bag moving request.
		 */
		private String requestId;
		
		/**
		 * Result possible path. 
		 */
		private ShortestPath shortestPath;
		
		/**
		 * @param requestId
		 * @param shortestPath
		 */
		public BagRequestInfo(String requestId, ShortestPath shortestPath) {
			this.requestId = requestId;
			this.shortestPath = shortestPath;
		}
		
		/**
		 * @return path info with cost.
		 */
		public String getResult(){
			return requestId + " " + shortestPath.toString();
		}
	}
}
