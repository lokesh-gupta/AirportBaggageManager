package airport.conveyor.path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import airport.conveyor.Conveyor;
import airport.conveyor.vertex.ConveyorVertex;

/**
 * Shortest path also. Implemented dijkstra's algorithm
 * to find shortest path inbetween nodes.
 * 
 * @author Lokesh Gupta
 *
 */
public class ShortestPathDefaultAlgo implements ShortestPathAlgo{

	/**
	 *  Maintain wrappers for each algo loop.
	 */
	private HashMap<ConveyorVertex, ConveyorVertexWrapper> map;
	
	/* (non-Javadoc)
	 * @see airport.conveyor.path.ShortestPathAlgo#build(java.util.List)
	 */
	@Override
	public ShortestPathCollection build(List<ConveyorVertex> vertexs) {
		
		ShortestPathCollection collection = new ShortestPathCollection();

		// Loop for each vertex
		for (int i = 0; i < vertexs.size(); i++) {
			// Will take new collection for each loop.
			map = new HashMap<ConveyorVertex, ShortestPathDefaultAlgo.ConveyorVertexWrapper>();
			
			// Vertex from this vertex to each node.
			ConveyorVertex firstVertex = vertexs.get(i);
			
			// Update all min cost in wrapper min distance variable.
			ConveyorVertexWrapper last = computePaths(getWrapper(firstVertex));
			
			// Update all min value to collection.
			map.remove(firstVertex);
			for (Map.Entry<ConveyorVertex, ConveyorVertexWrapper> entry : map.entrySet()) {
				last = entry.getValue();
				if(null != collection.getShortestPath(firstVertex, last.conveyorVertex)){
					continue;
				}
				
				LinkedList<ConveyorVertex> list = new LinkedList<ConveyorVertex>();
				while(null != last){
					list.addFirst(last.conveyorVertex);
					last = last.previous;
				}
				
				for (int k = list.size() - 1; k > 0; k--) {
					ShortestPath shortestPath = new ShortestPath(list);
					collection.updatePathValue(list.getFirst(), list.getLast(), shortestPath);
					list.removeLast();
				}	
			}
		}
		
		return collection;
	}
	
	/**
	 * Implementation of dijkstra's algorithm
	 * 
	 * @param from
	 * @return
	 */
	public ConveyorVertexWrapper computePaths(ConveyorVertexWrapper from)
    {
		from.minDistance = 0.;

		// Queue to maintain all vertex with min cost priority
        PriorityQueue<ConveyorVertexWrapper> vertexQueue = new PriorityQueue<ConveyorVertexWrapper>();
        vertexQueue.add(from);

        ConveyorVertexWrapper currentVertex = from;
        while (!vertexQueue.isEmpty()) {
        	currentVertex = vertexQueue.poll();
        	
            // Visit each edge exiting currentVertex
            for (Conveyor conveyorToStudy : currentVertex.conveyorVertex.getAllConveyor())
            {
            	ConveyorVertexWrapper vertexToStudy = getWrapper(conveyorToStudy.getEnd());
                double cost = conveyorToStudy.getCost().getCost();
                double distanceThroughCurrentVertex = currentVertex.minDistance + cost;
                
                // if current cost is low then previous than update min cost
                if (distanceThroughCurrentVertex < vertexToStudy.minDistance) {
                    vertexQueue.remove(vertexToStudy);

                    vertexToStudy.minDistance = distanceThroughCurrentVertex ;
                    vertexToStudy.previous = currentVertex;
                    vertexQueue.add(vertexToStudy);
                }
            }
        }
        
        return currentVertex;
    }
	
	/**
	 * Get wrapper of current vertex.

	 * @param conveyorVertex
	 * @return
	 */
	private ConveyorVertexWrapper getWrapper(ConveyorVertex conveyorVertex){
		ConveyorVertexWrapper conveyorVertexWrapper;
		if(null == (conveyorVertexWrapper = map.get(conveyorVertex))){
			conveyorVertexWrapper = new ConveyorVertexWrapper(conveyorVertex);
			map.put(conveyorVertex, conveyorVertexWrapper);
		}
		
		return conveyorVertexWrapper;
	}
	
	/**
	 * Give more feature like maintain min distance and previous.
	 * 
	 * @author Lokesh Gupta
	 *
	 */
	private static class ConveyorVertexWrapper implements Comparable<ConveyorVertexWrapper>{
		/**
		 *  Main vertex.
		 */
		private ConveyorVertex conveyorVertex;
		
		/**
		 * Distance cost from first vertex. 
		 */
		private Double minDistance = Double.MAX_VALUE;
		
		/**
		 * Previous vertex in path. 
		 */
		private ConveyorVertexWrapper previous;
		
		/**
		 * @param conveyorVertex
		 */
		public ConveyorVertexWrapper(ConveyorVertex conveyorVertex) {
			this.conveyorVertex = conveyorVertex;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(ConveyorVertexWrapper o) {
			return this.minDistance.compareTo(o.minDistance);
		}
	}
}
