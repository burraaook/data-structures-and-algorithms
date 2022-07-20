package BinarySearchTreeGTU;

/**
 * 
 * Necessary methods for search tree structure.
 * @author Burak Kocausta
 * @version 1.0 10.04.2022
 */
public interface SearchTree<E> {
	/**
	 * 
	 * Inserts the given item properly to the tree, while insertion it does comparison.
	 * @param item item
	 * @return true if operation is successful, false otherwise.
	 */
	boolean add ( E item );
	
	/**
	 * 
	 * Removes target from tree.
	 * @param target target
	 * @return true if operation is successful, returns false if it is not.
	 */
	boolean remove ( E target );

	/**
	 * 
	 * Deletes target from tree.
	 * @param target target
	 * @return target if operation is successful, null if it is not.
	 */
	E delete ( E target );
	
	/**
	 * 
	 * Check operation for searching.
	 * @param target target
	 * @return true if it is found, false otherwise.
	 */
	boolean contains ( E target );
	
	/**
	 * 
	 * Finds the data and returns it reference.
	 * @param target target
	 * @return data if it is found, returns null otherwise.
	 */
	E find ( E target );
}