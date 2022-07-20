package BinaryHeapGTU;
import java.io.*;
import java.util.*;
/**
 * 
 * A Binary Heap class which is implemented with node link structure.
 * @author Burak Kocausta
 * @version 1.0 09.04.2022
 */ 
@SuppressWarnings("serial")
public class LinkedBinaryHeap<E> extends BinaryTree<E> {

	/**
	 * 
	 * Node class which is extended from BinaryTree's inner node class.
	 * @author Burak Kocausta
	 * @version 1.0 09.04.2022
	 */ 
	@SuppressWarnings("serial")
	protected static class Node<E> extends BinaryTree.Node<E> {
		
		/**
		 * Node reference to the parent.
		 */ 
		protected Node<E> parent = null;

		/**
		 * Constructs a node with given data.
		 * @param data data
		 */  
		public Node ( E data ) {
			super(data);
			parent = null;
		}
	}

	/**
	 * comparator for constructing different kind of heaps
	 */ 
	protected Comparator<E> comparator = null;

	/**
	 * reference to the insertion place.
	 */ 
	protected Node<E> addNodeRef = null;

	/**
	 * reference to the node which is parent of the node that will be put root before updating.
	 */ 
	protected Node<E> removeNodeRef = null;
	
	/**
	 * size of the tree.
	 */ 
	protected int size = 0;

	/**
	 * maximum depth of the nodes.
	 */ 
	protected int maxDepth = 0;

	/**
	 * No parameter constructor sets fields to null or 0.
	 */ 
	public LinkedBinaryHeap ( ) {
		super.root = null;
		size = 0;
		comparator = null;
	}

	/**
	 * Constructor which takes root.
	 * @param root root
	 */ 
	protected LinkedBinaryHeap ( Node<E> root ) {
		if ( root != null )
			root.parent = null;
		super.root = root;
		addNodeRef = root;
		removeNodeRef = root;
		size = 1;
		maxDepth = 1;
		comparator = null;
	}

	/**
	 * Constructor which takes root and comparator.
	 * @param comp comparator
	 * @param item root item 
	 */  
	public LinkedBinaryHeap ( E item, Comparator<E> comp ) {
		Node<E> temp = new Node<E>(item);		
		
		if ( item == null ) {
			size = 0;
			maxDepth = 0;
		}
		else {
			size = 1;
			maxDepth = 1;				
		}
		super.root = temp;		
		addNodeRef = temp;
		removeNodeRef = temp;
		comparator = comp;
	}

	/**
	 * Constructor using left and right trees.
	 * @param data data
	 * @param leftTree left subtree
	 * @param rightTree right subtree
	 */
	public LinkedBinaryHeap ( E data, LinkedBinaryHeap<E> leftTree, LinkedBinaryHeap<E> rightTree ) {
		Node<E> tempRoot = new Node<E>(data);
		if( leftTree != null )
			tempRoot.left = leftTree.root;
		else
			tempRoot.left = null;
		
		if( rightTree != null )
			tempRoot.right = rightTree.root;
		else 
			tempRoot.right = null;
		root = tempRoot;
		updateNodesFromRoot( (Node<E>) root);	// arrange hiearchy
	}

	/**
	 * Method to get left subtree.
	 * @return New binary heap which is left, if left is null return null. 
	 */ 
	@Override
	public LinkedBinaryHeap<E> getLeftSubtree ( ) {
		if( root != null && root.left != null )
			return new LinkedBinaryHeap<E>( (Node<E>) root.left );
		else
			return new LinkedBinaryHeap<E> (null);
	}

	/**
	 * Method to get right subtree.
	 * @return New binary heap which is right, if right is null return null. 
	 */ 
	@Override
	public LinkedBinaryHeap<E> getRightSubtree ( ) {
		if( root != null && root.right != null )
			return new LinkedBinaryHeap<E>( (Node<E>) root.right );
		else 
			return new LinkedBinaryHeap<E> (null);
	}

	/**
	 * Get size of binary heap.
	 * @return integer size
	 */ 
	public int size ( ) {
		return size;
	}

	/**
	 * Private helper compare method to make comparison with or without comparator.
	 * @param right right value
	 * @param left left value
	 * @return int value according to the comparison.
	 */ 
	@SuppressWarnings("unchecked")
	private int compare(E left, E right){
		if(comparator != null)
			return comparator.compare(left, right);
		else
			return ((Comparable<E>) left).compareTo(right);
	}

	/**
	 * Offer an item to binary heap.
	 * @param item item
	 * @return true
	 */ 
	public boolean offer ( E item ) {
		return add(item);
	}

	/**
	 * Add an item to binary heap.
	 * @param item item
	 * @return true
	 */ 
	public boolean add( E item ) {
		if ( item == null )
			return false;

		if ( root == null ) {
			//create new root
			Node<E> temp = new Node<E>(item);
			addNodeRef = temp;
			removeNodeRef = temp;
			root = temp;
			temp.parent = null;
			size++;
			maxDepth++;
		}
		else {
			//check left
			if ( addNodeRef.left == null ) {
				Node<E> temp = new Node<E>(item);
				addNodeRef.left = temp;
				temp.parent = addNodeRef;
				if(isPerfect())
					maxDepth++;
				size++;
				updateNodes( temp );
			}
			// check right
			else if ( addNodeRef.right == null ) {
				Node<E> temp = new Node<E>(item);
				addNodeRef.right = temp;
				temp.parent = addNodeRef;
				size++;
				updateNodes( temp );
				findNewAddNodeRef();
			}
			findNewRemoveNodeRef();
		}
		return true;
	}

	/**
	 * Private helper method to arrange new addNodeRef.
	 */ 
	private void findNewAddNodeRef ( ) {
		if ( isPerfect() ) {
			Node<E> temp = ( Node<E> ) root;
			while ( temp.left != null ) {
				temp = ( Node<E> ) temp.left;
			}
			addNodeRef = temp;
		}
		else 
			findNewAddNodeRef ( (Node<E>) root, 1 );	// search whole heap.
	}
	/**
	 * Private helper method to arrange addNodeRef.
	 * @param curDepth depth of current node
	 * @param node node
	 * @return true if it is arranged.
	 */ 
	private boolean findNewAddNodeRef ( Node<E> node, int curDepth ) {		
		if( node == null || curDepth == maxDepth )
			return false;
		if ( curDepth + 1 == maxDepth ) {
			if ( (Node<E>) node.left == null ) {
				addNodeRef = node;
				return true;
			}
			else if ( (Node<E>) node.right == null ) {
				addNodeRef = node;
				return true;
			}
			return false;
		}
		else {
			boolean flag = findNewAddNodeRef( (Node<E>) node.left, curDepth + 1 );
			if( !flag )
				flag = findNewAddNodeRef( (Node<E>) node.right, curDepth + 1 );
			return flag;
		}
	}

	/** 
	 *	Private helper method to update heap structure recursively.
	 *	@param cur current node.
	 */
	@SuppressWarnings("unchecked")
	private void updateNodes ( Node<E> cur ) {
		if ( cur == null || cur.parent == null )
			return;
		int result = compare(cur.data, cur.parent.data);

		if ( result < 0 ) {
			E temp = cur.data;
			cur.data = cur.parent.data;
			cur.parent.data = temp;
		}
		updateNodes( cur.parent );	// go up
	}

	/**
	 * Returns top element of the heap.
	 * @return data, if tree is empty return null.
	 */ 
	public E peek ( ) {
		if ( root == null )
			return null;
		return root.data;
	}

	/**
	 * Returns top element of the heap.
	 * @return top element of the heap.
	 * @throws NoSuchElementException if tree is empty.
	 */ 
	public E element ( ) throws NoSuchElementException {
		E item = peek();
		if( item == null )
			throw new NoSuchElementException("Tree is empty!");
		return item;
	}
	/**
	 * Removes top element of the heap.
	 * @return E returns deleted data.
	 */ 
	public E remove( ) {
		return poll();
	}

	/**
	 * Removes top element of the heap.
	 * @return E returns deleted data.
	 */ 
	public E poll ( ) {
		if ( root == null ) 
			return null;
		else {
			E temp = root.data;
			
			// check right
			if ( removeNodeRef != null && removeNodeRef.right != null ) {
				root.data = removeNodeRef.right.data;
				removeNodeRef.right = null;
			}

			//check left
			else if ( removeNodeRef != null && removeNodeRef.left != null ) {
				root.data = removeNodeRef.left.data;
				removeNodeRef.left = null;
			}
			size--;
			if ( isPerfect() ) maxDepth--;
			if ( size == 0 ) {
				removeNodeRef = null;
				addNodeRef = null;
				root = null;
				maxDepth = 0;
			}
			else {
				//update all values and heap.
				updateNodesFromRoot( (Node<E>) root );
				findNewRemoveNodeRef();
				findNewAddNodeRef();
			}
			return temp;
		}
	}

	/**
	 * Helper private method to update heap from root.
	 * @param cur current node
	 */
	@SuppressWarnings("unchecked")
	private void updateNodesFromRoot ( Node<E> cur ) {
		
		if ( cur == null || (cur.right == null && cur.left == null) )
			return;

		int result = 0;

		if ( cur.right == null && cur.left != null ) {
			result = 1;
		}

		else if ( cur.left == null && cur.right != null ) {
			result = -1;	
		}
		else
			result = compare(cur.right.data, cur.left.data);
		
		if ( result >= 0 
					&& ( compare(cur.data, cur.left.data) > 0 ) ) {
		
			E temp = cur.data;
			cur.data = cur.left.data;
			cur.left.data = temp;
			updateNodesFromRoot( (Node<E>) cur.left );								
		}
		else if ( result <= 0 
					&& ( compare(cur.data, cur.right.data) > 0 ) ) {

			E temp = cur.data;
			cur.data = cur.right.data;
			cur.right.data = temp;
			updateNodesFromRoot( (Node<E>) cur.right );						
		}
	}

	/**
	 * Helper private method to arrange new removeNodeRef.
	 */ 
	private void findNewRemoveNodeRef ( ) {
		if ( isPerfect() ) {
			Node<E> temp = ( Node<E> ) root;
			while ( temp.right != null ) {
				temp = ( Node<E> ) temp.right;
			}
			removeNodeRef = temp.parent;
		}
		else 
			findNewRemoveNodeRef( (Node<E>)root, 1 );	// search all heap.
	}	

	/**
	 * Helper private method to arrange new removeNodeRef recursively.
	 * @param node node
	 * @param curDepth depth of current node
	 */ 
	private boolean findNewRemoveNodeRef ( Node<E> node, int curDepth ) {
		if( node == null || curDepth == maxDepth )
			return false;
		if ( curDepth + 1 == maxDepth ) {
			if ( (Node<E>) node.right != null ) {
				removeNodeRef = node;
				return true;
			}
			else if ( (Node<E>) node.left != null ) {
				removeNodeRef = node;
				return true;
			}
			return false;
		}
		else {
			boolean flag = findNewRemoveNodeRef( (Node<E>) node.right, curDepth + 1 );
			if( !flag )
				flag = findNewRemoveNodeRef( (Node<E>) node.left, curDepth + 1 );
			return flag;
		}		
	}

	/**
	 * Method to check if heap is perfect or not.
	 * @return true if tree is perfect, otherwise returns false.
	 */ 
	public boolean isPerfect ( ) {
		return ( (size+1) & ( size )) == 0;
	}

	/**
	 * Merges two binary heap to calling object.
	 * @param other other heap.
	 * @return LinkedBinaryHeap merged version.
	 */ 
	public LinkedBinaryHeap<E> merge ( LinkedBinaryHeap<E> other ) {
		if ( other != null ) 
			merge ( (Node<E>) other.root );
		return this;
	}

	/**
	 * Helper private method to merge two trees.
	 * @param node node
	 */ 
	private void merge ( Node<E> node ) {
		if ( node != null ) {
			add(node.data);
			merge((Node<E>) node.left);
			merge((Node<E>) node.right);
		}
	}

	/**
	 * Increments given key.
	 * @param key key value
	 * @param val value to be set
	 * @return true if it is incremented, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean increment ( E key, E val ) {
		
		if ( root == null || key == null || val == null) 
			return false;
		
		Node<E> node = find( (Node<E>) root, key);		
		if ( node == null )
			return false;

		int result = compare(val, key);
		if ( result < 0 )
			return false;
		
		node.data = val;
		updateNodesFromRoot(node);
		return true;
	}

	/**
	 * Finds the node which has given key recursively.
	 * @param node node
	 * @param key key value
	 * @return node if it is found, otherwise null.
	 */ 
	protected Node<E> find ( Node<E> node, E key ) {
		if ( node == null )
			return null;
		if ( node.data.equals(key) )
			return node;
		
		Node<E> retval = find( (Node<E>) node.left, key );
		if ( retval == null )
			retval = find( (Node<E>) node.right, key );

		return retval;
	}

	/**
	 * Method to read a binary heap from file
	 * @param scan the Scanner attached to the input file
	 * @return binary heap
	 */
	public static LinkedBinaryHeap<String> readLinkedBinaryHeap ( Scanner scan ) {
		String data = scan.next();
		
		if(data.equals("null"))
			return null;

		else {
			LinkedBinaryHeap<String> leftTree = readLinkedBinaryHeap( scan );
			LinkedBinaryHeap<String> rightTree = readLinkedBinaryHeap( scan );
			return new LinkedBinaryHeap<String>( data, leftTree, rightTree );
		}
	}		
}