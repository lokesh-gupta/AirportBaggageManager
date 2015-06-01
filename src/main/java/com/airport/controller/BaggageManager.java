package com.airport.controller;

import com.airport.conveyor.graph.ConveyorGraphManager;
import com.airport.conveyor.graph.ConveyorGraphStore;
import com.airport.conveyor.graph.DefaultConveyorGraphManager;
import com.airport.flight.DefaultFlightStore;
import com.airport.flight.FlightStore;
import com.airport.logging.LogginConstants;
import com.airport.query.BagPathQuery;
import com.airport.query.BasicConveyorQuery;
import com.airport.query.FlightQuery;

/**
 * Responsible to manage all baggage, its flow
 * 
 * @author Lokesh Gupta
 *
 */
public class BaggageManager {

	public static void main(String[] args) {
		
		LogginConstants.setSTD_OUT_Enable(true);
		
		ConveyorGraphManager conveyorManager = new DefaultConveyorGraphManager();
		FlightStore flightStore = new DefaultFlightStore();
		
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("Concourse_A_Ticketing A5 5"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A5 BaggageClaim 5"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A5 A10 4"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A5 A1 6"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A1 A2 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A2 A3 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A3 A4 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A10 A9 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A9 A8 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A8 A7 1"));
		conveyorManager.addConveyorInStore(new BasicConveyorQuery("A7 A6 1"));
		
		(new FlightQuery("UA10 A1 MIA 08:00", flightStore)).compile().execute();
		(new FlightQuery("UA11 A1 LAX 09:00", flightStore)).compile().execute();
		(new FlightQuery("UA12 A1 JFK 09:45", flightStore)).compile().execute();
		(new FlightQuery("UA13 A2 JFK 08:30", flightStore)).compile().execute();
		(new FlightQuery("UA14 A2 JFK 09:45", flightStore)).compile().execute();
		(new FlightQuery("UA15 A2 JFK 10:00", flightStore)).compile().execute();
		(new FlightQuery("UA16 A3 JFK 09:00", flightStore)).compile().execute();
		(new FlightQuery("UA17 A4 MHT 09:15", flightStore)).compile().execute();
		(new FlightQuery("UA18 A5 LAX 10:15", flightStore)).compile().execute();
		

		BagPathQuery bagPathQuery = new BagPathQuery("0001 Concourse_A_Ticketing UA12");
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		System.out.println(bagPathQuery.compile().execute().getResult().getResult());
		
		bagPathQuery = new BagPathQuery("0002 A5 UA17");
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		System.out.println(bagPathQuery.compile().execute().getResult().getResult());
		
		bagPathQuery = new BagPathQuery("0003 A2 UA10");
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		System.out.println(bagPathQuery.compile().execute().getResult().getResult());
		
		bagPathQuery = new BagPathQuery("0004 A8 UA18");
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		System.out.println(bagPathQuery.compile().execute().getResult().getResult());
		
		bagPathQuery = new BagPathQuery("0005 A7 BaggageClaim");
		bagPathQuery.setConveyorGraphStore((ConveyorGraphStore) conveyorManager);
		bagPathQuery.setFlightStore(flightStore);
		bagPathQuery.setConveyorGraphManager(conveyorManager);
		System.out.println(bagPathQuery.compile().execute().getResult().getResult());
	}
}
