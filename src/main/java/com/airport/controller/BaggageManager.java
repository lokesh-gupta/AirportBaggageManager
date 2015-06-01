package com.airport.controller;

import com.airport.conveyor.graph.ConveyorGraphManager;
import com.airport.conveyor.graph.ConveyorGraphStore;
import com.airport.conveyor.graph.DefaultConveyorGraphManager;
import com.airport.flight.DefaultFlightStore;
import com.airport.flight.FlightStore;
import com.airport.query.BagPathQuery;
import com.airport.query.BagPathQuery.BagRequestInfo;
import com.airport.query.BasicConveyorQuery;
import com.airport.query.FlightQuery;

/**
 * Responsible to manage all baggage, its flow
 * 
 * @author Lokesh Gupta
 *
 */
public class BaggageManager {

	private ConveyorGraphManager conveyorManager = new DefaultConveyorGraphManager();
	private FlightStore flightStore = new DefaultFlightStore();
	
	public void updateConveyorGraph(BasicConveyorQuery query){
		conveyorManager.addConveyorInStore(query);
	}
	
	public void addFlightInAirport(FlightQuery query){
		query.setFlightStore(flightStore);
		query.compile().execute();
	}
	
	public BagRequestInfo bagPathRequest(BagPathQuery bagPathQuery){
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		
		return bagPathQuery.compile().execute().getResult();
	}
}
