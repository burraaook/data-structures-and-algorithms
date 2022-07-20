
package CityBuildingGTU;

/**
 * 
 * Office class represents an office which can be constructible an extends from CityBuilding class.
 * @version 1.0 1.03.2022
 * @author Burak Kocausta
 */ 
public class Office extends CityBuilding implements Cloneable{

	/**
	 *	Owner of the office and job type of the office provides are holded in private fields.
	 */  
	String owner;
	String jobType;

	/**
	 * Initializes the fields to their default values. 
	 * owner and job types are initizalized to "none".
	 */ 
	public Office ( ) {
		super( );

		this.owner = new String( "none" );
		this.jobType = new String( "none" );
	}

	/**
	 * @param length Length of the office as an integer.
	 * @param height Height of the office as an integer.
	 * @param position Position of the office as an integer.
	 * @param owner Owner of the office as a String.
	 * @param jobType Job Type of the office as a String.
	 * @throws Exception if any of the integers are negative or any of the given strings are null.
	 */ 
	public Office ( int position, int length, int height, 
			String owner, String jobType ) throws Exception {
		super( position, length, height );

		try {
			this.setOwner( owner );
			this.setJobType( jobType );
		}
		catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * Sets the owner of the office.
	 * @param owner Owner of the office.
	 * @throws Exception if given String argument is null.
	 */ 
	public void setOwner ( String owner ) throws Exception {
		if ( owner == null ) 
			throw new Exception( "Uninitialized String cannot be used as owner!" );
		this.owner = owner;
	}

	/**
	 * Sets the jobType of the office.
	 * @param jobType Job Type of the office.
	 * @throws Exception if given String argument is null.
	 */ 
	public void setJobType ( String jobType ) throws Exception {
		if ( jobType == null ) 
			throw new Exception( "Uninitialized String cannot be used as jobType!" );
		this.jobType = jobType;
	}

	/**
	 * Getter to get owner.
	 * @return returns String.
	 */ 
	public String getOwner ( ) {
		return this.owner;
	}

	/**
	 * Getter to get job type.
	 * @return returns String.
	 */ 
	public String getJobType ( ) {
		return this.jobType;
	}

	/**
	 * When focusing this classes objects, job type will be returned.
	 * @return Object
	 */ 
	@Override
	public Object focus( ) {
		return this.jobType;
	}

	/**
	 * Overriden toString method to generate String representation of object.
	 * @return String
	 */ 
	public String toString ( ) {
		return String.format( "%-10s %-3d %-3d %-3d %-10s %-10s %-10s", "Office", this.getPosition()
						, this.getLength(), this.getHeight(), this.owner, this.jobType, " " );
	}

	/**
	 * Overriden equals method to check if object is equal to another object.
	 * @return boolean
	 */ 
    @Override
    public boolean equals ( Object other ) {
    	if ( other != null && other instanceof Office ) {

            final Office otherOffice = ( Office ) other; 
    		if ( ( otherOffice.getPosition() == this.getPosition() )
    				&& ( otherOffice.getLength() == this.getLength() ) 
    				&& ( otherOffice.getHeight() == this.getHeight() )
    				&& ( otherOffice.owner.equals( this.owner ) )
					&& ( otherOffice.jobType.equals( this.jobType ) ) ) {
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

    		Office copy = ( Office ) super.clone();
    		copy.owner = new String ( this.owner );
    		copy.jobType = new String ( this.jobType );
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
		result += owner.hashCode();
		result += jobType.hashCode();

		return result;
	}
}