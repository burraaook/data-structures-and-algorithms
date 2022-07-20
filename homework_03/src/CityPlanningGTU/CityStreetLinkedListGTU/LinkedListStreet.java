package CityStreetLinkedListGTU;
import CityBuildingGTU.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ListIterator;

/**
 * 
 * LinkedListStreet class provides a buildable two dimensional, and two sided space for CityBuilding type buildings.
 * @version 1.0 18.03.2022
 * @author Burak Kocausta
 * 
 */
public class LinkedListStreet implements Cloneable {

    /** 
     *  CityBuilding arrays to store lands.  
     */
    private LinkedList<CityBuilding> frontBuildings;
    private LinkedList<CityBuilding> backBuildings;

    /**
     *  Length of street and numbers of buildings in both sides stored. 
     */
    private int lengthOfStreet;
    private int numOfBuildingFront;
    private int numOfBuildingBack;

    /**
     * Number of land left can be usable is holded as an integer.
     */ 
    private int numOfLandLeft;
    
    /**
     *  No parameter constructor sets every field to its default value.
     */ 
    public LinkedListStreet ( ) {
        this.lengthOfStreet = 55;
        this.frontBuildings = new LinkedList<CityBuilding>();
        this.backBuildings = new LinkedList<CityBuilding>();
        this.numOfBuildingFront = 0;
        this.numOfBuildingBack = 0;
        this.numOfLandLeft = 56*2;
    }

    /**
     * Constructor takes only lengthOfStreet of the street and sets the value according to the parameter.
     * @param lengthOfStreet total length of the street.
     * @throws Exception if lengthOfStreet is less than 10.
     */ 
    public LinkedListStreet ( int lengthOfStreet ) throws Exception {
        
        try {
            this.setLengthOfStreet( lengthOfStreet );
            this.numOfBuildingFront = 0;
            this.numOfBuildingBack = 0;
            this.numOfLandLeft = (lengthOfStreet + 1)*2;
            this.frontBuildings = new LinkedList<CityBuilding>();
            this.backBuildings = new LinkedList<CityBuilding>();  
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
        
        return this.frontBuildings.get(index);
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
        
        return this.backBuildings.get(index);
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
        
        ListIterator<CityBuilding> itr = this.backBuildings.listIterator();
        // check if same building reference is already exist.
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( curBuilding == building )
               throw new Exception ( "Same CityBuilding object is already added to street!\n" ); 
        }
        
        itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            // check if building can be added
            CityBuilding curBuilding = itr.next();
            int tempPos = curBuilding.getPosition();
            int tempEndPos = curBuilding.getLength();

            for ( int j = bPos; j < bPos+bLen; ++j )
                if ( j >= tempPos && j <= tempEndPos )
                    throw new Exception( "Place is occupied! Building cannot be added.\n" );              
        }

        // if building can be added proceed.
        this.frontBuildings.add( building );
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

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        // check if same building reference is already exist.
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( curBuilding == building )
               throw new Exception ( "Same CityBuilding object is already added to street!\n" ); 
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            // check if building can be added
            CityBuilding curBuilding = itr.next();
            int tempPos = curBuilding.getPosition();
            int tempEndPos = curBuilding.getLength();

            for ( int j = bPos; j < bPos+bLen; ++j )
                if ( j >= tempPos && j <= tempEndPos )
                    throw new Exception( "Place is occupied! Building cannot be added.\n" );              
        }
        
        // if building can be added proceed.            
        this.backBuildings.add( building );
        this.numOfBuildingBack += 1;
        this.numOfLandLeft -= bLen;                 
    }

    /**
     * Deletes building from front side of the street according to the given position.
     * @param position Position of the building as an int.
     */ 
    public void deleteFront ( int position ) {

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();

        while( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building.getPosition() == position ) {
                this.numOfLandLeft += building.getLength();
                this.numOfBuildingFront -= 1;
                itr.remove();
                break;              
            }
        }
    } 

    /**
     * Deletes building from front side of the street according to the given object.
     * @param building CityBuilding reference.
     */ 
    public void deleteFront ( CityBuilding building ) {

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( building.equals(curBuilding) ) {

                this.numOfLandLeft += curBuilding.getLength();
                itr.remove();
                this.numOfBuildingFront -= 1;
                break;
            }
        }
    }    

    /**
     * Deletes building from back side of the street according to the given position.
     * @param position Position of the building as an int.
     */ 
    public void deleteBack ( int position ) {

        ListIterator<CityBuilding> itr = this.backBuildings.listIterator();

        while( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building.getPosition() == position ) {
                this.numOfLandLeft += building.getLength();
                this.numOfBuildingBack -= 1;
                itr.remove();
                break;              
            }
        }
    }

    /**
     * Deletes building from back side of the street according to the given reference.
     * @param building CityBuilding reference.
     */
    public void deleteBack ( CityBuilding building ) {

        ListIterator<CityBuilding> itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( building.equals(curBuilding) ) {

                this.numOfLandLeft += curBuilding.getLength();
                itr.remove();
                this.numOfBuildingBack -= 1;
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
        
        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( curBuilding instanceof Playground ) {
                count++;
                totalLength = curBuilding.getLength();
            }
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding curBuilding = itr.next();
            if ( curBuilding instanceof Playground ) {
                count++;
                totalLength = curBuilding.getLength();
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


        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof House )
                houseLength += building.getLength();
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof House )
                houseLength += building.getLength();
        }

        return houseLength;
    }

    /**
     * Calculates the total length of markets in the street and returns.
     * @return integer
     */ 
    public int lengthOfMarkets ( ) {
        int marketLength = 0;

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof Market )
                marketLength += building.getLength();
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof Market )
                marketLength += building.getLength();
        }

        return marketLength;
    }

    /**
     * Calculates the total length of offices in the street and returns.
     * @return integer
     */ 
    public int lengthOfOffices ( ) {
        int officeLength = 0;

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof Office )
                officeLength += building.getLength();
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            if ( building instanceof Office )
                officeLength += building.getLength();
        }

        return officeLength;
    }

    /**
     * Overriden toString method returns information about LinkedListStreet object as a String.
     * @return It returns String.
     */ 
    @Override
    public String toString ( ) {
        
        String street = new String ( "\n__Front Side Buildings__\n\n" );

        int totalNum = 0;
        if ( this.numOfBuildingFront == 0 ) 
            street += String.format( "There aren't any building! Please add buildings to front side.\n" );
        else {
            ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
            while ( itr.hasNext() ) {
                CityBuilding building = itr.next();    totalNum++;
                street += String.format( "%d-  %s\n", totalNum, building );
            }
        }

        street += String.format( "\n__Back Side Buildings__\n\n" );
        if ( this.numOfBuildingBack == 0 ) 
            street += String.format( "There aren't any building! Please add buildings to back side.\n" );
        else {
            ListIterator<CityBuilding> itr = this.backBuildings.listIterator();
            while ( itr.hasNext() ) {
                CityBuilding building = itr.next();     totalNum++;
                street += String.format( "%d-  %s\n", totalNum, building );
            }
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
        if ( other != null && other instanceof LinkedListStreet ) {

            final LinkedListStreet otherStreet = ( LinkedListStreet ) other;
            if ( ( otherStreet.numOfBuildingFront == this.numOfBuildingFront )
                    && ( otherStreet.numOfBuildingBack == this.numOfBuildingBack ) 
                    && ( otherStreet.lengthOfStreet == this.lengthOfStreet ) 
                    && ( otherStreet.numOfLandLeft == this.numOfLandLeft ) ) {

                ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
                while ( itr.hasNext() ) {
                    CityBuilding building = itr.next();
                    if ( !otherStreet.frontBuildings.contains( building ) ) 
                        return false;                    
                }
                    
                itr = this.backBuildings.listIterator();
                while ( itr.hasNext() ) {
                    CityBuilding building = itr.next();
                    if ( !otherStreet.backBuildings.contains( building ) ) 
                        return false;                    
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
            LinkedListStreet copy = ( LinkedListStreet ) super.clone();
            copy.frontBuildings = new LinkedList<CityBuilding>();
            copy.backBuildings = new LinkedList<CityBuilding>();

            ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
            while ( itr.hasNext() ) {
                CityBuilding building = itr.next();
                copy.frontBuildings.add ( ( CityBuilding ) building.clone() );
            }
            
            itr = this.backBuildings.listIterator();
            while ( itr.hasNext() ) {
                CityBuilding building = itr.next();
                copy.backBuildings.add ( ( CityBuilding ) building.clone() );
            }
            
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
 
        result += this.frontBuildings.hashCode();

        result += this.backBuildings.hashCode();

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
                            this.deleteFront( this.frontBuildings.get(bIndex - 1) );    

                        else 
                            this.deleteBack( this.backBuildings.get(bIndex - this.numOfBuildingFront - 1) );
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
                            CityBuilding building = null;

                            if ( bIndex > ( this.numOfBuildingFront + this.numOfBuildingBack ) 
                                    || bIndex < 1 ) {
                                System.out.printf( "Enter the numbers between 1 and %d", this.numOfBuildingFront + this.numOfBuildingBack );
                                continue;
                            }
                            
                            else if ( bIndex <= this.numOfBuildingFront ) {

                                building = this.frontBuildings.get( bIndex - 1 );

                                Object tool = building.focus();
                                System.out.printf("\nfocus() function returned = ");
                                System.out.println(tool + "\n");
                            }

                            else {

                                building = this.backBuildings.get( bIndex - this.numOfBuildingFront - 1 );
                                Object tool = building.focus();
                                System.out.printf("\nfocus() function returned = ");
                                System.out.println(tool + "\n");                           
                            }

                            if ( building == null) break;

                            if ( building instanceof House ) {
                                House house = ( House ) building;
                                System.out.printf( "\nType = House\nOwner = %s\nColor = %s\nNumber Of Room = %s\n"
                                            , house.getOwner(), house.getColor(), house.getNumOfRoom() );
                            }

                            else if ( building instanceof Market ) {
                                Market market = ( Market ) building;
                                System.out.printf( "\nType = Market\nClosing Time = %s\nOpening Time = %s\nOwner = %s\n"
                                            , market.getClosingTime(), market.getOpeningTime(), market.getOwner() );
                            }

                            else if ( building instanceof Office ) {
                                Office office = ( Office ) building;
                                System.out.printf( "\nType = Office\nJob Type = %s\nOwner = %s\n"
                                            , office.getJobType(), office.getOwner() );                          
                            }

                            else if ( building instanceof Playground ) {
                                Playground playground = ( Playground ) building;
                                System.out.printf("\nType = Playground\n");
                            }

                            System.out.printf( "Position = %d\nLength = %d\nHeight = %d\n"
                                        , building.getPosition()
                                        , building.getLength()
                                        , building.getHeight() );
                            break;                          
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

        ListIterator<CityBuilding> itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            int tempEndPos = building.getPosition() + building.getLength();

            if ( building.getHeight() > maxHeight )
                maxHeight = building.getHeight();

            if ( tempEndPos > maxEndPos )
                maxEndPos = tempEndPos;           
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            int tempEndPos = building.getPosition() + building.getLength();

            if ( building.getHeight() > maxHeight )
                maxHeight = building.getHeight();

            if ( tempEndPos > maxEndPos )
                maxEndPos = tempEndPos;           
        }

        int[] skyline = new int[ maxEndPos + 1 ];

        itr = this.frontBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
            for ( int j = building.getPosition(); j < building.getPosition() + building.getLength(); ++j )
                if ( building.getHeight() > skyline[ j ] )
                    skyline[j] = building.getHeight();              
        }

        itr = this.backBuildings.listIterator();
        while ( itr.hasNext() ) {
            CityBuilding building = itr.next();
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