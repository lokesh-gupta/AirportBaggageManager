package com.airport.conveyor.graph;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.path.ShortestPathAlgo;
import com.airport.conveyor.path.ShortestPathAlgo.ShortestPath;
import com.airport.conveyor.vertex.ConveyorVertex;
import com.airport.query.BasicConveyorQuery;

/**
 * Manage all bags flow with include vertex and conveyor and 
 * path from their graph.
 * 
 * 
 * @author Lokesh Gupta
 *
 */
public interface ConveyorGraphManager {

	/**
	 * Shortest path from one vertex to another.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	ShortestPath getShortestPath(ConveyorVertex from, ConveyorVertex to);

	/**
	 * Shortest path from one vertex to another.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	ShortestPath getShortestPath(String from, String to);
	
	/**
	 * Use this path to find shortest path between vertex to move bags.
	 * 
	 * @param shortestPathAlgo
	 */
	void setShortestPathAlgo(ShortestPathAlgo shortestPathAlgo);
	
	/**
	 * Add new conveyor and its vertex in graph.
	 * 
	 * @param query
	 * @return
	 */
	Conveyor addConveyorInStore(BasicConveyorQuery query);
}
