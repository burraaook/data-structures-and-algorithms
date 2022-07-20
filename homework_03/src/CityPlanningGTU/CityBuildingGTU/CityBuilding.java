
package CityBuildingGTU;

/**
 * 
 * CityBuilding class is an abstract class which can be used to derive classes for buildings.
 * @version 1.0 28.02.2022
 * @author Burak Kocausta
 */ 
public abstract class CityBuilding {
	
	/**
	 * Length, height and position of building.
	 */ 
	private int length;
	private int height;
	private int position;

	/**
	 * No parameter constructor sets the field's values to 0.
	 */ 
	public CityBuilding ( ) {
		this.length = 5;
		this.height = 5;
		this.position = 5;
	}

	/**
	 * Constructor to set every value of the class.
	 * @param length length of the building as an integer.
	 * @param height height of the building as an integer.
	 * @param position position of the building as an integer.
	 * @throws Exception If at least one of the parameter is negative.
	 */ 
	public CityBuilding ( int position, int length, int height ) throws Exception {
		try {
			this.setPosition( position );
			this.setLength( length );
			this.setHeight( height );
		}	
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * A private setter to set position.
	 * @param position integer
	 * @throws Exception if position is negative.
	 */ 
	private void setPosition ( int position ) throws Exception {

		if ( position < 0 )
			throw new Exception( "position cannot be negative!" );

		this.position = position;
	}

	/**
	 * A private setter to set length.
	 * @param position integer
	 * @throws Exception if position is negative.
	 */ 
	private void setLength ( int length ) throws Exception {

		if ( length < 0 )
			throw new Exception( "length cannot be negative!" );

		this.length = length;
	}

	/**
	 * A private setter to set height.
	 * @param position integer
	 * @throws Exception if position is negative.
	 */ 
	private void setHeight ( int height ) throws Exception {

		if ( height < 0 )
			throw new Exception( "height cannot be negative!" );

		this.height = height;		
	}
	/**
	 * An abstract method which returns type spesific key, when calling object is wanted to focus.
	 * @return Object
	 */ 
	public abstract Object focus ( );
		
	/**
	 * Getter which returns length of the building as an integer.
	 * @return returns length of the building as an integer.
	 */ 
	public int getLength ( ) {
		return this.length;
	}

	/**
	 * Getter which returns height of the building as an integer.
	 * @return returns height of the building as an integer.
	 */ 
	public int getHeight ( ) {
		return this.height;
	}

	/**
	 * Getter which returns position of the building as an integer.
	 * @return returns position of the building as an integer.
	 */ 
	public int getPosition ( ) {
		return this.position;
	}

	/**
	 * Overriden toString method to generate String representation of object.
	 * @return String
	 */ 
	@Override
	public String toString ( ) {
		return String.format( "%-3d %-3d %-3d", this.position, this.length, this.height );
	}

	/**
	 * Overriden equals method to check if object is equal to another object.
	 * @return boolean
	 */ 
    @Override
    public boolean equals ( Object other ) {
    	if ( other != null && other instanceof CityBuilding ) {

            final CityBuilding otherBuilding = ( CityBuilding ) other;   		
    		if ( otherBuilding.position == this.position 
    				&& otherBuilding.length == this.length 
    				&& otherBuilding.height == this.height ) {
    			return true;
    		}
    		return false;
    	}
    	return false;
    }	

    /**
     * Overriden clone method to make deep copy.
     * @return Object
     */ 
    @Override
    public Object clone ( ) {

    	try {

    		CityBuilding copy = ( CityBuilding ) super.clone();
    		return copy;
    	}	
    	catch ( Exception e ) {
    		return null;
    	}
    }

    /**
     * Returns unique number.
     * @return int
     */ 
    @Override
    public int hashCode() {
		int result = (this.height+1) * 31;
		result += this.length * 3;
		result += this.position * 7;

		return result;
	}
}