
package CityBuildingGTU;

/**
 * 
 * Market class represents a market which can be constructible an extends from CityBuilding class.
 * @version 1.0 28.02.2022
 * @author Burak Kocausta
 */ 
public class Market extends CityBuilding implements Cloneable {

	/**
	 * owner of market, closing and opening times of the markets are holded in private fields.
	 */ 
	private String owner;
	private String openingTime;
	private String closingTime;

	/**
	 * Initializes everything to its default value. owner is "none", openingTime is "08:00", 
	 * closingTime is "21:00". other fields which are implemented from CityBuilding class are
	 * also initialized its default values.
	 */ 
	public Market ( ) {
		super( );

		this.owner = new String( "none" );
		this.openingTime = new String( "08:00" );
		this.closingTime = new String( "21:00" );
	}

	/**
	 * Initializes everything to its default value except owner. openingTime is "08:00", 
	 * closingTime is "21:00". other fields which are implemented from CityBuilding class are
	 * also initialized its default values.
	 * @param owner Owner of the market as a String.
	 * @throws Exception If string is null.
	 */ 
	public Market ( String owner ) throws Exception {
		super ( );

		try {
			this.setOwner( owner );
			this.openingTime = new String( "08:00" );
			this.closingTime = new String( "21:00" );		
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * Initializes fields to given parameters.
	 * @param length Length of the market as an integer.
	 * @param height Height of the market as an integer.
	 * @param position Position of the market as an integer.
	 * @param owner Owner of the market as a String.  
	 * @param openingTime Opening Time of the market (Example Format: "08:00") as a string.
	 * @param closingTime Closing Time of the market (Example Format: "08:00") as a string.
	 * @throws Exception if time format is wrong, any of the strings are null or any of the integers are negative.
	 */ 
	public Market ( int position, int length, int height, 
			String owner, String openingTime, String closingTime ) throws Exception {
		super ( position, length, height );

		try {
			this.setOwner( owner );
			this.setOpeningTime( openingTime );
			this.setClosingTime( closingTime );
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * Sets the owner of the market.
	 * @param owner Owner of the market.
	 * @throws Exception if given String argument is null.
	 */ 
	public void setOwner ( String owner ) throws Exception {
		if ( owner == null ) 
			throw new Exception( "Uninitialized String cannot be used as owner!" );
		this.owner = owner;
	}

	/**
	 * Sets the opening time of the market.
	 * @param openingTime Opening Time of the market (Example Format: "08:00") as a string.
	 * @throws Exception if time format is wrong.
	 */ 
	public void setOpeningTime ( String openingTime ) throws Exception {
		if ( openingTime == null )
			throw new Exception( "Uninitialized String cannot be used!" );
		
		if ( checkTimeFormat( openingTime ) ) // check format here
			this.openingTime = openingTime;	
		else
			throw new Exception( "Time format is wrong! (Example String format: \"08:00\")" );
	}

	/**
	 * Sets the closing time of the market.
	 * @param closingTime Closing Time of the market (Example Format: "08:00") as a string.
	 * @throws Exception if time format is wrong.
	 */ 
	public void setClosingTime ( String closingTime ) throws Exception {
		if ( closingTime == null )
			throw new Exception( "Uninitialized String cannot be used!" );

		if ( checkTimeFormat( closingTime ) ) 
			this.closingTime = closingTime;
		else
			throw new Exception( "Time format is wrong! (Example String format: \"08:00\")" );
	}

	/**
	 * Helper function to check if given time format is proper.
	 */ 
	private boolean checkTimeFormat ( String time ) {
		if ( ( time.length() == 5 ) && ( time.charAt(2) == ':' ) ) {
			// check each character in given string

			char dig1;
			
			if ( ( time.charAt(0) >= '0' ) && ( time.charAt(0) <= '1' ) ) dig1 = '9';
			else if ( time.charAt(0) == '2' ) dig1 = '4';
			else return false;

			if ( ( time.charAt(1) >= '0' ) && ( time.charAt(1) <= dig1 )
					&& ( time.charAt(3) >= '0' ) && ( time.charAt(3) <= '5' )
					&& ( time.charAt(4) >= '0' ) && ( time.charAt(4) <= '9' ) ) {
				return true;
			}
			return false;			
		}		
		return false;
	}

	/**
	 * Getter to get owner.
	 * @return returns String.
	 */ 
	public String getOwner( ) {
		return this.owner;
	}

	/**
	 * Getter to get opening time.
	 * @return returns String.
	 */ 
	public String getOpeningTime ( ) {
		return this.openingTime;
	}

	/**
	 * Getter to get closing time.
	 * @return returns String.
	 */ 
	public String getClosingTime ( ) {
		return this.closingTime;
	}

	/**
	 * When focusing this classes objects, closing time will be returned.
	 * @return Object
	 */ 
	@Override
	public Object focus( ) {
		return this.closingTime;
	}

	/**
	 * Overriden toString method to generate String representation of object.
	 * @return String
	 */ 
	@Override
	public String toString ( ) {
		return String.format( "%-10s %-3d %-3d %-3d %-10s %-10s %-10s", "Market", this.getPosition()
						, this.getLength(), this.getHeight(), this.owner, this.openingTime, this.closingTime );
	}

	/**
	 * Overriden equals method to check if object is equal to another object.
	 * @return boolean
	 */ 
    @Override
    public boolean equals ( Object other ) {
    	if ( other != null && other instanceof Market ) {

            final Market otherMarket = ( Market ) other; 
    		if ( ( otherMarket.getPosition() == this.getPosition() )
    				&& ( otherMarket.getLength() == this.getLength() ) 
    				&& ( otherMarket.getHeight() == this.getHeight() )
    				&& ( otherMarket.owner.equals( this.owner ) )
    				&& ( otherMarket.closingTime.equals( this.closingTime ) ) 
    				&& ( otherMarket.openingTime.equals( this.openingTime ) ) ) {
    			return true;
    		}
    		return false;
    	}
    	return false;
    }

    /**
     * Returns unique number.
     * @return int
     */
    @Override
	public int hashCode() {
		int result = super.hashCode();
		result += owner.hashCode();
		result += openingTime.hashCode();
		result += closingTime.hashCode();

		return result;
	}

    /**
     * Overriden clone method to make deep copy.
     * @return Object
     */ 
    @Override
    public Object clone ( ) {

    	try {

    		Market copy = ( Market ) super.clone();
    		copy.closingTime = new String ( this.closingTime );
    		copy.openingTime = new String ( this.openingTime );
    		copy.owner = new String ( this.owner );
    		return copy;
    	}	
    	catch ( Exception e ) {
    		return null;
    	}
    }    	
}