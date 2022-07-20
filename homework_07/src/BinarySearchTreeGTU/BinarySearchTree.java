package BinarySearchTreeGTU;

/**
 * 
 * An iterable binary search tree class which extend BinaryTree class, and implements SearchTree, Iterable interfaces.
 * @version 1.2 14.05.2022
 * @author Burak Kocausta
 */ 
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E>{

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
	 * 
	 * Creates Tree with given root.
	 * @param root node
	 */ 
	protected BinarySearchTree ( Node<E> root ) {
		super(root);
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
	 * Converts the given binary search tree to avl tree with rotations.
	 * @param <T> a comparable type	 
	 * @param bst binary search tree
	 * @return AVL version of given binary search tree(type is BinarySearchTree)
	 */ 
	public static <T extends Comparable<T>> BinarySearchTree<T> convertToAVLTree ( BinarySearchTree<T> bst ) {
		
		bst.root = balancePostOrderly(bst.root);
		return bst;
	}	

	/**
	 * Private recursive helper method which makes rotation operations till tree is balanced.
	 * @param localRoot local root
	 * @return root
	 */ 
	private static <T extends Comparable<T>> Node<T> balancePostOrderly ( Node<T> localRoot ) {

		if ( localRoot == null )
			return null;

		localRoot.left = balancePostOrderly(localRoot.left);
		localRoot.right = balancePostOrderly(localRoot.right);		
		
		int balance = getBalanceFactor(localRoot);

		// left is heavy
		if ( balance < -1 ) {
			localRoot = reBalanceLeft(localRoot);
			localRoot.right = balancePostOrderly(localRoot.right);				
		}	

		// right is heavy
		if( balance > 1 ) {
			localRoot = reBalanceRight(localRoot);
			localRoot.left = balancePostOrderly(localRoot.left);			
		}

		return localRoot;
	}

	/**
	 * Calculates balance factor for given node.
	 * @param localRoot local root
	 * @return balance factor
	 */ 
	private static <T extends Comparable<T>> int getBalanceFactor ( Node<T> localRoot ) {

		if ( localRoot == null )
			return 0;

		int left = getHeight(localRoot.left);
		int right = getHeight(localRoot.right);
		return right - left;
	}

	/**
	 * Calculates height for given node.
	 * @param localRoot local root
	 * @return height
	 */ 
	private static <T extends Comparable<T>> int getHeight ( Node<T> localRoot ) {

		if ( localRoot == null )
			return 0;

		int left = getHeight(localRoot.left);
		int right = getHeight(localRoot.right);
		return 1 + ((right >= left) ? right : left);
	}

	/**
	 * Private helper method to rebalance left.
	 * @param localRoot localRoot
	 * @return root
	 */ 
	private static <T extends Comparable<T>> Node<T> reBalanceLeft ( Node<T> localRoot ) {
		int balance = getBalanceFactor(localRoot.left);

		// left right
		if ( balance > 0 ) 
			localRoot.left = rotateLeft(localRoot.left);
		

		// left left and left balanced
		return rotateRight(localRoot);
	}

	/**
	 * Private helper method to rebalance right
	 * @param localRoot localRoot
	 * @return root
	 */ 
	private static <T extends Comparable<T>> Node<T> reBalanceRight ( Node<T> localRoot ) {
		int balance = getBalanceFactor(localRoot.right);

		// right left
		if ( balance < 0 ) 
			localRoot.right = rotateRight(localRoot.right);
		

		// left left and left balanced
		return rotateLeft(localRoot);
	}

	/**
	 * Private helper method to rotate right
	 * @param localRoot localRoot
	 * @return root
	 */ 
	private static <T extends Comparable<T>> Node<T> rotateRight( Node<T> root ) {
		Node<T> temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}

	/**
	 * Private helper method to rotate left
	 * @param localRoot localRoot
	 * @return root
	 */ 
	private static <T extends Comparable<T>> Node<T> rotateLeft( Node<T> root ) {
		Node<T> temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}	
}