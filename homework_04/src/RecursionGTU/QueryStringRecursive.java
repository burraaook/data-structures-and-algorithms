package RecursionGTU;

/**
 * 
 * This class provides static recursive implementation for searching a String inside a greater String.
 * @version 1.0 23.03.2022
 * @author Burak Kocausta
 */
public class QueryStringRecursive {

	/**
	 * 
	 * Searches a string inside greater String, returns its index recursively.
	 * @return -1 if String cannot be found, otherwise returns its index.
	 * @param mainString String that will be searched inside.
	 * @param queryString String that will be searched.
	 * @param occurrence used to differ wanted String with its occurring time.
	 */ 
	public static int returnQueryStringIndex ( String mainString, String queryString, int occurrence ) {

		if ( queryString == null || mainString == null || queryString.length() > mainString.length() || occurrence <= 0 )
			return -1;

		int retval = returnQueryStringIndex ( mainString, queryString, occurrence, 0 );
		return retval;
	}

	/**
	 * Helper private method.
	 * @param mainString String that will be searched inside.
	 * @param queryString String that will be searched.
	 * @param occurrence used to differ wanted String with its occurring time.
	 * @param pos position of index. 
	 */ 
	private static int returnQueryStringIndex ( String mainString, String queryString, int occurrence, int pos ) {		
		if ( occurrence < 0 || mainString.equals("") || pos + queryString.length() > mainString.length())
			return -1;
		
		else if ( queryString.equals( mainString.substring( pos, pos + queryString.length())) ) {
			occurrence--;

			if ( occurrence == 0 )
				return pos;
			pos++;
			return returnQueryStringIndex( mainString, queryString, occurrence, pos );
		}

		else {
			pos++;
			return returnQueryStringIndex( mainString, queryString, occurrence, pos );
		}
	}	
}