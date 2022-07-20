
package CityBuildingGTU;

/**
 * 
 * House class represents a house which can be constructible and extends CityBuilding class.
 * @version 1.0 28.02.2022
 * @author Burak Kocausta
 */ 
public class House extends CityBuilding implements Cloneable {

	/**
	 * number of room, color of the house and owner of the house are holded in private fields.
	 */ 
	private int numOfRoom;
	private String color;
	private String owner;

	/**
	 * No parameter constructor sets color to white and owner to none.
	 */ 
	public House ( ) {
		super ( );
		this.numOfRoom = 1;
		this.color = new String( "white" );
		this.owner = new String( "none" );
	}

	/**
	 * Constructor takes all the parameters.
	 * @param length Length of the house as an integer.
	 * @param height Height of the house as an integer.
	 * @param position Position of the house as an integer.
	 * @param numOfRoom Number of room in the house as an integer.
	 * @param color Color of the house as a String.
	 * @param owner Owner of the house as a String.
	 * @throws Exception If at least one of the parameter is negative, throws exception.
	 */ 
	public House ( int position, int length, int height, 
			String owner, String color, int numOfRoom ) throws Exception {
		super ( position, length, height );

		try {
			setNumOfRoom( numOfRoom );
			setColor( color );
			setOwner( owner );
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * Just sizes are taken, could be used for complexity testing.
	 * @param length Length of the house as an integer.
	 * @param height Height of the house as an integer.
	 * @param position Position of the house as an integer.
	 * @throws Exception If at least one of the parameter is negative, throws exception.
	 */ 
	public House ( int position, int length, int height ) throws Exception {
		super ( position, length, height );

		try {
			setNumOfRoom( 3 );
			setColor( "black" );
			setOwner( "none" );
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * Setter to set number of room.
	 * @param numOfRoom Number of room in the house.
	 * @throws Exception if numOfRoom is negative throws Exception.
	 */ 
	public void setNumOfRoom ( int numOfRoom ) throws Exception {
		if ( numOfRoom >= 0 )
			this.numOfRoom = numOfRoom;
		else
			throw new Exception( "Number of room cannot be negative!" );
	}

	/**
	 * Setter to set owner of the house.
	 * @param owner Owner of the house as a String.
	 * @throws Exception if null parameter is passed, throws exception.
	 */ 
	public void setOwner ( String owner ) throws Exception {
		if ( owner == null ) 
			throw new Exception( "Uninitialized String cannot be used as owner!" );
		this.owner = owner;
	}

	/**
	 * Setter to set Color of the house.
	 * @param color Color of the house as a String.
	 * @throws Exception if null parameter is passed, throws exception.
	 */ 
	public void setColor ( String color ) throws Exception {
		if ( color != null )
			this.color = color;
		else
			throw new Exception( "Uninitialized String cannot be used as color!" );
	}

	/**
	 * Getter to get number of the room.
	 * @return returns integer.
	 */ 
	public int getNumOfRoom ( ) {
		return this.numOfRoom;
	}

	/**
	 * Getter to get Owner.
	 * @return returns String.
	 */ 
	public String getOwner ( ) {
		return this.owner;
	}

	/**
	 * Getter to get color.
	 * @return returns String.
	 */ 
	public String getColor ( ) {
		return this.color;
	}
	/**
	 * When focusing this classes objects, owner will be returned.
	 * @return Object
	 */ 
	@Override
	public Object focus( ) {
		return this.owner;
	}

	/**
	 * Overriden toString method to generate String representation of object.
	 * @return String
	 */ 
	public String toString ( ) {
		return String.format( "%-10s %-3d %-3d %-3d %-10s %-10s %-10d","House",
						this.getPosition(), this.getLength(), this.getHeight() ,
						this.owner, this.color, this.numOfRoom );
	}

	/**
	 * Overriden equals method to check if object is equal to another object.
	 * @return boolean
	 */ 
    @Override
    public boolean equals ( Object other ) {
    	if ( other != null && other instanceof House ) {

            final House otherHouse = ( House ) other; 
    		if ( ( otherHouse.getPosition() == this.getPosition() )
    				&& ( otherHouse.getLength() == this.getLength() ) 
    				&& ( otherHouse.getHeight() == this.getHeight() )
    				&& ( otherHouse.owner.equals( this.owner ) )
    				&& ( otherHouse.color.equals( this.color ) ) 
    				&& ( otherHouse.numOfRoom == this.numOfRoom ) ) {
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

    		House copy = ( House ) super.clone();
    		copy.owner = new String ( this.owner );
    		copy.color = new String ( this.color );
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

		int result = super.hashCode();
		result += this.numOfRoom;
		result += this.owner.hashCode();
		result += this.color.hashCode();

		return result;
	}
}