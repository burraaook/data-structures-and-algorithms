package BinarySearchTreeGTU;
import SortGTU.*;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Binary Tree class which is implemented with node link structure, and it is also iterable.
 * @author Burak Kocausta
 * @version 1.2 14.05.2022
 */
public class BinaryTree<E> implements Iterable<E>{	
	
	/**
	 * Root of the tree.
	 */ 
	protected Node<E> root;
	
	/**
	 * Class for tree node
	 * @author Burak Kocausta
	 * @version 1.0 09.04.2022
	 */
	protected static class Node<E>{
		
		/**
		 * data to be stored.
		 */ 
		protected E data;
		
		/**
		 * left child.
		 */ 
		protected Node<E> left;

		/**
		 * right child.
		 */  
		protected Node<E> right;
		
		/**
		 * Construct a node, assign null to childrens.
		 * @param data data
		 */
		public Node ( E data ) {
			this.data = data;
			left = null;
			right = null;
		}
		
		/**
		 * Return String representation of data
		 * @return String
		 */
		public String toString ( ) {
			return data.toString();
		}
	}

	/**
	 * No parameter constructor creates null root
	 */ 
	public BinaryTree ( ) {
		root = null;
	}
	
	/**
	 * Creates binary tree with given item
	 * @param item item
	 */ 
	public BinaryTree ( E item ) {
		root = new Node<E>(item);
	}

	/**
	 * 
	 * Creates Tree with given root.
	 * @param root node
	 */ 
	protected BinaryTree ( Node<E> root ) {
		this.root = root;
	}

	/**
	 * Returns an iterator which has next, hasNext methods.
	 * @return iterator
	 */ 
	public Iterator<E> iterator ( ) {
		return new BTIterator();
	}

	/**
	 * Constructor using left and right trees.
	 * @param data data
	 * @param leftTree left subtree
	 * @param rightTree right subtree
	 */
	public BinaryTree ( E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree ) {
		root = new Node<E>(data);
		if( leftTree != null )
			root.left = leftTree.root;
		else
			root.left = null;
		
		if( rightTree != null )
			root.right = rightTree.root;
		else 
			root.right = null;
	}
	
	/**
	 * Determine whether this tree is a leaf
	 * @return true if the root has no children
	 */
	public boolean isLeaf ( ) {
		return root == null || (root.left == null && root.right == null);
	}
		
	/**
	 * Returns left subtree
	 * @return BinaryTree which is left of this tree, returns null if left is null.
	 */
	public BinaryTree<E> getLeftSubtree ( ) {
		if( root != null && root.left != null )
			return new BinaryTree<E>(root.left);
		else
			return new BinaryTree<E>();
	}
	
	/**
	 * Returns right subtree
	 * @return BinaryTree which is right of this tree, returns null if right is null.
	 */
	public BinaryTree<E> getRightSubtree ( ) {
		if( root != null && root.right != null )
			return new BinaryTree<E>(root.right);
		else 
			return new BinaryTree<E>();
	}
	
	/**
	 * Returns the pre order traversed version of the tree as a string.
	 * @return string representation of tree.
	 */ 
	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * Private method to perform preorder traversal.
	 * @param node node
	 * @param depth depth of current node
	 * @param sb String buffer
	 */
	private void preOrderTraverse ( Node<E> node, int depth, StringBuilder sb ) {
		for( int i = 1; i < depth; i++ )
			sb.append("  ");
		
		if(node == null)
			sb.append("null\n");
		else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	/**
	 * Method which converts binary tree to binary search tree with given data.
	 * pre: number of elements must be same, and array's elements must be unique
	 * @param <T> a comparable type
	 * @param binaryTree binary tree
	 * @param arr array which has unique elements
	 * @return binary search tree
	 */ 
	public static <T extends Comparable<T>> BinarySearchTree<T> convertToSearchTree(BinaryTree<T> binaryTree, T[] arr) {

		// sort the array
		MergeSort.sort(arr);
		setTreeInorderly(binaryTree.root, arr, 0);
		BinarySearchTree<T> bst = new BinarySearchTree<T>();

		// traverse with iterator for the bst class version
		for(T data: binaryTree) {
			bst.add(data);
		}
		return bst;
	}

	/**
	 * Private helper method to traverse and set the tree.
	 * @param localRoot local root
	 * @param arr array which has unique elements
	 * @param cur current index of array
	 * @return binary search tree
	 */ 
	private static <T extends Comparable<T>> int setTreeInorderly(Node<T> localRoot, T[] arr, int cur) {
		
		if ( cur >= arr.length || localRoot == null )
			return cur;

		cur = setTreeInorderly(localRoot.left, arr, cur);
		localRoot.data = arr[cur];
		return setTreeInorderly(localRoot.right, arr, cur+1);
	}

	/**
	 * 
	 * An iterator class which iterator through tree preorderly.
	 * @version 1.0 23.04.2022
	 * @author Burak Kocausta
	 */ 
	private class BTIterator implements Iterator<E> {

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
		public BTIterator ( ) {
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