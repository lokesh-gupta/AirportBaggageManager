package airport.query;

import airport.conveyor.Conveyor;
import airport.conveyor.graph.ConveyorGraphStoreUpdater;
import airport.conveyor.store.ConveyorFactory.ConveyorType;
import airport.exception.QueryCompilationFailException;
import airport.exception.QueryExecutionFailException;
import airport.logging.LogginConstants;

/**
 * Conveyor information in query.
 * Format: <Node 1> <Node 2> <travel_time>
 * 
 * @author Lokesh Gupta
 *
 */
public class BasicConveyorQuery extends AbstractQuery<Conveyor>{

	private static String SPLLITER = " ";
	
	/**
	 * Conveyor graph
	 */
	private ConveyorGraphStoreUpdater conveyorGraphStoreUpdater;
	
	/**
	 * @param query
	 */
	public BasicConveyorQuery(String query) {
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
		
		try{
			Double.valueOf(words[2]);
		}catch(NumberFormatException e){
			throw new QueryCompilationFailException("Conveyor travel time should be number only.");
		}
	}
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#executeQuerySpecific()
	 */
	@Override
	protected void executeQuerySpecific() throws QueryExecutionFailException {
		String[] words = query.split(SPLLITER);
		
		result = conveyorGraphStoreUpdater.addConveyor(Double.valueOf(words[2]), words[0], words[1], ConveyorType.SingleDirectionConstantSpeed);
		
		if(LogginConstants.isSTD_OUT_Enable()){
			System.out.println("Add conveyor to store : " + result.toString());
		}
	}  
	
	/* (non-Javadoc)
	 * @see airport.query.AbstractQuery#resultQuerySpecific()
	 */
	@Override
	protected Conveyor resultQuerySpecific() {
		return result;
	}
	
	/**
	 * @param conveyorStoreUpdater
	 */
	public void setConveyorGraphStoreManager(ConveyorGraphStoreUpdater conveyorGraphStoreUpdater) {
		this.conveyorGraphStoreUpdater = conveyorGraphStoreUpdater;
	}
}
