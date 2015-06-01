package com.airport.conveyor.path;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airport.conveyor.Conveyor;
import com.airport.conveyor.SingleDirectionConveyor;
import com.airport.conveyor.path.ShortestPathAlgo.ShortestPathCollection;
import com.airport.conveyor.vertex.ConveyorVertex;
import com.airport.conveyor.vertex.DefaultConveyorVertex;


public class ShortestPathAlgoTest {

	private LinkedList<ConveyorVertex> vertexList;
	private ConveyorVertex v1;
	private ConveyorVertex v2;
	private ConveyorVertex v3;
	private ConveyorVertex v4;
	private ConveyorVertex v5;
	private ConveyorVertex v6;
	
	@Before
	public void init(){
		v1 = new DefaultConveyorVertex("V1");
		v2 = new DefaultConveyorVertex("V2");
		v3 = new DefaultConveyorVertex("V3");
		v4 = new DefaultConveyorVertex("V4");
		v5 = new DefaultConveyorVertex("V5");
		v6 = new DefaultConveyorVertex("V6");
		
		Conveyor c1 = new SingleDirectionConveyor(5d , v1, v2);
		v1.addConveyor(c1);
		
		Conveyor c2 = new SingleDirectionConveyor(10d , v1, v3);
		v1.addConveyor(c2);
		
		Conveyor c3 = new SingleDirectionConveyor(5d , v2, v4);
		v2.addConveyor(c3);
		
		Conveyor c4 = new SingleDirectionConveyor(8d , v2, v5);
		v2.addConveyor(c4);
		
		Conveyor c5 = new SingleDirectionConveyor(4d , v4, v5);
		v4.addConveyor(c5);
		
		Conveyor c6 = new SingleDirectionConveyor(1d , v3, v6);
		v3.addConveyor(c6);
		
		Conveyor c7 = new SingleDirectionConveyor(1d , v6, v5);
		v6.addConveyor(c7);
		
		vertexList = new LinkedList<ConveyorVertex>();
		
		vertexList.add(v1);
		vertexList.add(v2);
		vertexList.add(v3);
		vertexList.add(v4);
		vertexList.add(v5);
		vertexList.add(v6);
	}
	
	
	@Test
	public void findShortestPath(){
		ShortestPathAlgo shortestPathAlgo = new ShortestPathDefaultAlgo();
		
		ShortestPathCollection shortestPathCollection = shortestPathAlgo.build(vertexList);
		
		Assert.assertTrue(12.0 == shortestPathCollection.getShortestPath(v1, v5).getCost());
	}
}
