package BinaryHeapGTU;
import java.io.*;
import java.util.Scanner;

/**
 * Binary Tree class which is implemented with node link structure.
 * @author Burak Kocausta
 * @version 1.0 08.04.2022
 */
@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable {	
	
	/**
	 * Root of the tree.
	 */ 
	protected Node<E> root;
	
	/**
	 * Class for tree node
	 * @author Burak Kocausta
	 * @version 1.0 09.04.2022
	 */
	@SuppressWarnings("serial")
	protected static class Node<E> implements Serializable {
		
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
	 * 
	 * Creates Tree with given root.
	 * @param root node
	 */ 
	protected BinaryTree ( Node<E> root ) {
		this.root = root;
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
			return new BinaryTree<E>(null);
	}
	
	/**
	 * Returns right subtree
	 * @return BinaryTree which is right of this tree, returns null if right is null.
	 */
	public BinaryTree<E> getRightSubtree ( ) {
		if( root != null && root.right != null )
			return new BinaryTree<E>(root.right);
		else 
			return new BinaryTree<E>(null);
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
	 * Method to read a binary tree from file
	 * @param scan Scanner
	 * @return the binary tree read from file
	 */
	public static BinaryTree<String> readBinaryTree ( Scanner scan ) {
		String data = scan.next();
		
		if(data.equals("null"))
			return null;

		else {
			BinaryTree<String> leftTree = readBinaryTree( scan );
			BinaryTree<String> rightTree = readBinaryTree( scan );
			return new BinaryTree<String>( data, leftTree, rightTree );
		}
	}
}