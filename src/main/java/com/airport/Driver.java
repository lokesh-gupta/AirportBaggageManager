package com.airport;

import java.util.LinkedList;
import java.util.List;

import com.airport.controller.BaggageManager;
import com.airport.logging.LogginConstants;
import com.airport.query.BagPathQuery;
import com.airport.query.BasicConveyorQuery;
import com.airport.query.FlightQuery;
import com.airport.query.BagPathQuery.BagRequestInfo;

public class Driver {

	public static void main(String[] args) {
		
		LogginConstants.setSTD_OUT_Enable(false);
		
		BaggageManager baggageManager = new BaggageManager();
		
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("Concourse_A_Ticketing A5 5"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A5 BaggageClaim 5"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A5 A10 4"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A5 A1 6"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A1 A2 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A2 A3 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A3 A4 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A10 A9 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A9 A8 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A8 A7 1"));
		baggageManager.updateConveyorGraph(new BasicConveyorQuery("A7 A6 1"));
		
		baggageManager.addFlightInAirport(new FlightQuery("UA10 A1 MIA 08:00"));
		baggageManager.addFlightInAirport(new FlightQuery("UA11 A1 LAX 09:00"));
		baggageManager.addFlightInAirport(new FlightQuery("UA12 A1 JFK 09:45"));
		baggageManager.addFlightInAirport(new FlightQuery("UA13 A2 JFK 08:30"));
		baggageManager.addFlightInAirport(new FlightQuery("UA14 A2 JFK 09:45"));
		baggageManager.addFlightInAirport(new FlightQuery("UA15 A2 JFK 10:00"));
		baggageManager.addFlightInAirport(new FlightQuery("UA16 A3 JFK 09:00"));
		baggageManager.addFlightInAirport(new FlightQuery("UA17 A4 MHT 09:15"));
		baggageManager.addFlightInAirport(new FlightQuery("UA18 A5 LAX 10:15"));
		

		List<BagRequestInfo> list = new LinkedList<BagPathQuery.BagRequestInfo>();
		
		list.add(baggageManager.bagPathRequest(new BagPathQuery("0001 Concourse_A_Ticketing UA12")));
		list.add(baggageManager.bagPathRequest(new BagPathQuery("0002 A5 UA17")));
		list.add(baggageManager.bagPathRequest(new BagPathQuery("0003 A2 UA10")));
		list.add(baggageManager.bagPathRequest(new BagPathQuery("0004 A8 UA18")));
		list.add(baggageManager.bagPathRequest(new BagPathQuery("0005 A7 BaggageClaim")));

		for (BagRequestInfo bagRequestInfo : list) {
			System.out.println(bagRequestInfo.getResult());
		}
	}
}
