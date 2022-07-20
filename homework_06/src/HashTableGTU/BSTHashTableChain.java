package HashTableGTU;

import BinarySearchTreeGTU.*;
import java.util.Iterator;
import java.lang.StringBuilder;

/**
 * 
 * Class implements KWHashMap, and uses binary search trees for holding (K, V) objects. 
 * @version 1.0 23.04.2022
 * @author Burak Kocausta
 */
public class BSTHashTableChain<K extends Comparable<K>, V> implements KWHashMap<K, V> {

    /**
     * Hash table
     */ 
    private BinarySearchTree<Entry<K, V>>[] table;

    /**
     * Total number of keys.
     */ 
    private int numKeys = 0;

    /**
     * Initial capacity of the array.
     */ 
    private static final int INITIAL_CAPACITY = 11;

    /**
     * Maximum load factor.
     */ 
    private static final double LOAD_THRESHOLD = 3.0;

    /**
     * Creates an empty table.
     */
    @SuppressWarnings("unchecked")
    public BSTHashTableChain ( ) {
        table = new BinarySearchTree[INITIAL_CAPACITY];
    }

    /**
     * Returns total number of the keys.
     * @return size of non-null elements of the table.
     */ 
    @Override
    public int size ( ) {
        return numKeys;
    }

    /**
     * Checks if table is empty or not.
     * @return true if table is empty, otherwise false.
     */ 
    @Override
    public boolean isEmpty ( ) {
        return (numKeys == 0); 
    }

    /**
     * Returns the value according to the key.
     * @return value of the given key, if key does not exists, returns null.
     * @param key key to access value.
     */ 
    @Override
    public V get ( Object key ) {
        if ( key == null )
            return null;

        int index = key.hashCode() % table.length;
        
        if ( index < 0 )
            index += table.length;
        
        if ( table[index] == null )
            return null;

        // traverse using iterator
        for ( Entry<K, V> nextItem : table[index] ) 
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        
        return null;
    }

    /**
     * Puts the given pair to the table, if key exists sets the new value.
     * @param key key
     * @param value value
     * @return if key is new returns null, otherwise returns old value.
     */ 
    @Override
    public V put ( K key, V value ) {
        if ( key == null || value == null )
            return null;

        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            table[index] = new BinarySearchTree<Entry<K, V>>();
        
        // traverse the tree using iterator
        for (Entry<K, V> nextItem : table[index]) {

            // replace the value if it exists
            if (nextItem.getKey().equals(key)) {
                V retVal = nextItem.getValue();
                nextItem.setValue(value);
                return retVal; 
            }
        }

        table[index].add(new Entry<K, V>(key, value));
        numKeys++;  // increment number of keys

        // check load factor
        if (numKeys > (LOAD_THRESHOLD * table.length))
            rehash();
        return null;
    }

    /**
     * Private rehashing method to rearrange the hash table. 
     */
    @SuppressWarnings("unchecked")
    private void rehash ( ) {
        // Save a reference to oldTable.
        BinarySearchTree<Entry<K,V>>[] oldTable = table;
        // Double capacity of this table.
        table = new BinarySearchTree[2 * oldTable.length + 1];

        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null)) {

                // reinsert using iterator.
                Iterator<Entry<K,V>> itr = oldTable[i].iterator();
                while ( itr.hasNext() ) {
                    Entry<K,V> nextItem = itr.next();
                    put(nextItem.getKey(), nextItem.getValue());
                }
            }
        }
    }

    /**
     * Removes the pair according to the given key.
     * @param key key
     * @return the deleted object.
     */ 
    @Override
    @SuppressWarnings("unchecked")
    public V remove ( Object key ) {
        if ( key == null )
            return null;

        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;
        if (table[index] == null)
            return null;
        
        try {
            K castedKey = (K) key;
            Entry<K, V> retVal = table[index].delete(new Entry<K, V>(castedKey, null));
            if ( retVal != null ) {
                numKeys--;
                // if it is empty set it to null.
                if( table[index].isEmpty() )
                    table[index] = null;
                return retVal.value;            
            }    
            return null;              
        }
        catch(Exception e) {
            return null;
        }    
    }

    /**
     * Returns String representation of the table.
     * @return String representation of the table.
     */ 
    @Override
    public String toString ( ) {
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < table.length; ++i ) {
            sb.append(i + ":\n"); 
            if ( table[i] == null )
                sb.append("null\n");
            else {

                sb.append(table[i]);
            /*
                for (Entry<K, V> nextItem : table[i]) {
                    sb.append("(" + nextItem.key + ", " + nextItem.value + ")");
                    if ()
                }
                sb.append("\n"); */
            }
            sb.append("------\n");
        }
        return sb.toString();
    }

    /**
     * A comparable Entry class to be an element of binary search tree.
     * @version 1.0  23.04.2022
     * @author Burak Kocausta
     */ 
    private static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
        
        /**
         * Key of the entry.
         */ 
        private final K key;

        /**
         * The data to hold.
         */ 
        private V value;

        /**
         * Constructs a new entry.
         * @param key key of the pair.
         * @param value value of the pair.
         */ 
        public Entry ( K key, V value ) {
            this.key = key;
            this.value = value;
        }

        /**
         * Getter for key.
         * @return key
         */ 
        public K getKey ( ) {
            return key;
        }

        /**
         * Getter for value.
         * @return value
         */ 
        public V getValue ( ) {
            return value;
        }

        /**
         * Sets the value.
         * @param val value to set.
         * @return old value.
         */ 
        public V setValue ( V val ) {
            V retVal = value;
            value = val;
            return retVal;
        }

        /**
         * Compares only the keys.
         * @return result of the comparison
         */ 
        @Override
        public int compareTo ( Entry<K,V> other ) {
            Comparable<K> ckey = (Comparable<K>) key;
            return ckey.compareTo(other.key);
        }

        /**
         * String format of the entry.
         * @return String format of the entry.
         */ 
        @Override
        public String toString ( ) {
            return String.format("(" + key + ", " + value + ")");
        }

        /**
         * Only compares the keys.
         * @return true if keys are equal, false otherwise.
         */ 
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals ( Object other ) {
            if ( other != null ) {
                try {
                    Entry<K, V> otherEntry = (Entry<K, V>) other;
                    if ( key.equals(otherEntry.key) )
                        return true;                    
                }
                catch ( Exception e ) {
                    return false;
                }
            }
            return false;
        }

        /**
         * Returns hashCode of the key.
         * @return hashCode of the key.
         */ 
        @Override
        public int hashCode ( ) {
            return key.hashCode();
        }
    }    
}