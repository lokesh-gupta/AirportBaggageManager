package com.airport.place;

import com.airport.conveyor.vertex.AbstractConveyorVertex;

/**
 * 
 * Will contain any geo logical place like city, village, state etc.
 * 
 * @author Lokeh Gupta
 *
 */
public class Place {

	/**
	 * Name of the place.
	 */
	private final String name;
	
	
	/**
	 * Description about the place
	 */
	private String description;
	
	/**
	 * @param name
	 */
	public Place(String name) {
		this.name = name;
	}
	
	/**
	 * @return place name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param discription
	 */
	public void setDescription(String discription) {
		this.description = discription;
	}
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractConveyorVertex)) return false;

        Place that = (Place) o;

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
}
