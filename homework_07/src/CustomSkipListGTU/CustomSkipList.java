package CustomSkipListGTU;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Custom Skip List implementation which does not flip coin(completely) while insertion, it uses probability operation.
 * @version 1.0 14.05.2022
 */ 
public class CustomSkipList<E extends Comparable<E>> {
	/**
	 * head reference of the list
	 */
	public CSLNode<E> head;
	/**
	 * size of the list
	 */
	private int size;

	/**
	 * maximum level
	 */
	private int maxLevel;

	/**
	 * maximum capacity
	 */ 
	private int maxCap = 10;
	
	/**
	 * Constructs 4 leveled skip list.
	 */ 
	public CustomSkipList ( ) {
		size = 0;
		maxLevel = 4;
		head = new CSLNode<E>(maxLevel, null);
	}

	/**
	 * Finds and returns given target's reference.
	 * @param target item
	 * @return reference of wanted item.
	 */
	public E find ( E target ) {
		CSLNode<E>[] pred = search(target);
		if ( pred[0].links != null && pred[0].links[0].data != null && pred[0].links[0].data.compareTo(target) == 0 )
			return pred[0].links[0].data;
		return null;
	}
	
	/**
	 * Private helper method, which returns predecessor array.
	 * @param target item
	 * @return predecessor array.
	 */
	@SuppressWarnings("unchecked")
	private CSLNode<E>[] search ( E target ) {
		CSLNode<E>[] pred = (CSLNode<E>[]) new CSLNode[maxLevel];
		CSLNode<E> cur = head;
		for ( int i = cur.links.length - 1; i >= 0; i-- ) {

			while( cur.links[i] != null && cur.links[i].data.compareTo(target) < 0 )
				cur = cur.links[i];
			pred[i] = cur;
		}
		return pred;
	}
	
	/**
	 * Inserts given item to the list.
	 * @param item item
	 * @return true
	 */
	public boolean add ( E item ) {
		size++;
		CSLNode<E>[] pred = search(item);
		if ( size > maxCap ) {

			maxLevel++;
			maxCap = size + 10;
			head.links = Arrays.copyOf(head.links, maxLevel);
			pred = Arrays.copyOf(pred, maxLevel);
			pred[maxLevel - 1] = head;
			incrementLevels();	// increment the tall item's levels
		}

		int left = 0;
		int right = 0;
		CSLNode<E> cur = pred[1];

		// count left
		while ( cur != null && (cur != head && cur.data.compareTo(item) < 0) ) {
			left++;
			cur = cur.links[0];
		}			

		// count right
		if ( pred[1].links[1] != null ) {
			cur = pred[0];
			while ( cur != null && (cur != head && cur.data.compareTo(pred[1].links[1].data) != 0) ) {
				right++;
				cur = cur.links[0];
			}		
		}				
		int level = getLevel(left, right);
		CSLNode<E> newNode = new CSLNode<E>(level, item);
		
		for ( int i = 0; i < newNode.links.length; i++ ) {
			newNode.links[i] = pred[i].links[i];
			pred[i].links[i] = newNode;
		}

		return true;
	}
	
	/**
	 * Returns a level, which calculated with probability.
	 * @param right right count
	 * @param left left count
	 * @return level
	 */ 
	private int getLevel ( int left, int right ) {
		if ( size == 1 )
			return 2;		
		if (left == 0 && right == 0)
			return 1;

		int level = 1;

		int[] arr = new int[10];

		int chance = left + right;
		int i = 0;
		while ( i < chance && i < arr.length ) {
			arr[i] = 1;
			++i;
		}

		while ( level < maxLevel ) {
			Random rand = new Random();			
			int index = rand.nextInt(arr.length);
			if ( arr[index] == 1 )
				level++;
			else
				return level;			
		}
		return level;
	}

	/**
	 * Increments the levels except level 0.
	 */ 
	private void incrementLevels ( ) {
		
		for ( int i = maxLevel - 2; i >= 1; --i ) {
			CSLNode<E> pre = head;
			CSLNode<E> pos = head.links[i];
			

			while ( pos != null ) {
				
				if ( pos.links.length == i + 1 ) {
					pos.links = Arrays.copyOf(pos.links, pos.links.length + 1);
					pos.links[i+1] = pre.links[i+1];
					pre.links[i+1] = pos;					
				}
				pos = pos.links[i];
				pre = pre.links[i+1];
			}
		}
	}

	/**
	 * toString method for skip list.
	 * @return String representation of skip list.
	 */
	@Override
	public String toString ( ) {
		if(size == 0)
			return "Empty Skip List";
		
		StringBuilder sb = new StringBuilder();
		
		CSLNode<E> node = head;
		
		sb.append("Maximum Level = " + maxLevel + ", size = " + size + "\n");
		int i = 0;
		
		while ( node.links[0] != null ) {

			node = node.links[0];
			sb.append(", " + node.toString());
			i++;
			
			if ( i == 3 ) {
				sb.append("\n");
				i = 0;
			}
		}
		return sb.toString();
	}
	
	/**
	 * Node class for CustomSkipList, holds a link array and data.
	 */ 
	protected static class CSLNode<E> {

		/**
		 * links
		 */ 
		protected CSLNode<E>[] links;
		
		/**
		 * data to hold
		 */ 
		protected E data;
		
		/**
		 * Creates a node with given level.
		 * @param lev level
		 * @param data data
		 */
		@SuppressWarnings("unchecked")
		public CSLNode ( int lev, E data ){
			links = (CSLNode<E>[]) new CSLNode[lev];
			this.data = data;
		}
		
		/**
		 * toString method for node class.
		 * @return string representation of node
		 */
		@Override
		public String toString ( ) {
			return ("(Level = " + links.length + ", data = [" + data.toString() + "])"); 
		}
	}
}
