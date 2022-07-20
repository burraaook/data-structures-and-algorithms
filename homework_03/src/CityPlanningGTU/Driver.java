
import CityStreetArrayListGTU.*;
import CityStreetLDLinkedListGTU.*;
import CityStreetLinkedListGTU.*;
import CityStreetBasicArrayGTU.*;
import CityBuildingGTU.*;

import java.util.*;

/**
 * 
 *	Driver class to test all functionalities of BasicArrayStreet, CityBuilding, House, Office, Market, Playground classes.
 * 	@author Burak Kocausta
 * 	@version 1.0 6.03.2022 
 */
public class Driver {

	public static void main ( String args[] ) {

		test();
		cityMenu();
	}

	/**
	 * An interface for testing interactively.
	 */ 
	public static void cityMenu ( ) {

		boolean flag = true;
        String userInput;
        Scanner scanInput = new Scanner( System.in );
        BasicArrayStreet cityStreet = new BasicArrayStreet( );
		
		while ( flag ) {	
			System.out.printf( "\n___City Planning Menu___\n%s\n", cityStreet);
			System.out.printf( "\n1- New Street\n2- Edit Mode\n3- View Mode\n4- Test LinkedListStreet\n" );
			System.out.printf( "5- Test ArrayListStreet\n6- Test LDLinkedList\n7- Test LDLinkedListStreet\n" );
			System.out.printf( "8- Test BasicArrayStreet and Buildings\n9- Exit\nInput = " );
									
			userInput = scanInput.nextLine( );

            if( userInput.length() >= 1 ) {

                if ( userInput.equals( "1" ) || userInput.equals( "1\r" ) ) {
                  
					try {

						System.out.printf ( "Please enter length of the street = " );
						
					    while ( !scanInput.hasNextInt() ) {
					      System.out.printf( "Please enter an integer!\nStreet Length = " );
					      scanInput.nextLine();
					    } 
						int length = Integer.parseInt( scanInput.nextLine() );						
						cityStreet = new BasicArrayStreet ( length );
					}
					catch( Exception e ) {
						System.out.println( "\n" + e);
					}
                }

                else if ( userInput.equals( "2" ) || userInput.equals( "2\r" ) ) {
                    cityStreet.editMode();
                }

                else if ( userInput.equals( "3" ) || userInput.equals( "3\r" ) ) {
                    cityStreet.viewMode();
                }

                else if ( userInput.equals( "4" ) || userInput.equals( "4\r" ) )
                   	TestLinkedListStreet.testLinkedListStreet();

                else if ( userInput.equals( "5" ) ) 
                	TestArrayListStreet.testArrayListStreet();

                else if ( userInput.equals( "6" ) ) 
					TestLDLinkedList.testLDLinkedList();

                else if ( userInput.equals( "7" ) ) 
					TestLDLinkedListStreet.testLDLinkedListStreet();	

                else if ( userInput.equals( "8" ) ) {
					TestBasicArrayStreet.testBasicArrayStreet();
					testCityBuildings();    
                }

                else if ( userInput.equals( "9" ) ) 
                	flag = false;             	                	
                
                else
                    System.out.println( "Wrong Input! Please enter one of the given numbers." );
            }			
		}
	}

	/**
	 * Method tests each methods of these classes.
	 */ 
	public static void test ( ) {
		try {	
			
			testCityBuildings();
			TestBasicArrayStreet.testBasicArrayStreet();			
			TestArrayListStreet.testArrayListStreet();
			TestLinkedListStreet.testLinkedListStreet();			
			TestLDLinkedList.testLDLinkedList();
			TestLDLinkedListStreet.testLDLinkedListStreet();	
			testExceptions();

			System.out.println( "Exceptions are tested.\n" );
			System.out.printf( "\n\nTEST SUMMARY\n1- All 4 street classes constructors are tested.\n" );
			System.out.printf( "2- add and delete methods are tested with their overloads.\n" );
			System.out.printf( "3- All view mode functionalities are tested( like ratio calculation ).\n" );
			System.out.printf( "4- Skyline silhoullette printing is tested.\n" );
			System.out.printf( "5- focus() methods is tested.\n" );
			System.out.printf( "6- clone(), equals(), toString(), hashCode() methods are tested for all classes.\n" );
			System.out.printf( "7- All methods of CityBuilding, House, Market, Office, Playground are tested.\n" );
			System.out.printf( "8- LDLinkedList class and its methods tested. Also its iterator is tested.\n" );			
			System.out.printf( "9- All exceptions are tested.\n\n" );
		}
		catch ( Exception e ) {
			System.out.println(e);
		}		
	}
  

	/**
	 * Method to test exceptions.
	 */ 
	public static void testExceptions ( ) {
		
		System.out.println( "\n___TESTING EXCEPTIONS___\n" );

		try {
			System.out.println( "\nNegative value is entered as number of house in House constructor.\n" );
			House house = new House ( 0, 10, 30 ,"owner", "red", -2 );
		}
		catch ( Exception e ) {
			System.out.println( e );
		}

		try {
			System.out.println( "\nInvalid time format entered as opening time(or closing time) in Market constructor.\n" );
			Market market = new Market ( 0, 15, 20, "owner", "15", "3" );
		}
		catch ( Exception e ) {
			System.out.println( e );
		} 

		try {
			System.out.println ( "\nNegative position, length, or height entered to Office(any CityBuilding class) class.\n" );
			Office office = new Office( -5,20, 6, "owner", "jobtype");
		}
		catch ( Exception e ) {
			System.out.println( e );
		}
		try {
			System.out.printf( "\nNegative value is entered as an argument during BasicArrayStreet constructor.\n" );
			BasicArrayStreet street = new BasicArrayStreet( -5 );
		}
		catch ( Exception e ) {
			System.out.println ( e );
		}

		try {

			BasicArrayStreet street = new BasicArrayStreet ( );
			System.out.println( "\nNew street is created with no parameter constructor. Its length is 55.\n" );
			System.out.println( street );
			street.addFront( new Playground ( 3, 10 ) );
			System.out.println("\nPlayground is added to street.\n" );
			System.out.println( street );
			System.out.println( "\nNew building is added to conflicting position with playground.\n " );
			street.addFront( new Playground( 2, 5 ) );
		}
		catch ( Exception e ) {
			System.out.println ( e );			
		}

		try {
			BasicArrayStreet street = new BasicArrayStreet ( 55 );
			System.out.println( "\nNew street is created.\n" );
			Playground playground = new Playground ( 0, 15 );
			System.out.println( "\nPlayground is added to the street.\n" );
			street.addBack(playground);
			System.out.println ( street );

			System.out.println( "\nSame object is tried to add to street.\n" );
			street.addFront(playground);
		}

		catch ( Exception e ) {
			System.out.println ( e );
		}

		try {
			BasicArrayStreet street = new BasicArrayStreet ( 55 );
			System.out.println( "\nNew street is created.\n" );

			street.addFront( new House( 0, 10 , 15, "burak", "green", 4 ) );
			street.addFront( new Market( 11, 12, 3, "owner", "18:00", "21:00" ) );
			street.addBack( new Playground( 15, 3 ) );
			System.out.println( "\nBuildings are added to the street.\n" );
			System.out.println ( street );

			System.out.println( "\nTrying to access 3th index of front buildings.\n" );
			street.atFront(3);
		}
		catch ( Exception e ) {
			System.out.println ( e );
		}

		System.out.println( "\nTESTING ArrayListStreet Exceptions" );

		try {
            System.out.printf( "\nNegative value is entered as an argument during ArrayListStreet constructor.\n" );
            ArrayListStreet street = new ArrayListStreet( -5 );
        }
        catch ( Exception e ) {
            System.out.println ( e );
        }

        try {

            ArrayListStreet street = new ArrayListStreet ( );
            System.out.println( "\nNew street is created with no parameter constructor. Its length is 55.\n" );
            System.out.println( street );
            street.addFront( new Playground ( 3, 10 ) );
            System.out.println("\nPlayground is added to street.\n" );
            System.out.println( street );
            System.out.println( "\nNew building is added to conflicting position with playground.\n " );
            street.addFront( new Playground( 2, 5 ) );
        }
        catch ( Exception e ) {
            System.out.println ( e );           
        }

        try {
            ArrayListStreet street = new ArrayListStreet ( 55 );
            System.out.println( "\nNew street is created.\n" );
            Playground playground = new Playground ( 0, 15 );
            System.out.println( "\nPlayground is added to the street.\n" );
            street.addBack(playground);
            System.out.println ( street );

            System.out.println( "\nSame object is tried to add to street.\n" );
            street.addFront(playground);
        }

        catch ( Exception e ) {
            System.out.println ( e );
        }

        try {
            ArrayListStreet street = new ArrayListStreet ( 55 );
            System.out.println( "\nNew street is created.\n" );

            street.addFront( new House( 0, 10 , 15, "burak", "green", 4 ) );
            street.addFront( new Market( 11, 12, 3, "owner", "18:00", "21:00" ) );
            street.addBack( new Playground( 15, 3 ) );
            System.out.println( "\nBuildings are added to the street.\n" );
            System.out.println ( street );

            System.out.println( "\nTrying to access 3th index of front buildings.\n" );
            street.atFront(3);
        }
        catch ( Exception e ) {
            System.out.println ( e );
        }		
	}

	/**
	 * Method to test CityBuilding class and its derived classes.
	 */ 
	public static void testCityBuildings ( ) {

		System.out.println( "___TESTING CityBuildings___" );

		testHouse();
		testMarket();
		testOffice();
		testPlayground();
	}

	/**
	 * Method to test House class.
	 */ 
	public static void testHouse ( ) {

		System.out.println ( "---TESTING House---\n" );
		try {
			CityBuilding house1 = new House ( 3, 15, 20, "burak", "red", 4 );
			CityBuilding house2 = new House ( );
			System.out.println( "\nhouse1 and house2 created. house2 is created with no parameter constructor." );
			System.out.println( "\nhouse1 and house2 is declared as CityBuilding class." );
			System.out.println( "\nhouse1 = " + house1 + "\nhouse2 = " + house2 );

			System.out.printf( "\nCalling CityBuilding class getters = %d %d %d", house1.getPosition(),
						 house1.getLength(), house1.getHeight() );

			System.out.printf( "\nChecking equality with equals method.\nhouse1.equals(house2) returns = %s\n", 
				house1.equals(house2) );

			House house3 = ( House ) house1.clone();
			System.out.printf( "\nhouse3 created with using clone() method. And the result is downcasted to House class.\nhouse3 = %s\n",house3 );
			System.out.printf( "house1.equals(house3) returns = %s\n", house1.equals(house3) );

			house3.setNumOfRoom( 7 );
			house3.setColor("black");
			house3.setOwner("elma");
			System.out.println( "house3 is changed with its accessors." );
			System.out.printf("House classes getters returns = %s %s %d\n" , house3.getOwner(), house3.getColor(), house3.getNumOfRoom() );

			System.out.printf( "house1.focus() returns(called from CityBuilding class) = %s\n", house1.focus() );
			System.out.println( "house3.hashCode() = " + house3.hashCode() + "\n" );
		}
		catch( Exception e ) {
			System.out.println(e);
		}
	}

	/**
	 * Method to test Market class.
	 */ 
	public static void testMarket ( ) {
		System.out.println ( "---TESTING Market---\n" );
		try {
			CityBuilding market1 = new Market ( 21, 10, 17, "john", "08:00", "21:00" );
			CityBuilding market2 = new Market ( );
			System.out.println( "\nmarket1 and market2 created. market2 is created with no parameter constructor." );
			System.out.println( "\nmarket1 and market2 is declared as CityBuilding class." );
			System.out.println( "\nmarket1 = " + market1 + "\nhouse2 = " + market2 );

			System.out.printf( "\nCalling CityBuilding class getters = %d %d %d", market1.getPosition(),
						 market1.getLength(), market1.getHeight() );

			System.out.printf( "\nChecking equality with equals method.\nmarket1.equals(market2) returns = %s\n", 
				market1.equals(market2) );

			Market market3 = ( Market ) market1.clone();
			System.out.printf( "\nmarket3 created with using clone() method. And the result is downcasted to Market class.\nmarket3 = %s\n"
							, market3 );
			System.out.printf( "market1.equals(market3) returns = %s\n", market1.equals(market3) );

			market3.setOpeningTime( "13:00" );
			market3.setClosingTime("23:30");
			market3.setOwner("elma");
			System.out.println( "market3 is changed with its accessors." );
			System.out.printf("Market classes getters returns = %s %s %s\n"
				 , market3.getOwner(), market3.getOpeningTime(), market3.getClosingTime() );

			System.out.printf( "market1.focus() returns(called from CityBuilding class) = %s\n", market1.focus() );
			System.out.println( "market3.hashCode() = " + market3.hashCode() + "\n" );			
		}

		catch ( Exception e ) {
			System.out.println(e);
		}
	}

	/**
	 * Method to test Office class.
	 */ 
	public static void testOffice ( ) {
		System.out.println ( "---TESTING Office---\n" );
		try {
			CityBuilding office1 = new Office ( 19, 10, 15, "alice", "sales" );
			CityBuilding office2 = new Office ( );
			System.out.println( "\noffice1 and office2 created. office2 is created with no parameter constructor." );
			System.out.println( "\noffice1 and office2 is declared as CityBuilding class." );
			System.out.println( "\noffice1 = " + office1 + "\nhouse2 = " + office2 );

			System.out.printf( "\nCalling CityBuilding class getters = %d %d %d", office1.getPosition(),
						 office1.getLength(), office1.getHeight() );

			System.out.printf( "\nChecking equality with equals method.\noffice1.equals(office2) returns = %s\n", 
				office1.equals(office2) );

			Office office3 = ( Office ) office1.clone();
			System.out.printf( "\noffice3 created with using clone() method. And the result is downcasted to Office class.\noffice3 = %s\n"
				, office3 );
			System.out.printf( "office1.equals(office3) returns = %s\n", office1.equals(office3) );

			office3.setJobType( "medical" );
			office3.setOwner("elma");
			System.out.println( "office3 is changed with its accessors." );
			System.out.printf("Office classes getters returns = %s %s\n"
				 , office3.getOwner(), office3.getJobType() );

			System.out.printf( "office1.focus() returns(called from CityBuilding class) = %s\n", office1.focus() );
			System.out.println( "office3.hashCode() = " + office3.hashCode() + "\n" );		
		}

		catch ( Exception e ) {
			System.out.println(e);
		}
	}

	/**
	 * Method to test playground class.
	 */ 
	public static void testPlayground ( ) {
		System.out.println ( "---TESTING Playground---\n" );
		try {
			CityBuilding playground1 = new Playground ( 22, 11 );
			CityBuilding playground2 = new Playground ( );
			System.out.println( "\nplayground1 and playground2 created. playground2 is created with no parameter constructor." );
			System.out.println( "\nplayground1 and playground2 is declared as CityBuilding class." );
			System.out.println( "\nplayground1 = " + playground1 + "\nhouse2 = " + playground2 );

			System.out.printf( "\nCalling CityBuilding class getters = %d %d %d\n", playground1.getPosition(),
						 playground1.getLength(), playground1.getHeight() );

			System.out.printf( "\nChecking equality with equals method.\nplayground1.equals(playground2) returns = %s\n", 
				playground1.equals(playground2) );

			Playground playground3 = ( Playground ) playground1.clone();
			System.out.printf( "\nplayground3 created with using clone() method. And the result is downcasted to Playground class.\n" );
			System.out.printf("playground3 = %s\n", playground3);
			System.out.printf( "playground1.equals(playground3) returns = %s\n", playground1.equals(playground3) );

			System.out.printf( "playground1.focus() returns(called from CityBuilding class) = %s\n", playground1.focus() );
			System.out.println( "playground3.hashCode() = " + playground3.hashCode() + "\n" );		
		}
		catch ( Exception e ) {
			System.out.println(e);
		}
	}
}