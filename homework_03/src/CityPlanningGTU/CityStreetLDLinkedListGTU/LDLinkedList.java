package CityStreetLDLinkedListGTU;
import CityBuildingGTU.*;
import java.util.*;


/**
 * A class that extends AbstractList. LD means lazy deletion. This is similar class of like LinkedList, but it has lazy deletion strategy.
 * @author Burak Kocausta
 * @version 1.0 20.03.2022
 */ 
public class LDLinkedList<E> extends AbstractList<E> implements List<E> {

	//nodes
	private Node<E> head = null;
	private Node<E> tail = null;
	private Node<E> deletedHead = null;	// head of the deleted nodes.

	private int size = 0;

	/**
	 * Returns an Iterator at the head of the list
	 * @return An Iterator at the first index
	 */
	@Override
	public Iterator<E> iterator ( ) {
		return new LDListIterator(0);
	}

	/**
	 * Returns a ListIterator at the head of the list
	 * @return a ListIterator at the first index
	 */
	@Override	
	public ListIterator<E> listIterator ( ) {
		return new LDListIterator(0);
	}

	/**
	 * Returns a ListIterator at given index
	 * @param i The index of the list iterator
	 * @return A list iterator at given position of the list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public ListIterator<E> listIterator(int i)
	{
		return new LDListIterator(i);
	}

	/**
	 * removes all the elements of the list.
	 */ 	
	@Override
	public void clear ( ) {
		ListIterator<E> itr = this.listIterator();
		while ( itr.hasNext() ) {
			itr.next();
			itr.remove();
		}
	}


	/**
	 * adds given element at specified index.
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */ 
	@Override
	public void add ( int index, E element ) {
		this.listIterator(index).add(element);
	}

	/**
	 * adds element to the end of the list.
	 */ 
	public boolean add ( E element ) {
		this.addLast(element);
		return true;
	}

	/**
	 * adds element to the head of the list.
	 * @param element element to be added
	 */ 
	public void addFirst ( E element ) {
		this.listIterator().add(element);
	}

	/**
	 * adds element to the tail of the list.
	 * @param element element to be added
	 */ 
	public void addLast ( E element ) {
		this.listIterator(size).add(element);
	}

	/**
	 * getter to get element at given position.
	 * @param index position of element.
	 * @return returns the object at given index.
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */ 
	@Override
	public E get(int index)
	{
		return this.listIterator(index).next();
	}

	/**
	 * Replaces the element at the specified position.
	 * @param index position of element.
	 * @param element setting element to given place.
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */ 	
	@Override
	public E set ( int index, E element ) {
		if ( index < 0 || index >= size() )
			throw new IndexOutOfBoundsException();

		ListIterator<E> itr = this.listIterator(index);
		E tool = itr.next();
		itr.set(element);
		return tool;
	}

	/**
	 * Returns last element.
	 * @return returns last element.
	 */
	public E getLast ( )
	{
		return this.get(size-1);
	}
	
	/**
	 * Returns first element.
	 * @return returns first element.
	 */
	public E getFirst ( ) {
		return this.get(0);
	}

	/**
	 * returns size of the list.
	 * @return size of the list as an int.
	 */ 
	@Override
	public int size ( ) {
		return this.size;
	}

	/**
	 * Checks if given object is in the list.
	 * @param o object that wanted to be checked.
	 * @return true if it is in the list, false otherwise.
	 */ 
	public boolean contains ( Object o ) {

		if ( o == null ) return false;
		else {
			ListIterator<E> itr = this.listIterator(0);
			while ( itr.hasNext() ) {
				E obj = itr.next();
				if ( obj.equals(o) )
					return true;
			}
		}
		return false;
	}

	/**
	 * Removes the given object from the list.
	 * @param o object that wanted to be removed.
	 * @return true if it is removed successfully, false otherwise.
	 */ 	
	@Override
	public boolean remove ( Object o ) {

		if ( o == null ) return false;
		else {
			ListIterator<E> itr = this.listIterator(0);
			while ( itr.hasNext() ) {
				E obj = itr.next();
				if ( obj.equals(o) ) {
					itr.remove();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes the given object from the list.
	 * @param index object that wanted to be removed.
	 * @return true if it is removed successfully, false otherwise.
	 */ 
	@Override
	public E remove ( int index ) {

		if ( index < 0 || index >= size() )
			throw new IndexOutOfBoundsException();

		ListIterator<E> itr = this.listIterator(index);
		E tool = itr.next();
		itr.remove();
		return tool;
	}


	/**
	 * A static class to represent node.
	 */ 
	private static class Node<E> {

		private E data;
		private Node<E> next = null;
		private Node<E> prev = null;

		/**
		 * Data constructor.
		 * @param other given data.
		 */ 
		private Node ( E other ) {
			data = other;
		}

		/**
		 * Holds data and points to next node.
		 * @param other given data
		 * @param other next node
		 */ 
		private Node ( E other, Node<E> otherNode ) {
			data = other;
			next = otherNode;
		}
	}


	/**
	 * 
	 * An iterator class which implements ListIterator. It makes lazy deletion.
	 */ 
	private class LDListIterator implements ListIterator<E> {
		private Node<E> nextItem;
		private Node<E> lastItemReturned;
		private int index = 0;

		/**
		 * An iterator at given index.
		 * @throws IndexOutOfBoundsException if index exceeds the border.
		 */ 
		public LDListIterator ( int i ) throws IndexOutOfBoundsException {
			if ( i < 0 || i > size )
				throw new IndexOutOfBoundsException(i);

			lastItemReturned = null;

			if ( i == size ) {
				index = size;
				nextItem = null;
			}

			else {
				nextItem = head;
				index = 0;
				while ( index != i ) {
					nextItem = nextItem.next;
					index++;
				}
			}
		}

		/**
		 * Checks if there is next item.
		 * @return true if next item is not null
		 */ 
		@Override
		public boolean hasNext ( ) {
			return nextItem != null;
		}

		/**
		 * Returns the next items data and increments index.
		 * @return returns the next items data.
		 * @throws NoSuchElementException if there is no next item.
		 */ 
		public E next ( ) throws NoSuchElementException {
			if ( !hasNext() )
				throw new NoSuchElementException();

			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.data;
		}

		/**
		 * Checks if there is previous item.
		 * @return true if previous item is not null
		 */ 		
		@Override
		public boolean hasPrevious ( ) {
			if ( size == 0 ) return false;

			return nextItem == null || nextItem.prev != null;
		}

		/**
		 * Returns the previous items data and increments index.
		 * @return returns the previous items data.
		 * @throws NoSuchElementException if there is no previous item.
		 */ 
		@Override
		public E previous ( ) throws NoSuchElementException {

			if ( !hasPrevious() )
				throw new NoSuchElementException();
			if ( nextItem == null ) 
				nextItem = tail;		
			else 
				nextItem = nextItem.prev;
			
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.data;
		}

		/**
		 * Adds element to list.
		 * @param other element which is inserted.
		 */ 
		@Override
		public void add ( E other ) {
			if ( head == null ) {	
				if ( deletedHead == null ){
					head = new Node<E>(other);
					tail = head;
				}
				else {	// make lazy deletion
					Node<E> otherNode = deletedHead;
					deletedHead = deletedHead.next;
					head = otherNode;
					tail = head;
					otherNode.next = null;
					otherNode.data = other;
				}
				
			}

			// head
			else if ( nextItem == head ) {

				if ( deletedHead == null ) {
					Node<E> otherNode = new Node<E>(other);
					otherNode.next = nextItem;
					nextItem.prev = otherNode;
					head = otherNode;					
				}
				else {	// make lazy deletion
					Node<E> otherNode = deletedHead;
					deletedHead = deletedHead.next;
					otherNode.next = nextItem;
					nextItem.prev = otherNode;
					head = otherNode;
					otherNode.data = other;		
				}

			}

			// tail
			else if ( nextItem == null ) {

				if ( deletedHead == null ) {
					Node<E> otherNode = new Node<E>(other);
					tail.next = otherNode;
					otherNode.prev = tail;
					tail = otherNode;					
				}
				else { // make lazy deletion
					Node<E> otherNode = deletedHead;
					deletedHead = deletedHead.next;
					tail.next = otherNode;
					otherNode.prev = tail;
					tail = otherNode;
					otherNode.next = null;
					otherNode.data = other;					
				}


			}

			// middle
			else {	

				if ( deletedHead == null ) {
					Node<E> otherNode = new Node<E>(other);
					otherNode.next = nextItem;
					nextItem.prev.next = otherNode;
					otherNode.prev = nextItem.prev;
					nextItem.prev = otherNode;					
				}
				else { // make lazy deletion
					Node<E> otherNode = deletedHead;
					deletedHead = deletedHead.next;
					otherNode.next = nextItem;
					nextItem.prev.next = otherNode;
					otherNode.prev = nextItem.prev;
					nextItem.prev = otherNode;	
					otherNode.data = other;			
				}

			}
			size++;
			index++;
			lastItemReturned = null;
		}


		/**
		 * Removes element from list.
		 * @throws IllegalStateException if last returned item is null.
		 */ 
		@Override
		public void remove ( ) throws IllegalStateException {

			if ( lastItemReturned == null )
				throw new IllegalStateException();

			// check if last call is previous()
			if ( nextItem == lastItemReturned ) {

				if ( lastItemReturned == head ) {
					nextItem = head.next;
					head = nextItem;
				}
				else if ( lastItemReturned == tail ) {
					tail = tail.prev;
					tail.next = null;
					nextItem = null;
				}
				else {
					lastItemReturned.prev.next = lastItemReturned.next;
					nextItem.next.prev = lastItemReturned.prev;
					nextItem = nextItem.next;
				}
			}

			// check if last call is next()
			else {
				if ( lastItemReturned == head ) {
					head.prev = null;
					head = nextItem;
				}
				else if ( lastItemReturned == tail ) {
					tail = tail.prev;
					tail.next = null;
				}
				else {
					lastItemReturned.prev.next = nextItem;
					nextItem.prev = lastItemReturned.prev;
				}
				index--;
			}
			Node<E> copy = lastItemReturned;		// save it to the deleted nodes for future use.
			copy.next = deletedHead;
			deletedHead = copy;
			copy.prev = null;
			lastItemReturned = null;
			size--;
		}

		/**
		 * Returns previous index. -1 if it is at the beginning.
		 * @return previous index as an int.
		 */ 
		@Override
		public int previousIndex() {
			return index-1;
		}

		/**
		 * Returns next index.
		 * @return next index as an int.
		 */ 
		@Override
		public int nextIndex() {
			return index;
		}

		/**
		 * Sets the given data.
		 * @param other data to set.
		 * @throws IllegalStateException if last returned item is null.
		 */ 
		@Override
		public void set ( E other ) {
			if ( lastItemReturned == null )
				throw new IllegalStateException();
			lastItemReturned.data = other;
		}				
	}
}