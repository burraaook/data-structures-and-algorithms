package HashTableGTU;
import java.lang.StringBuilder;

/**
 * 
 * A class that implements hash table which is hybrid of double and coalesced hashing techniques.
 * @version 1.0 24.04.2022
 * @author Burak Kocausta
 */ 
public class HashTableHybrid<K, V> implements KWHashMap<K, V> {
    
    /**
     * Hash table
     */ 
    private Entry<K, V>[] table;

    /**
     * Initial capacity of the table.
     */    
    private static final int INITIAL_CAPACITY = 11;

    /**
     * Maximum load factor.
     */ 
    private double LOAD_THRESHOLD = 0.5;

    /**
     * Total number of keys.
     */ 
    private int numKeys = 0;
    
    /**
     * Prime num that will be used in hash number calculation.
     */ 
    private int PRIME_NUM = 7;

    /**
     * Constructs an empty table.
     */
    @SuppressWarnings("unchecked")
    public HashTableHybrid() {
        table = new Entry[INITIAL_CAPACITY]; 
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

        int hash1 = key.hashCode() % table.length;
        int hash2 = PRIME_NUM - (key.hashCode() % PRIME_NUM);        
        int index = findHash(hash1, hash2, 1);
        Entry<K, V> temp = table[index];
        while ( temp != null ) {
            if ( key.equals(temp.key) )
                return temp.value;
            temp = temp.next;
        }
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

        int hash1 = key.hashCode() % table.length;
        int hash2 = PRIME_NUM - (key.hashCode() % PRIME_NUM);        
        int index = findHash(hash1, hash2, 1);
        Entry<K, V> ref = table[index];

        if ( ref == null || ref.key == null)
            table[index] = new Entry<K, V>(key, value);
        else {
            int probeCount = 2;
            while ( ref.next != null && !(key.equals(ref.key)) ) {
                probeCount++;
                ref = ref.next;
            }
            // check if same key founded.
            if ( key.equals(ref.key) ) {
                V retVal = ref.getValue();
                ref.setValue(value);
                return retVal;                    
            }
            int newIndex = findHash(hash1, hash2, probeCount);
            ref.next = new Entry<K, V>(key, value);

            // calculate new hash till empty place founded.
            while ( table[newIndex] != null && table[newIndex].key != null )
               newIndex = findHash(hash1, hash2, ++probeCount);
            table[newIndex] = ref.next;
        }
        numKeys++;
        double loadFactor = (double) numKeys / table.length;
        if (loadFactor > LOAD_THRESHOLD)
            rehash();
        return null;
    }
   
    /**
     * Private rehashing method to rearrange the hash table. 
     */
    @SuppressWarnings("unchecked")
    private void rehash ( ) {
        Entry<K, V>[] oldTable = table;
        table = new Entry[2 * oldTable.length + 1];
        numKeys = 0;
        updatePrime();        
        for ( int i = 0; i < oldTable.length; i++ )
            if ( oldTable[i] != null && oldTable[i].key != null ) 
                put(oldTable[i].getKey(), oldTable[i].getValue());
    }

    /**
     * Returns a proper index with given hash values.
     * @param hash1 hash2
     * @param hash2 hash1
     * @param probeNum probe number
     * @return proper index
     */  
    private int findHash ( int hash1, int hash2, int probeNum ) {
        int index = (hash1 + (probeNum * hash2)) % table.length;
        if (index < 0)
            index += table.length;

        return index;        
    }

    /**
     * Removes the pair according to the given key.
     * @param key key
     * @return the deleted object.
     */     
    @Override
    public V remove ( Object key ) {
        if ( key == null )
            return null;

        int hash1 = key.hashCode() % table.length;
        int hash2 = PRIME_NUM - (key.hashCode() % PRIME_NUM);        
        int index = findHash(hash1, hash2, 1);

        if ( table[index] == null || table[index].key == null )
            return null;
        else if ( ( table[index].next == null || table[index].next.key == null ) 
                    && key.equals(table[index].key) ) {
            V retVal = table[index].value;
            table[index] = null;
            numKeys--;
            return retVal;
        }

        // if element founded with 0 probe.
        if ( key.equals(table[index].key) ) {
            // swap with the next element
            Entry<K, V> pre = table[index];
            Entry<K, V> pos = pre.next;
            V retVal = table[index].value;

            while ( pos.next != null ) {
                pos = pos.next;
                pre = pre.next;
            }
            table[index].value = pos.value;
            table[index].key = pos.key;
            pos.key = null; // set it to dummy
            pre.next = null;
            numKeys--;
            return retVal;
        }
        else {
            // single linked list removal
            Entry<K, V> pre = table[index];
            Entry<K, V> pos = pre.next;

            // traverse the elements
            while ( pos != null && !(key.equals(pos.key)) ) {
                pre = pos;
                pos = pos.next;
            }
            if ( pos == null )
                return null;
            pos.key = null;     // set it to dummy
            pre.next = pos.next;
            V retVal = pos.value;
            numKeys--;
            return retVal;            
        }
    }

    /**
     * Updates the prime value to the next.
     */ 
    private void updatePrime ( ) {
        PRIME_NUM = (table.length * 4) / 5;
        while ( !(isPrime(PRIME_NUM)) ) {
            PRIME_NUM--;
        }
    }

    /**
     * Checks if given num is prime or not.
     * pre: 2 is not counted as prime.
     * @param num number
     */ 
    private boolean isPrime ( int num ) {
        if ( num % 2 == 0 )
            return false;
        for ( int i = 3; i < num/2 + 1; i += 2 ) {
            if ( num % i == 0 )
                return false;
        }
        return true;
    }

    /**
     * Returns the total number of keys.
     * @return total number of keys.
     */ 
    @Override
    public int size ( ) {
        return numKeys;
    }

    /**
     * Checks if table is empty or not.
     * @return true if it is empty, false otherwise.
     */ 
    @Override
    public boolean isEmpty ( ) {
        return (numKeys == 0); 
    }

    /**
     * Returns String representation of the table.
     * @return String representation of the table.
     */ 
    @Override
    public String toString ( ) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < table.length; ++i ) {
            sb.append( i + ": ");
            if ( table[i] == null || table[i].key == null ) {
                sb.append("null\n");
                continue;
            }
            sb.append("(key = " + table[i].key + ", value = " + table[i].value + ", next key = ");
            if ( table[i].next == null )
                sb.append("null)\n");
            else
                sb.append(table[i].next.key + ")\n");
        }
        return sb.toString();
    }

    /**
     * An Entry class to hold key and value. It also points to the next element.
     * @version 1.0  23.04.2022
     * @author Burak Kocausta
     */ 
    private static class Entry<K, V> {
        
        /**
         * Key of value.
         */ 
        private K key;
        /**
         * Data to hold.
         */ 
        private V value;

        /**
         * Next element which has validated to the same index.
         */ 
        private Entry<K,V> next = null;
        
        /**
         * Constructs a new entry.
         * @param key key of the pair.
         * @param value value of the pair.
         */ 
        public Entry ( K key, V value ) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
        
        /**
         * Getter for key.
         * @return key
         */ 
        public K getKey() {
            return key;
        }
        
        /**
         * Getter for value.
         * @return value
         */ 
        public V getValue() {
            return value;
        }

        /**
         * Getter for next.
         * @return next
         */ 
        public Entry<K, V> getNext ( ) {
            return next;
        }

        /**
         * Sets the value.
         * @param val value to set.
         * @return old value.
         */ 
        public V setValue ( V val ) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        /**
         * Sets the next entry.
         * @param entry entry to set.
         */ 
        public void setNext ( Entry<K, V> entry ) {
            next = entry;
        }
    }

}