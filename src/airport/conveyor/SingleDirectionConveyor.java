package airport.conveyor;

import airport.conveyor.vertex.ConveyorVertex;
import airport.exception.FeatureNotSupportedException;

/**
 * 
 * Basic conveyor and support only one direction flow.
 * 
 * @author Lokesh Gupta
 *
 */
public class SingleDirectionConveyor extends AbstractConveyor{

	/**
	 * @param cost
	 * @param from
	 * @param to
	 */
	public SingleDirectionConveyor(ConveyorTravelCost cost, ConveyorVertex from, ConveyorVertex to) {
		super(cost, from, to);
	}
	
	/**
	 * @param cost
	 * @param from
	 * @param to
	 */
	public SingleDirectionConveyor(Double cost, ConveyorVertex from, ConveyorVertex to) {
		this(new ConveyorTravelCost(cost), from, to);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.AbstractConveyor#switchConveyorDirection(airport.conveyor.ConveyorTravelCost)
	 */
	@Override
	public void switchConveyorDirection(ConveyorTravelCost cost) {
		throw new FeatureNotSupportedException();
	}
	
	 /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof SingleDirectionConveyor)) return false;

         SingleDirectionConveyor that = (SingleDirectionConveyor) o;

         if (!verticesFrom.equals(that.verticesFrom)) return false;
         if (!verticesTo.equals(that.verticesTo)) return false;

         return true;
     }

     /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
     public int hashCode() {
         int result = verticesFrom.hashCode();
         result = 31 * result + verticesTo.hashCode();
         return result;
     }
     
     /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
     public String toString() {
         return "SingleDirectionConveyor{" +
                 "verticesFrom=" + verticesFrom +
                 ", verticesTo=" + verticesTo +
                 '}';
     }
}
