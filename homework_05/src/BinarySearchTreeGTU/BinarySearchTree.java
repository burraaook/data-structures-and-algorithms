package BinarySearchTreeGTU;
import java.util.Arrays;
/**
 * 
 * A class which implements BinarySearchTree using array structure.
 * @author Burak Kocausta
 * @version 1.0 10.04.2022
 */ 
public class BinarySearchTree<E> implements SearchTree<E> {

	private E[] theData;
	private int capacity;

	private static final int INITIAL_CAPACITY = 31;

	/**
	 * No parameter constructor creates an empty tree.
	 */ 
	@SuppressWarnings("unchecked")
	public BinarySearchTree (  ) {
		theData = (E[]) new Object[INITIAL_CAPACITY];
		capacity = INITIAL_CAPACITY;
	}
	
	/**
	 * Constructor which takes root of the tree.
	 * @param root root of the tree.
	 */ 	
	public BinarySearchTree ( E root ) {
		this();
		if ( root != null )
			theData[0] = root;
	}

	/**
	 * Inserts given item to the tree properly.
	 * @param item item
	 * @return true if operation is successful, false otherwise.
	 */ 
	@Override
	public boolean add ( E item ) {
		if ( item == null )
			return false;

		if ( theData[0] == null ) {
			theData[0] = item;
			return true;
		}
		return add(item, 0);
	}

	/**
	 * Helper private recursive method for insertion.
	 * @return true if it is successful.
	 * @param item item
	 * @param cur current index
	 */ 
	@SuppressWarnings("unchecked")
	private boolean add( E item, int cur ) {

		//reallocate till cur can be accessible
		while ( cur >= capacity )	
			reallocate();

		int left = 2*cur + 1;
		int right = 2*cur + 2;		

		// insert location is found
		if ( theData[cur] == null ) {
			theData[cur] = item;

			while ( left >= capacity )
				reallocate();
			while ( right >= capacity )
				reallocate();

			// insert childs
			theData[left] = null;
			theData[right] = null;
			return true;
		}

		int result = ( (Comparable<E>) item).compareTo(theData[cur]);

		if ( result == 0 )
			return false;
		else if ( result < 0 )
			return add(item, left);		// go left
		else
			return add(item, right);	// go right
	}

	/**
	 * Helper private method for reallocation.
	 */ 
	private void reallocate ( ) {
		capacity = capacity * 2;
		theData = Arrays.copyOf(theData, capacity);
	}

	/**
	* Checks the given target is in the tree or not.
	* @param target target
	* @return true if it contains, false otherwise.
	*/ 
	@Override
	public boolean contains ( E target ) {
		if ( theData[0] == null )
			return false;
		return ( find(target,0) != null );
	}

	/**
	* 
	* Finds the data and returns it reference.
	* @param target target
	* @return data if it is found, returns null otherwise.
	*/
	@Override
	public E find( E target ) {
		if ( theData[0] == null )
			return null;
		return find(target,0);
	}

	/**
	 * Helper private recursive find method.
	 * @param target target
	 * @param cur current index
	 * @return target if it is found, null otherwise
	 */ 
	@SuppressWarnings("unchecked")
	private E find( E target, int cur ) {
		if ( cur >= capacity || theData[cur] == null )
			return null;

		int left = 2*cur + 1;
		int	right = 2*cur + 2;
		int result = ((Comparable<E>) target).compareTo ( theData[cur] );

		if ( result == 0 )
			return theData[cur];	
		else if ( result < 0 )
			return find(target, left); 		// go left
		else
			return find(target, right);		// go right
	}

	/**
	* 
	* Removes target from tree.
	* @param target target
	* @return true if operation is successful, returns false if it is not.
	*/
	@Override
	public boolean remove ( E target ) {
		
		if ( target == null || theData[0] == null )
			return false;

		return remove(target, 0);
	}

	/**
	 * Helper private recursive method to remove target from tree.
	 * @param target target
	 * @param cur current index
	 * @return true if it is removed, false otherwise.
	 */ 
	@SuppressWarnings("unchecked")
	private boolean remove ( E target, int cur ) {
		if ( cur >= capacity || theData[cur] == null )
			return false;
		
		int left = 2*cur + 1;
		int	right = 2*cur + 2;
		int result = ((Comparable<E>) target).compareTo ( theData[cur] );

		if ( result < 0 )
			return remove(target, left);

		else if ( result > 0 )
			return remove(target, right);

		else {	// if target is found
			// if it has no child
			if ( theData[left] == null && theData[right] == null )
				theData[cur] = null;
			
			// if it has only right child
			else if ( theData[left] == null ) 
				theData[cur] = findLeftMostRemove(right);
			
			// if it has only left child
			else if ( theData[right] == null ) 
				theData[cur] = findRightMostRemove(left);
			
			// if it has both child
			else 
				theData[cur] = findRightMostRemove(left);
			return true;
		}
	}

	/**
	 * Helper private recursive method to find and remove left most of given location.
	 * @param cur current index
	 * @return founded data
	 */ 
	private E findLeftMostRemove ( int cur ) {

		if ( cur >= capacity || theData[cur] == null )
			return null;

		int left = 2*cur + 1;
		int right = 2*cur + 2;

		if ( theData[left] == null ) {

			// if left and right is null remove data and return.
			if ( theData[right] == null ) {
				E target = theData[cur];
				theData[cur] = null;
				return target;				
			}

			// if left is null right is not null, remove current data and return.
			else {
				E target = theData[cur];
				remove(theData[cur], cur);
				return target;
			}
		}
		// if left is not null go left
		else
			return findLeftMostRemove( left );
	}

	/**
	 * Helper private recursive method to find and remove right most of given location.
	 * @param cur current index
	 * @return founded data
	 */ 
	private E findRightMostRemove ( int cur ) {
		if ( cur >= capacity || theData[cur] == null )
			return null;

		int left = 2*cur + 1;
		int right = 2*cur + 2;

		if ( theData[right] == null ) {

			// if left and right is null remove data and return.
			if ( theData[left] == null ) {
				E target = theData[cur];
				theData[cur] = null;
				return target;				
			}

			// if right is null, left is not null, remove current data, and return.
			else {
				E target = theData[cur];
				remove(theData[cur], cur);
				return target;
			}
		}
		// if right is not null go right		
		else
			return findLeftMostRemove( right );		
	}

	/**
	 * 
	 * Deletes target from tree.
	 * @param target target
	 * @return target if operation is successful, null if it is not.
	 */	
	@Override
	public E delete ( E target ) {
		if ( remove(target) )
			return target;
		return null;
	}

	/**
	 * Returns String representation of tree, it is preorder.
	 * @return String representation of tree.
	 */ 
	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(0, 1, sb);
		return sb.toString();
	}

	/**
	 * Helper private method for toString.
	 * @param cur current index
	 * @param depth current depth
	 * @param sb returned string.
	 */ 
	private void preOrderTraverse ( int cur, int depth, StringBuilder sb ) {
		for( int i = 1; i < depth; i++ )
			sb.append("  ");
		
		if ( cur >= capacity )
			return;
		
		int left = 2*cur+1;
		int right = 2*cur+2;

		if(theData[cur] == null)
			sb.append("null\n");
		else {
			sb.append(theData[cur].toString());
			sb.append("\n");
			preOrderTraverse(left, depth + 1, sb);
			preOrderTraverse(right, depth + 1, sb);
		}
	}
}