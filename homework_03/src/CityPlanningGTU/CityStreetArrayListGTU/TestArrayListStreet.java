package CityStreetArrayListGTU;
import CityBuildingGTU.*;
import java.util.*;

/**
 * Class for testing ArrayListStreet class.
 * @author Burak Kocausta
 */ 
public class TestArrayListStreet {


    /**
     * Method to test ArrayListStreet class
     */ 
    public static void testArrayListStreet ( ) {
        try {
            System.out.println( "___TESTING ArrayListStreet class___\n" );

            ArrayListStreet street1 = new ArrayListStreet( 55 );

            System.out.println( "\nEmpty Street created.\n" );
            System.out.printf( "Length of Street is = %d", street1.getLengthOfStreet() );

            System.out.println( street1 );
            System.out.println( "---------------------------\n" );

            CityBuilding house1 = new House( 6, 7, 5, "burak", "green", 3 );
            System.out.println( "\nHouse created\n" );
            System.out.println( house1 );
            System.out.println( "---------------------------\n" );

            CityBuilding market1 = new Market( 0, 8, 20, "james" , "08:00" , "21:00" );
            System.out.println( "\nMarket created\n" );
            System.out.println( market1 );
            System.out.println( "---------------------------\n" );

            CityBuilding office1 = new Office( 10, 12, 20, "rachel", "consulting" );
            System.out.println( "\nOffice created\n" );
            System.out.println( office1 );
            System.out.println( "---------------------------\n" );

            CityBuilding playground1 = new Playground ( 31, 4 );
            System.out.println( "\nPlayground created\n" );
            System.out.println( playground1 );
            System.out.println( "---------------------------\n" );

            street1.addFront( house1 );
            street1.addBack( market1 );
            street1.addBack( office1 );
            street1.addFront( playground1 );

            System.out.println( "\nhouse, market, office, and playground are added to street.\n" );
            System.out.println( street1 );
            System.out.println( "---------------------------\n" );

            street1.deleteFront( house1 );
            street1.deleteFront( playground1 );
            street1.deleteBack( 10 );
            street1.deleteBack( 0 );

            System.out.println( "\nAll buildings are removed from class with 4 overloaded delete methods.\n" );
            System.out.println( street1 );
            System.out.println( "---------------------------\n" );

            street1.addFront( market1 );
            street1.addBack( house1 );
            street1.addBack( playground1 );
            street1.addFront( office1 );

            System.out.println( "\nBuildings are added to street oppositely.\n" );
            System.out.println( street1 );
            System.out.printf( "Total remaining lands = %d\n", street1.totalRemainingLand() );
            System.out.printf( "Total number of Buildings = %d\n", street1.getNumOfBuilding() );
            street1.ratioOfPlaygrounds();
            System.out.printf("\nTotal length of Market(s) = %d\nTotal length of House(s) = %d\nTotal length of Office(s) = %d\n"
                        , street1.lengthOfMarkets(), street1.lengthOfHouses(), street1.lengthOfOffices() );                         
            System.out.println( "---------------------------\n" );

            street1.printSkyline();

            street1.addFront( new House( 36, 10 , 10,  "olivia", "yellow", 3 ) );
            street1.addFront( new House( 48, 7, 10, "ahmet", "gray", 2 ));
            street1.addBack( new House( 17, 13 , 30, "elif", "black", 3  ) );
            street1.addBack( new Market( 43, 8 , 20, "george", "06:30", "19:00" ) );
            System.out.println( "\nNew buildings are added.\n" );

            System.out.println( street1 );
            System.out.printf("\nTotal length of Market(s) = %d\nTotal length of House(s) = %d\nTotal length of Office(s) = %d\n"
                        , street1.lengthOfMarkets(), street1.lengthOfHouses(), street1.lengthOfOffices() ); 

            street1.printSkyline();

            System.out.println( "Testing focus() method for every building. Called from CityBuilding array." );

            for ( int i = 0; i < street1.getNumOfBuildingFront(); ++i ) 
                System.out.printf( "focus() returned = %s\n", street1.atFront(i).focus() );
                
            for ( int i = 0; i < street1.getNumOfBuildingBack(); ++i )
                System.out.printf( "focus() returned = %s\n", street1.atBack(i).focus() );
            
            System.out.println( "---------------------------\n" );

            System.out.println( "\nTesting clone() method." );

            ArrayListStreet street2 = ( ArrayListStreet ) street1.clone();

            System.out.println( "Original street\n"  + street1 );
            System.out.println( "Cloned street\n" + street2 );

            System.out.println( "---------------------------\n" );

            System.out.printf( "\nTesting equals() method\nresult of street1.equals(street2) is = " );

            System.out.println( street1.equals(street2) );
            System.out.println( "---------------------------\n" );

            System.out.println( "\nDeleting one building from street2\n" );
            street2.deleteBack(playground1);
            System.out.println( street2 );

            System.out.println( "After deleting result of condition is: " + street1.equals(street2) );
            System.out.println( "---------------------------\n" );

            System.out.println( "street1.hashCode() = " + street1.hashCode() + "\n" );
        }
        catch ( Exception e ) {
            System.out.println(e);
        }
    }	
}