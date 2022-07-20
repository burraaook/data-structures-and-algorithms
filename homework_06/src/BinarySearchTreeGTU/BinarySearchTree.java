package BinarySearchTreeGTU;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * An iterable binary search tree class which extend BinaryTree class, and implements SearchTree, Iterable interfaces.
 * @version 1.0 23.04.2022
 * @author Burak Kocausta
 */ 
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E>, Iterable<E> {

	/**
	 * Return value which is for returning the result of insertion.
	 */ 
	protected boolean addReturn;
	
	/**
	 * Returned value which is for returning the deleted element.
	 */ 
	protected E deleteReturn;

	/**
	 * No parameter constructor for BinarySearchTree.
	 */ 
	public BinarySearchTree ( ) {
		super();
	}

	/**
	 * One parameter constructor makes given item the root of the tree.
	 * @param item comparable item
	 */ 
	public BinarySearchTree ( E item ) {
		root = new Node<E>(item);
	}
	/**
	 * Returns an iterator which has next, hasNext methods.
	 * @return iterator
	 */ 
	public Iterator<E> iterator ( ) {
		return new BSTIterator();
	}

	/**
	 * Checks if tree is empty or not.
	 * @return true if it is empty.
	 */ 
	public boolean isEmpty ( ) {
		return root == null;
	}

	/**
	 * Checks if target is in the tree.
	 * @param target target
	 * @return true if it is found, false otherwise.
	 */
	@Override 
	public boolean contains( E target ) {
		E result = find(target);
		return result == target;
	}

	/**
	 * Finds the data and returns it reference.
	 * @param target target
	 * @return data if it is found, returns null otherwise.
	 */
	@Override
	public E find ( E target ) {
		if ( target == null )
			return null;
		return find(root, target);
	}
	
	/**
	 * Helper Recursive method that finds the target value in the tree. 
	 * @param target target value
	 * @param localRoot local root
	 * @return the data that found. If it is not in the tree, returns null.
	 */ 
	private E find ( Node<E> localRoot, E target ) {
		if(localRoot == null)
			return null;
		int compResult = target.compareTo(localRoot.data);
		
		if(compResult == 0)
			return localRoot.data;
		else if (compResult < 0)
			return find(localRoot.left, target);
		else
			return find(localRoot.right, target);
	}

	/**
	 * Inserts given item to proper place.
	 * @param item comparable item.
	 * @return true if it is inserted.
	 */
	@Override
	public boolean add ( E item ) {
		if ( item == null )
			return false;
		root = add(root, item);
		return addReturn;
	}

	/**
	 * Private recursive helper method for insertion.
	 * @param localRoot local root
	 * @param item item
	 * @return returns the new root.
	 */ 
	private Node<E> add ( Node<E> localRoot, E item ) {
		if( localRoot == null ) {
			addReturn = true;
			return new Node<E>(item);
		} 

		int result = item.compareTo(localRoot.data);
		if ( result == 0 ) {
			addReturn = false;
			return localRoot;
		} 
		else if ( result < 0 ) {
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		} 
		else {
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}
	}
	
	/**
	 * Deletes target from tree.
	 * @param target target
	 * @return target if operation is successful, null if it is not.
	 */
	@Override
	public E delete ( E target ) {
		if ( target == null )
			return null;
		root = delete(root, target);
		return deleteReturn;
	}
	
	/**
	 * Private helper recursive method for delete operation.
	 * @param localRoot local root
	 * @param item item
	 * @return Node which will be new root.
	 */ 
	private Node<E> delete ( Node<E> localRoot, E item ) {
		if( localRoot == null ) {
			deleteReturn = null;
			return localRoot;
		}

		int result = item.compareTo(localRoot.data);
		if( result < 0 ) {
			// go left
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		} 
		else if ( result > 0 ) {
			// go right
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}
		else {
			// item is found
			deleteReturn = localRoot.data;
			if( localRoot.left == null )
				return localRoot.right;
			else if ( localRoot.right == null )
				return localRoot.left;
			else {
				// 2 children condition
				if ( localRoot.left.right == null ) {
					localRoot.data = localRoot.left.data;
					localRoot.left = localRoot.left.left;
					return localRoot;	
				} 
				else {
					// find rightmost child of left child
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}
	
	/**
	 * Private helper recursive method to return rightmost child.
	 * @param parent parent node which node's rightmost child will be returned.
	 * @return returns the data that found.
	 */ 
	private E findLargestChild ( Node<E> parent ) {
		if( parent.right.right == null ) {
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		} 
		else // go right 
			return findLargestChild(parent.right);
	}
	
	/**
	 * Removes target from tree.
	 * @param target target
	 * @return true if operation is successful, returns false if it is not.
	 */	
	public boolean remove ( E target ) {
		if ( target == null )
			return false;
		delete(target);
		return deleteReturn == target;
	}

	/**
	 * 
	 * An iterator class which iterator through tree preorderly.
	 * @version 1.0 23.04.2022
	 * @author Burak Kocausta
	 */ 
	private class BSTIterator implements Iterator<E> {

		/**
		 * nodeDeque to hold nodes as a stack.
		 */ 
		private ArrayDeque<Node<E>> nodeDeque;

		/**
		 * next node.
		 */ 
		private Node<E> next = null;

		/**
		 * Creates an empty deque or pushes the root.
		 */
		public BSTIterator ( ) {
			next = root;
			nodeDeque = new ArrayDeque<Node<E>>();
			if ( root != null )
				nodeDeque.push(root);
		}

		/**
		 * Returns the next nodes data, and moves to other node.
		 * @return the data of the next node.
		 * @throws NoSuchElementException if there is no next.
		 */
		@Override 
		public E next ( ) throws NoSuchElementException {
			if ( !hasNext() || nodeDeque.isEmpty())
				throw new NoSuchElementException();

			Node<E> curNode = nodeDeque.pop();
			
			if ( curNode.right != null )
				nodeDeque.push(curNode.right);
			
			if ( curNode.left != null )
				nodeDeque.push(curNode.left);
			
			next = nodeDeque.peek();
			return curNode.data;
		}

		/**
		 * Checks if there is next item.
		 * @return true if next item is not null
		 */
		@Override 
		public boolean hasNext( ) {
			return next != null;
		}
	}
}