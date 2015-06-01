package airport.conveyor.path;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import airport.conveyor.Conveyor;
import airport.conveyor.vertex.ConveyorVertex;

/**
 * Alog give logic to find min cost path in graph 
 * from one point to another.
 * 
 * @author Lokesh Gupta
 *
 */
public interface ShortestPathAlgo {

	/**
	 * Build result and store in collection.
	 * 
	 * @param vertexs
	 * @return ShortestPathCollection : collection of all shortest path from one point to another.
	 */
	ShortestPathCollection build(List<ConveyorVertex> vertexs);
	
	
	/**
	 * 
	 * Collection to store shortest path from one point to another.
	 * 
	 * @author Lokesh Gupta
	 *
	 */
	public static class ShortestPathCollection{
		/**
		 *  Map to store all data
		 */
		private HashMap<String, ShortestPath> pathMap = new HashMap<String, ShortestPath>();
		
		/**
		 * @param from
		 * @param to
		 * @return ShortestPath : path with all intermediate nodes. 
		 */
		public ShortestPath getShortestPath(ConveyorVertex from, ConveyorVertex to){
			return pathMap.get(getKey(from, to));
		}
		
		/**
		 * Update whole store.
		 * 
		 * @param pathMap
		 */
		protected void updatePathMap(HashMap<String, ShortestPath> pathMap){
			this.pathMap = pathMap;
		}
		
		/**
		 * Update particular path cost and intermediate nodes. .
		 * 
		 * @param from
		 * @param to
		 * @param shortestPath
		 */
		protected void updatePathValue(ConveyorVertex from, ConveyorVertex to, ShortestPath shortestPath){
			pathMap.put(getKey(from, to), shortestPath);
		}
		
		/**
		 * Store path on this key only
		 * 
		 * @param from
		 * @param to
		 * @return : key to store path in store.
		 */
		private String getKey(ConveyorVertex from, ConveyorVertex to){
			return from.getName() + "--" + to.getName();
		}
	}
	
	/**
	 * Shortesh path : maintain path and its cost.
	 * 
	 * @author Lokesh Gupta
	 *
	 */
	public static class ShortestPath{
		/**
		 * Path with all intermediate nodes.
		 */
		private LinkedList<ConveyorVertex> path;
		
		/**
		 * Cost of full path
		 */
		private double cost;
		
		/**
		 * @param path
		 * @param cost
		 */
		public ShortestPath(LinkedList<ConveyorVertex> path, double cost){
			this.path = new LinkedList<ConveyorVertex>(path);
			this.cost = cost;
		}
		
		/**
		 * Calculate cost by itself.
		 * 
		 * @param path
		 */
		public ShortestPath(LinkedList<ConveyorVertex> path){
			this.path = new LinkedList<ConveyorVertex>(path);;
			
			for (int i = 0; i < path.size() - 1; i++) {
				ConveyorVertex current = path.get(i);
				for (Conveyor conveyor : current.getAllConveyor()) {
					if(conveyor.getEnd().equals(path.get(i+1))){
						cost += conveyor.getCost().getCost();
						break;
					}
				}
			}
		}
		
		/**
		 * @return : cost
		 */
		public double getCost() {
			return cost;
		}
		
		/**
		 * @return path
		 */
		public List<ConveyorVertex> getPath() {
			return Collections.unmodifiableList(path);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			String str = "";
			for (ConveyorVertex conveyorVertex : path) {
				str += conveyorVertex.getName() + " ";
			}
			
			str += ": "+ cost;
			
			return str;
		}
	}
}
