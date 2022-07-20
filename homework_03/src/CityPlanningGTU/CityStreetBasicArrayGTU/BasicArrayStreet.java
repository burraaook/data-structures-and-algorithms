package CityStreetBasicArrayGTU;
import CityBuildingGTU.*;
import java.util.Scanner;

/**
 * 
 * BasicArrayStreet class provides a buildable two dimensional, and two sided space for CityBuilding type buildings.
 * @version 1.1 18.03.2022
 * @author Burak Kocausta
 * 
 */
public class BasicArrayStreet implements Cloneable {

    /** 
     *  CityBuilding arrays to store lands.  
     */
    private CityBuilding[] frontBuildings;
    private CityBuilding[] backBuildings;

    /**
     *  Length of street and numbers of buildings in both sides stored. 
     */
    private int lengthOfStreet;
    private int numOfBuildingFront;
    private int numOfBuildingBack;

    /**
     * Array capacity is stored to increase when its needed.
     */ 
    private int arrCapacity;

    /**
     * Number of land left can be usable is holded as an integer.
     */ 
    private int numOfLandLeft;
    
    /**
     *  No parameter constructor sets every field to its default value.
     */ 
    public BasicArrayStreet ( ) {
        this.lengthOfStreet = 55;
        this.arrCapacity = 20;
        this.frontBuildings = new CityBuilding[ this.arrCapacity ];
        this.backBuildings = new CityBuilding[ this.arrCapacity ];
        this.numOfBuildingFront = 0;
        this.numOfBuildingBack = 0;
        this.numOfLandLeft = 56*2;
    }

    /**
     * Constructor takes only lengthOfStreet of the street and sets the value according to the parameter.
     * @param lengthOfStreet total length of the street.
     * @throws Exception if lengthOfStreet is less than 10.
     */ 
    public BasicArrayStreet ( int lengthOfStreet ) throws Exception {
        
        try {
            this.setLengthOfStreet( lengthOfStreet );
            this.numOfBuildingFront = 0;
            this.numOfBuildingBack = 0;
            this.arrCapacity = 20;
            this.numOfLandLeft = (lengthOfStreet + 1)*2;
            this.frontBuildings = new CityBuilding[ this.arrCapacity ];
            this.backBuildings = new CityBuilding[ this.arrCapacity ];   
        }
        catch ( Exception e ) {
            throw e;
        }
    }

    /**
     * A private setter which sets length of the street.
     * @param lengthOfStreet integer
     * @throws Exception if lengthOfStreet is less than 10. 
     */ 
    private void setLengthOfStreet ( int lengthOfStreet ) throws Exception {

        if ( lengthOfStreet < 10 )
            throw new Exception ( "Length of street cannot be less than 10!" );
        
        this.lengthOfStreet = lengthOfStreet;
    }

    /**
     * Getter for getting lengthOfStreet of the street.
     * @return Length of the street will be returned as an integer.
     */ 
    public int getLengthOfStreet ( ) {
        return this.lengthOfStreet;
    }
    /**
     * Getter for getting total number of buildings of the CityBuildings array.
     * @return number of building in the array will be returned as an integer.
     */ 
    public int getNumOfBuilding ( ) {
        return ( this.numOfBuildingFront + this.numOfBuildingBack );
    }

    /**
     * Getter for getting number of building at front of the street.
     * @return number of building in the frontBuildings array is returned.
     */     
    public int getNumOfBuildingFront ( ) {
        return this.numOfBuildingFront;
    }

    /**
     * Getter for getting number of building at back of the street.
     * @return number of building in the backBuildings array is returned.
     */   
    public int getNumOfBuildingBack ( ) {
        return this.numOfBuildingBack;
    }

    /**
     * Accessor for getting the building at frontBuildings array.
     * @param index index of array.
     * @return building at located index is returned.
     * @throws Exception if index is out of bounds.
     */  
    public CityBuilding atFront ( int index ) throws Exception {
        if ( index < 0 || index >= this.numOfBuildingFront ) 
            throw new Exception( "Array out of bounds exception." );
        
        return this.frontBuildings[ index ];
    }

    /**
     * Accessor for getting the building at backBuildings array.
     * @param index index of array.
     * @return building at located index is returned.
     * @throws Exception if index is out of bounds.
     */  
    public CityBuilding atBack ( int index ) throws Exception {

        if ( index < 0 || index >= this.numOfBuildingBack ) 
            throw new Exception( "Array out of bounds exception." );            
        
        return this.backBuildings[ index ];
    }

    /**
     * Adds CityBuilding to front side of the street.
     * @param building CityBuilding type parameter.
     * @throws Exception if street length is exceeded or that place is already occuppied.
     */ 
    public void addFront ( CityBuilding building ) throws Exception {
        if ( building == null ) return;

        int bPos = building.getPosition();
        int bLen = building.getLength();
        int bHeight = building.getHeight();

        if ( bPos + bLen > this.lengthOfStreet )
            throw new Exception ( "Street length exceeded! Building cannot be added.\n" );
        
        // check if same building reference is already exist.            
        for ( int i = 0; i < this.numOfBuildingBack; i++ ) 
            if ( building == this.backBuildings[i] )
                throw new Exception ( "Same CityBuilding object is already added to street!\n" );

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) { 
            // check if building can be added
                
            int tempPos = this.frontBuildings[i].getPosition();
            int tempEndPos = tempPos + this.frontBuildings[i].getLength();

            for ( int j = bPos; j < bPos+bLen; ++j )
                if ( j >= tempPos && j <= tempEndPos )
                    throw new Exception( "Place is occupied! Building cannot be added.\n" );                 
        }
            // if building can be added proceed.
            // check if array is full, if it is increment capacity.            
        if ( this.numOfBuildingFront >= this.arrCapacity ) {          
            int tempCapacity = this.arrCapacity * 2;
            CityBuilding[] tempFront = new CityBuilding[ tempCapacity ];
            CityBuilding[] tempBack = new CityBuilding[ tempCapacity ];

            for ( int i = 0; i < this.arrCapacity; ++i ){
                tempFront[i] = this.frontBuildings[i];
                tempBack[i] = this.backBuildings[i];
            }

            this.arrCapacity = tempCapacity;
            this.frontBuildings = tempFront;
            this.backBuildings = tempBack;

        }
        frontBuildings[ this.numOfBuildingFront ] = building;
        this.numOfBuildingFront += 1;
        this.numOfLandLeft -= bLen; 
    }
   
    /**
     * Adds CityBuilding to back side of the street.
     * @param building CityBuilding type parameter.
     * @throws Exception if street length is exceeded, place is already occuppied, or same reference is in the street.
     */ 
    public void addBack ( CityBuilding building ) throws Exception {
        if ( building == null ) return;

        int bPos = building.getPosition();
        int bLen = building.getLength();

        if ( bPos + bLen > this.lengthOfStreet )
            throw new Exception ( "Street length exceeded! Building cannot be added.\n" );

        for ( int i = 0; i < this.numOfBuildingFront; ++i )
            if ( building == this.frontBuildings[i] )
                throw new Exception ( "Same CityBuilding object is already added to street!\n" );

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            // check if building can be added

            int tempPos = this.backBuildings[i].getPosition();
            int tempEndPos = tempPos + this.backBuildings[i].getLength();

            for ( int j = bPos; j < bPos+bLen; ++j )
                if ( j >= tempPos && j <= tempEndPos )
                    throw new Exception ("Place is occupied! Building cannot be added.\n");                  
        }
            // if building can be added proceed.            
            // check if array is full, if it is increment capacity. 
        if ( this.numOfBuildingBack >= this.arrCapacity ) {
            int tempCapacity = this.arrCapacity * 2;
            CityBuilding[] tempFront = new CityBuilding[ tempCapacity ];
            CityBuilding[] tempBack = new CityBuilding[ tempCapacity ];

            for ( int i = 0; i < this.arrCapacity; ++i ){
                tempFront[i] = this.frontBuildings[i];
                tempBack[i] = this.backBuildings[i];
            }

            this.arrCapacity = tempCapacity;
            this.frontBuildings = tempFront;
            this.backBuildings = tempBack;
        }
        backBuildings[ this.numOfBuildingBack ] = building;
        this.numOfBuildingBack += 1;
        this.numOfLandLeft -= bLen;                 
    }

    /**
     * Deletes building from front side of the street according to the given position.
     * @param position Position of the building as an int.
     */ 
    public void deleteFront ( int position ) {

        int lengthOfDeleted = 0;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
            if ( this.frontBuildings[i].getPosition() == position ) {
                lengthOfDeleted = this.frontBuildings[i].getLength();
                for ( int j = i; (j + 1) < this.numOfBuildingFront; ++j ) {
                    CityBuilding temp = this.frontBuildings[j];
                    this.frontBuildings[j] = this.frontBuildings[j+1];
                    this.frontBuildings[j+1] = temp;
                }
                this.numOfBuildingFront -= 1;
                this.numOfLandLeft += lengthOfDeleted;
                break;
            }
        }
    }

    /**
     * Deletes building from front side of the street according to the given reference.
     * @param building CityBuilding reference.
     */ 
    public void deleteFront ( CityBuilding building ) {

        int lengthOfDeleted = 0;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
            if ( this.frontBuildings[i].equals( building ) ) {
                lengthOfDeleted = this.frontBuildings[i].getLength();
                for ( int j = i; (j + 1) < this.numOfBuildingFront; ++j ) {
                    CityBuilding temp = this.frontBuildings[j];
                    this.frontBuildings[j] = this.frontBuildings[j+1];
                    this.frontBuildings[j+1] = temp;
                }
                this.numOfBuildingFront -= 1;
                this.numOfLandLeft += lengthOfDeleted;
                break;
            }
        }
    }    

    /**
     * Deletes building from back side of the street according to the given position.
     * @param position Position of the building as an int.
     */ 
    public void deleteBack ( int position ) {

        int lengthOfDeleted = 0;
        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            if ( this.backBuildings[i].getPosition() == position ) {

                lengthOfDeleted = this.backBuildings[i].getLength();
                for ( int j = i; (j + 1) < this.numOfBuildingBack; ++j ) {
                    CityBuilding temp = this.backBuildings[j];
                    this.backBuildings[j] = this.backBuildings[j+1];
                    this.backBuildings[j+1] = temp;
                }
                this.numOfBuildingBack -= 1;
                this.numOfLandLeft += lengthOfDeleted;
                break;
            }
        }
    }

    /**
     * Deletes building from back side of the street according to the given reference.
     * @param building CityBuilding reference.
     */
    public void deleteBack ( CityBuilding building ) {

        int lengthOfDeleted = 0;
        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            if ( this.backBuildings[i].equals( building ) ) {

                lengthOfDeleted = this.backBuildings[i].getLength();
                for ( int j = i; (j + 1) < this.numOfBuildingBack; ++j ) {
                    CityBuilding temp = this.backBuildings[j];
                    this.backBuildings[j] = this.backBuildings[j+1];
                    this.backBuildings[j+1] = temp;
                }
                this.numOfBuildingBack -= 1;
                this.numOfLandLeft += lengthOfDeleted;
                break;
            }
        }
    }    

    /**
     * Getter to get total remaining land.
     * @return integer
     */ 
    public int totalRemainingLand ( ) {
        return this.numOfLandLeft;
    }

    /**
     * Calculates and prints ratio of playground's total length according to the street.
     */ 
    public void ratioOfPlaygrounds ( ) {

        int count = 0;
        int totalLength = 0;
        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {

            if ( this.frontBuildings[i] instanceof Playground ) {
                count++;
                totalLength += this.frontBuildings[i].getLength();
            }
        }

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {

            if ( this.backBuildings[i] instanceof Playground ) {
                count++;
                totalLength += this.backBuildings[i].getLength();
            }
        }


        float ratio = ( ( float ) totalLength / this.lengthOfStreet );
        ratio *= 100;

        if ( count == 0 ) {
            System.out.printf( "\nThere aren't any playground.\n" );
        }
        else
            System.out.printf ( "\nNumber of playgrounds = %d\nRatio of playgrounds = %s %.3f\n",count, "%", ratio );        
    }

    /**
     * Calculates total length of houses in the street and returns.
     * @return integer
     */ 
    public int lengthOfHouses ( ) {

        int houseLength = 0;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
            if ( this.frontBuildings[i] instanceof House )
                houseLength += this.frontBuildings[i].getLength();
        }

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            if ( this.backBuildings[i] instanceof House )
                houseLength += this.backBuildings[i].getLength();
        }

        return houseLength;

    }

    /**
     * Calculates the total length of markets in the street and returns.
     * @return integer
     */ 
    public int lengthOfMarkets ( ) {
        int marketLength = 0;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) 
            if ( this.frontBuildings[i] instanceof Market )
                marketLength += this.frontBuildings[i].getLength();
        
        for ( int i = 0; i < this.numOfBuildingBack; ++i ) 
            if ( this.backBuildings[i] instanceof Market )
                marketLength += this.backBuildings[i].getLength();    

        return marketLength;
    }

    /**
     * Calculates the total length of offices in the street and returns.
     * @return integer
     */ 
    public int lengthOfOffices ( ) {
        int officeLength = 0;
        for ( int i = 0; i < this.numOfBuildingFront; ++i ) 
            if ( this.frontBuildings[i] instanceof Office )
                officeLength += this.frontBuildings[i].getLength();
        

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) 
            if ( this.backBuildings[i] instanceof Office )
                officeLength += this.backBuildings[i].getLength();
        return officeLength;
    }

    /**
     * Overriden toString method returns information about BasicArrayStreet object as a String.
     * @return It returns String.
     */ 
    @Override
    public String toString ( ) {
        
        String street = new String ( "\n__Front Side Buildings__\n\n" );

        int totalNum = 0;
        if ( this.numOfBuildingFront == 0 ) street += String.format( "There aren't any building! Please add buildings to front side.\n" );
        else {
            for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
                street += String.format( "%d-  %s\n", i+1, this.frontBuildings[i] );
                totalNum = i; totalNum++;
            }
        }

        street += String.format( "\n__Back Side Buildings__\n\n" );
        if ( this.numOfBuildingBack == 0 ) street += String.format( "There aren't any building! Please add buildings to back side.\n" );
        else {
            for ( int i = 0; i < this.numOfBuildingBack; ++i ) 
                street += String.format( "%d-  %s\n", i + totalNum + 1, this.backBuildings[i] );   
        }

        return street;
    }

    /**
     * Overriden equals() method to compare the objects.
     * @param other Object class object is taken as parameter.
     * @return If objects are equal returns true, otherwise false.
     */
    @Override
    public boolean equals ( Object other ) {
        if ( other != null && other instanceof BasicArrayStreet ) {

            final BasicArrayStreet otherStreet = ( BasicArrayStreet ) other;
            if ( ( otherStreet.numOfBuildingFront == this.numOfBuildingFront )
                    && ( otherStreet.numOfBuildingBack == this.numOfBuildingBack ) 
                    && ( otherStreet.lengthOfStreet == this.lengthOfStreet ) 
                    && ( otherStreet.numOfLandLeft == this.numOfLandLeft ) ) {

                for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
                    boolean flag = false;
                    for ( int j = 0; j < otherStreet.numOfBuildingFront; ++j ) {
                        if ( this.frontBuildings[i].equals( otherStreet.frontBuildings[j] ) ) {
                            flag = true;
                            break;
                        }
                    }
                    if ( !flag ) return false;
                }
                
                for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
                    boolean flag = false;
                    for ( int j = 0; j < otherStreet.numOfBuildingBack; ++j ){
                        if ( this.backBuildings[i].equals( otherStreet.backBuildings[j] ) ){
                            flag = true;
                            break;
                        }
                    }
                    if ( !flag ) return false;
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Overriden clone() method to make a deep copy of the object.
     * @return returns the copied object as an Object.
     */ 
    @Override
    public Object clone ( ) {

        try {
            BasicArrayStreet copy = ( BasicArrayStreet ) super.clone();
            copy.frontBuildings = new CityBuilding[ copy.arrCapacity ];
            copy.backBuildings = new CityBuilding[ copy.arrCapacity ];

            for ( int i = 0; i < copy.numOfBuildingFront; ++i )
                copy.frontBuildings[i] = ( CityBuilding )this.frontBuildings[i].clone();
            
            for ( int i = 0; i < copy.numOfBuildingBack; ++i )
                copy.backBuildings[i] = ( CityBuilding )this.backBuildings[i].clone();
            
            return copy;
        }

        catch ( Exception e ) {
            return null;
        }
    } 

    /**
     * Overriden hashCode() method to return hash code number for this object.
     * @return integer
     */ 
    @Override
    public int hashCode() {
        int result = this.lengthOfStreet * 7;
        result += this.numOfBuildingFront * 3;
        result += this.numOfBuildingBack * 5;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) 
            result += this.frontBuildings[i].hashCode();

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) 
            result += this.backBuildings[i].hashCode();

        return result;
    } 

    /**
     * A simple menu for editing the street. Like adding and deleting buildings.
     */ 
    public void editMode ( ) {
        boolean flag = true;
        String userInput;
        Scanner scanInput = new Scanner( System.in );
        while ( flag ) {
            
            System.out.printf( "\n___Street Editing Mode___\n\n%s\n\n", this );
            
            System.out.printf( "1- Add Building\n2- Delete Building\n3- Exit from Editing Mode\nInput = " );
            userInput = scanInput.nextLine( );
            
            if( userInput.length() >= 1 ) {

                if ( userInput.equals( "1" ) ) {
                    
                    System.out.printf( "\nWhich side you want to add building?\n1- Front Side\n2- Back Side\nInput = " );
                    userInput = scanInput.nextLine( );
                    int sideKey = 1;
                    if (  userInput.equals( "1" ) || userInput.equals( "2" ) ) {
                        
                        sideKey = Integer.parseInt( userInput );

                        while ( true ) {
                            System.out.printf( "\nWhat kind of a building you want to add?\n" );
                            System.out.printf( "1- House\n2- Market\n3- Office\n4- Playground\n5- Exit\nInput = " );
                            userInput = scanInput.nextLine( ); 
                            
                            int position = 0, bLength = 5, height = 1;

                            if ( userInput.equals( "1" ) || userInput.equals( "2" )
                                    || userInput.equals( "3" ) || userInput.equals( "4" ) ) {
                                
                                try {
                                    System.out.printf( "\nPlease enter the position of the building(integer) = " );
                                    while ( !scanInput.hasNextInt( ) ) {
                                      System.out.printf( "Please enter an integer!\nposition = " );
                                      scanInput.nextLine();
                                    } 
                                    position = Integer.parseInt( scanInput.nextLine() );   

                                    System.out.printf( "\nPlease enter the length of the building(integer) = ");
                                    while ( !scanInput.hasNextInt() ) {
                                      System.out.printf( "Please enter an integer!\nlength = " );
                                      scanInput.nextLine();
                                    } 
                                    bLength = Integer.parseInt( scanInput.nextLine() );

                                    if ( userInput.equals( "4" ) ) height = 0;
                                    
                                    else {

                                        System.out.printf( "\nPlease enter the height of the building(integer) = ");
                                        while ( !scanInput.hasNextInt() ) {
                                          System.out.printf( "Please enter an integer!\nheight = " );
                                          scanInput.nextLine();
                                        } 
                                        height = Integer.parseInt( scanInput.nextLine() );                                    
                                    }
                                }
                                catch( Exception e ) {
                                    System.out.println( "\nInvalid Input!" );
                                    scanInput.nextLine( );
                                    break;
                                }

                            }

                            if ( userInput.equals( "1" )) { 
                            
                                try {                              
                                
                                    System.out.printf( "\nPlease enter the owner of the house = " );
                                    String owner = scanInput.nextLine( );
                                    System.out.printf( "\nPlease enter the color of the house = " );
                                    String color = scanInput.nextLine( );
                                    System.out.printf( "\nPlease enter the total number of room of the house(integer) = ");
                                    
                                    while ( !scanInput.hasNextInt() ) {
                                      System.out.printf( "Please enter an integer!\nNumber of the room = " );
                                      scanInput.nextLine();
                                    } 
                                    int numOfRoom = Integer.parseInt( scanInput.nextLine() );

                                    
                                    House house = new House( position, bLength, height, owner, color, numOfRoom );

                                    if ( sideKey == 1 ) this.addFront( house );
                                    else if ( sideKey == 2 ) this.addBack( house );
                                }
                                catch ( Exception e ) {
                                    System.out.println( "\n" + e );
                                }
                                break;
                            }

                            else if ( userInput.equals( "2" ) ) {
                                try {
                                    System.out.printf( "\nPlease enter the owner of the market = " );
                                    String owner = scanInput.nextLine( );
                                    System.out.printf( "\nPlease enter the opening time of the market(Ex: 08:00) = " );
                                    String openingTime = scanInput.nextLine( );
                                    System.out.printf( "\nPlease enter the closing time of the market(Ex: 18:00) = " );
                                    String closingTime = scanInput.nextLine( );
                            
                                    
                                    Market market = new Market( position, bLength, height, owner, openingTime, closingTime );
                                    if ( sideKey == 1 ) this.addFront( market );
                                    else if ( sideKey == 2 ) this.addBack( market );                                    
                                }
                                catch ( Exception e ) {
                                    System.out.println( "\n" + e );
                                }
                                break;
                            }

                            else if ( userInput.equals( "3" ) ) {
                                try {
                                    System.out.printf( "\nPlease enter the owner of the office = " );
                                    String owner = scanInput.nextLine( );
                                    System.out.printf( "\nPlease enter the job type of the office = " );
                                    String jobType = scanInput.nextLine( );
                        
                                
                                    Office office = new Office( position, bLength, height, owner, jobType );
                                    if ( sideKey == 1 ) this.addFront( office );
                                    else if ( sideKey == 2 ) this.addBack( office );                                    
                                }
                                catch ( Exception e ) {
                                    System.out.println( e );
                                }
                                break;
                            }

                            else if ( userInput.equals( "4" ) ) {
                                try {
                                    Playground playground = new Playground( position, bLength );
                                    if ( sideKey == 1 ) this.addFront( playground );
                                    else if ( sideKey == 2 ) this.addBack( playground );                                    
                                }
                                catch ( Exception e ) {
                                    System.out.println( "\n" + e );
                                }
                                break;
                            }

                            else if ( userInput.equals( "5" ) ) break;
                            
                            else
                                System.out.printf( "\nWrong input is entered!\n" );
                        }
                    }

                    else
                        System.out.println( "\nWrong input! You can enter 1 or 2\n" );
                }

                else if ( userInput.equals( "2" ) ) {
                    try {

                        if ( this.numOfBuildingFront + this.numOfBuildingBack == 0 ) {
                            System.out.printf( "\nThere aren't any building to delete!" );
                            continue;
                        }
                        int bIndex;
                        System.out.println( this );
                        System.out.printf( "Which building you want to delete(enter building number) = " );
                        while ( !scanInput.hasNextInt( ) ) {
                          System.out.printf( "Please enter the given number left side.\nbuilding number = " );
                          scanInput.nextLine();
                        } 
                        bIndex = Integer.parseInt( scanInput.nextLine() );

                        if ( bIndex > ( this.numOfBuildingFront + this.numOfBuildingBack ) 
                                || bIndex < 1 ) {
                            System.out.printf( "\nEnter the numbers between 1 and %d", this.numOfBuildingFront + this.numOfBuildingBack );
                            continue;
                        }

                        else if ( bIndex <= this.numOfBuildingFront ) 
                            this.deleteFront( this.frontBuildings[ bIndex - 1 ].getPosition() );    

                        else 
                            this.deleteBack( this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ].getPosition() );
                    }
                    
                    catch ( Exception e ) {
                        System.out.println( "\n" + e );
                    }                          
                }

                else if ( userInput.equals( "3" ) )
                    flag = false;

                else
                    System.out.println( "\nWrong Input! Please enter one of the given numbers." );
            }
        }
    }


    /**
     * Simple menu for viewing the streets spesific properties like Skyline silhoullette etc..
     */ 
    public void viewMode ( ) {
        boolean flag = true;
        String userInput;
        Scanner scanInput = new Scanner( System.in );
        while ( flag ) {
            
            System.out.println( "\n___Street Viewing Mode___" );
            System.out.printf( "1- Display the total remaining length of lands on the street\n" );
            System.out.printf( "2- Display the list of buildings on the street\n" );
            System.out.printf( "3- Display the number and ratio of length of playgrounds in the street.\n" );
            System.out.printf( "4- Calculate the total length of street occupied by the markets, houses or offices.\n" );
            System.out.printf( "5- Display the skyline silhouette of the street\n" );
            System.out.printf( "6- Focus on a spesific building( test polimorphism )\n7- Exit from Viewing Mode\nInput = " );

            userInput = scanInput.nextLine( );
           
            if( userInput.length() >= 1 ) {

                if ( userInput.equals( "1" ) ) 
                    System.out.printf( "\nTotal remaining Lands = %d\n", this.numOfLandLeft );
                

                else if ( userInput.equals( "2" ) ) 
                    System.out.println( this );
                

                else if ( userInput.equals( "3" ) ) {
                    this.ratioOfPlaygrounds(); 

                }

                else if ( userInput.equals( "4" ) ) {
                    
                    System.out.printf("\nTotal length of Market(s) = %d\nTotal length of House(s) = %d\nTotal length of Office(s) = %d\n"
                                , this.lengthOfMarkets(), this.lengthOfHouses(), this.lengthOfOffices() );       
                }

                else if ( userInput.equals( "5" ) )                    
                    this.printSkyline();
                
                else if ( userInput.equals( "6" ) ) {
                    
                    while ( true ) {

                        try {
                            if ( (this.numOfBuildingFront + this.numOfBuildingBack) == 0 ) {
                                System.out.printf( "\nThere aren't any building currently! Please add buildings.\n" );
                                break;
                            }

                            System.out.println( this );

                            int bIndex = 1;
                            System.out.printf( "Which building do you want to focus?\nInput = " );
                            while ( !scanInput.hasNextInt( ) ) {
                                System.out.printf( "Please enter the given number left side.\nbuilding number = " );
                                scanInput.nextLine();
                            } 
                            bIndex = Integer.parseInt( scanInput.nextLine() );

                            if ( bIndex > ( this.numOfBuildingFront + this.numOfBuildingBack ) 
                                    || bIndex < 1 ) {
                                System.out.printf( "Enter the numbers between 1 and %d", this.numOfBuildingFront + this.numOfBuildingBack );
                                continue;
                            }
                            
                            else if ( bIndex <= this.numOfBuildingFront ) {
                                Object tool = this.frontBuildings[bIndex - 1].focus();
                                System.out.printf("\nfocus() function returned = ");
                                System.out.println(tool + "\n");

                                if ( this.frontBuildings[ bIndex - 1 ] instanceof House ) {
                                    House house = ( House ) this.frontBuildings[ bIndex - 1 ];
                                    System.out.printf( "\nType = House\nOwner = %s\nColor = %s\nNumber Of Room = %s\n"
                                                , house.getOwner(), house.getColor(), house.getNumOfRoom() );

                                }

                                else if ( this.frontBuildings[ bIndex - 1 ] instanceof Market ) {
                                    Market market = ( Market ) this.frontBuildings[ bIndex - 1 ];
                                    System.out.printf( "\nType = Market\nClosing Time = %s\nOpening Time = %s\nOwner = %s\n"
                                                , market.getClosingTime(), market.getOpeningTime(), market.getOwner() );
                                }

                                else if ( this.frontBuildings[ bIndex - 1 ] instanceof Office ) {
                                    Office office = ( Office ) this.frontBuildings[ bIndex - 1 ];
                                    System.out.printf( "\nType = Office\nJob Type = %s\nOwner = %s\n"
                                                , office.getJobType(), office.getOwner() );                          
                                }

                                else if ( this.frontBuildings[ bIndex - 1 ] instanceof Playground ) {
                                    Playground playground = ( Playground ) this.frontBuildings[ bIndex - 1 ];
                                    System.out.printf("\nType = Playground\n");
                                }

                                System.out.printf( "Position = %d\nLength = %d\nHeight = %d\n"
                                            , this.frontBuildings[ bIndex - 1 ].getPosition()
                                            , this.frontBuildings[ bIndex - 1 ].getLength()
                                            , this.frontBuildings[ bIndex - 1 ].getHeight() );                            
                                break;
                            }

                            else {

                                Object tool = this.backBuildings[bIndex - this.numOfBuildingFront - 1].focus();
                                System.out.printf("\nfocus() function returned = ");
                                System.out.println(tool + "\n");

                                if ( this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ] instanceof House ) {
                                    House house = ( House ) this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ];
                                    System.out.printf( "\nType = House\nOwner = %s\nColor = %s\nNumber Of Room = %s\n"
                                                , house.getOwner(), house.getColor(), house.getNumOfRoom() );

                                }

                                else if ( this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ] instanceof Market ) {
                                    Market market = ( Market ) this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ];
                                    System.out.printf( "\nType = Market\nClosing Time = %s\nOpening Time = %s\nOwner = %s\n"
                                                , market.getClosingTime(), market.getOpeningTime(), market.getOwner() );
                                }

                                else if ( this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ] instanceof Office ) {
                                    Office office = ( Office ) this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ];
                                    System.out.printf( "\nType = Office\nJob Type = %s\nOwner = %s\n"
                                                , office.getJobType(), office.getOwner() );                          
                                }

                                else if ( this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ] instanceof Playground ) {
                                    Playground playground = ( Playground ) this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ];
                                    System.out.printf("\nType = Playground\n");
                                }

                                System.out.printf( "Position = %d\nLength = %d\nHeight = %d\n"
                                            , this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ].getPosition()
                                            , this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ].getLength()
                                            , this.backBuildings[ bIndex - this.numOfBuildingFront - 1 ].getHeight() );                            
                                break;                            
                            }
                        }
                        catch ( Exception e ) {
                            System.out.println( "\n" + e );
                        }
                    }
                }
                else if ( userInput.equals( "7" ) )
                    flag = false;

                else
                    System.out.println( "\nWrong Input! Please enter one of the given numbers.\n" );
            }
        }
    }


    /**
     * Method to print skyline silhoullette of street.
     */ 
    public void printSkyline ( ) {

        System.out.printf( "\nSkyline Silhouette of Street\n\n");
        
        int maxHeight = 0;
        int maxEndPos = 0;

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
            CityBuilding building = this.frontBuildings[i];
            int tempEndPos = building.getPosition() + building.getLength();

            if ( building.getHeight() > maxHeight )
                maxHeight = building.getHeight();

            if ( tempEndPos > maxEndPos )
                maxEndPos = tempEndPos;             
        }

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            CityBuilding building = this.backBuildings[i];
            int tempEndPos = building.getPosition() + building.getLength();

            if ( building.getHeight() > maxHeight )
                maxHeight = building.getHeight();

            if ( tempEndPos > maxEndPos )
                maxEndPos = tempEndPos;             
        }

        int[] skyline = new int[ maxEndPos + 1 ];

        for ( int i = 0; i < this.numOfBuildingFront; ++i ) {
            CityBuilding building = this.frontBuildings[i];
             
            for ( int j = building.getPosition(); j < building.getPosition() + building.getLength(); ++j )
                if ( building.getHeight() > skyline[ j ] )
                    skyline[j] = building.getHeight();       
            
        }

        for ( int i = 0; i < this.numOfBuildingBack; ++i ) {
            CityBuilding building = this.backBuildings[i];
            
            for ( int j = building.getPosition(); j < building.getPosition() + building.getLength(); ++j )
                if ( building.getHeight() > skyline[ j ] )
                    skyline[j] = building.getHeight(); 
        }

        for ( int y = maxHeight; y > 0; --y ) {

            for ( int x = 0; x <= maxEndPos; ++x ) {

                if ( skyline[x] == y &&
                        ( (x != 0 && skyline[x] != 0 && skyline[x-1] == skyline[x]) || 
                          (x < maxEndPos && skyline[x] != 0 && skyline[x] <= skyline[x+1]) ||
                          (x > 0 && skyline[x] > skyline[x-1]) ||
                          (x == 0 && skyline[x] > 0) ) ) {
                    System.out.printf( "_" );
                }
                else if ( skyline[x] > y &&
                            ( (x > 0 && skyline[x] > skyline[x-1] && skyline[x - 1] <= y ) ||                              
                              (x < maxEndPos && skyline[x] > skyline[x+1] && skyline[x + 1] <= y ) ||
                              (x == 0 && skyline[x] > 0) || ( x == maxEndPos && skyline[x] > 0 ) ) ) {
                    System.out.printf( "|" );
                }
                else
                    System.out.printf( " " );
            }            
            System.out.printf( "\n" );
        }


        for ( int i = 0; i < this.lengthOfStreet; ++i )
            System.out.printf("=");

        System.out.printf("\n");

        for ( int i = 0; i <= this.lengthOfStreet; i += 5 ) {
            if ( i < 10 )
                System.out.printf("%d    ", i);
            else if ( i < 100)
                System.out.printf("%d   ", i);
            else
                System.out.printf("%d  ", i);
        }

        System.out.printf("\n");
    }
}