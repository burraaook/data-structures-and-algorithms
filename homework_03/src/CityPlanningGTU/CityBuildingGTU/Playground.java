
package CityBuildingGTU;

/**
 * 
 * Playground class represents a playground which can be constructible an extends from CityBuilding class. 
 * It has no private field extra from CityBuilding, but Height is initizialized 1 automatically.
 * @version 1.0 1.03.2022
 * @author Burak Kocausta
 */ 
public class Playground extends CityBuilding implements Cloneable {

	/**
	 * All fields initialized to 0.
	 * @throws Exception if length or height is negative.
	 */ 
	public Playground ( ) throws Exception {
		super( 1, 1, 1 );
	}

	/**
	 * Initializes the fields according to given parameters, and height is initialized to 1.
	 * @param length length of the playground.
	 * @param position position of the playground.
	 * @throws Exception if length or position are negative.
	 */  
	public Playground ( int position, int length ) throws Exception {
		super( position, length, 1 );
	}
	
	/**
	 * When focusing this classes objects, length is returned.
	 * @return Object
	 */ 	
	@Override
	public Object focus( ) {
		Integer retval = Integer.valueOf( super.getLength() );
		return retval;
	}
	
	/**
	 * Overriden toString method to generate String representation of object.
	 * @return String
	 */ 
	@Override
	public String toString ( ) {
		return String.format( "%-10s %-3d %-3d %-3d", "Playground", this.getPosition()
						, this.getLength() ,this.getHeight() ," " );
	}

    /**
     * Overriden clone method to make deep copy.
     * @return Object
     */ 
	@Override
	public Object clone( ) {
		try {
			Playground copy = ( Playground ) super.clone();
			return copy;
		}
		catch(Exception e) {
			return null;
		}
	}

    /**
     * Returns unique number.
     * @return int
     */
	@Override
	public int hashCode ( ) {
		int result = super.hashCode();
		result *= 11;
		return result;
	}

	/**
	 * Overriden equals method to check if object is equal to another object.
	 * @return boolean
	 */ 	
    @Override
    public boolean equals ( Object other ) {
    	if ( other != null && other instanceof Playground ) {

            final Playground otherPlayground = ( Playground ) other; 
    		if ( otherPlayground.getPosition() == this.getPosition() 
    				&& otherPlayground.getLength() == this.getLength() 
    				&& otherPlayground.getHeight() == this.getHeight() ) {
    			return true;
    		}
    		return false;
    	}
    	return false;
    } 

}