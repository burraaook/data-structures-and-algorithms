package HashTableGTU;

/**
 * 
 * Interface of Hash Map provides necessary methods for different kinds of hash structure.
 * @version 1.0 22.04.2022
 */ 
public interface KWHashMap<K, V> {

    /**
     * Checks if table is empty or not.
     * @return true if table is empty, otherwise false.
     */ 
	boolean isEmpty ( );
    
    /**
     * Returns total number of the keys.
     * @return size of non-null elements of the table.
     */ 
	int size ( );

    /**
     * Puts the given pair to the table, if key exists sets the new value.
     * @param key key
     * @param value value
     * @return if key is new returns null, otherwise returns old value.
     */ 
	V put ( K key, V value );

    /**
     * Returns the value according to the key.
     * @return value of the given key, if key does not exists, returns null.
     * @param key key to access value.
     */ 
	V get ( Object key );

    /**
     * Removes the pair according to the given key.
     * @param key key
     * @return the deleted object.
     */ 
	V remove ( Object key );
}