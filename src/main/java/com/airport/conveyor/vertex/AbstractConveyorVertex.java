package com.airport.conveyor.vertex;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.airport.conveyor.Conveyor;

/**
 * Basic implementation of vertex 
 * like maintain it final name
 * 
 * name is final and can't be change. Vertex object define unique by name only.
 * 
 * @author Lokesh Gupta
 *
 */
public abstract class AbstractConveyorVertex implements ConveyorVertex{

	/**
	 *  Name of vertex must be unique and should assign intital only.
	 */
	protected final String name;
	
	/**
	 *  All conveyor attached to vertex
	 */
	protected List<Conveyor> converyors = new LinkedList<Conveyor>();
	
	/**
	 * @param name
	 */
	public AbstractConveyorVertex(String name){
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.ConveyorVertex#getAllConveyor()
	 */
	@Override
	public List<Conveyor> getAllConveyor() {
		return Collections.unmodifiableList(converyors);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.ConveyorVertex#removeConveyor(airport.conveyor.Conveyor)
	 */
	@Override
	public boolean removeConveyor(Conveyor conveyor) {
		return converyors.remove(conveyor);
	}
	
	/* (non-Javadoc)
	 * @see airport.conveyor.vertex.ConveyorVertex#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractConveyorVertex)) return false;

        AbstractConveyorVertex that = (AbstractConveyorVertex) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractConveyorVertex{" +
                "name='" + name + '\'' +
                '}';
    }
}
