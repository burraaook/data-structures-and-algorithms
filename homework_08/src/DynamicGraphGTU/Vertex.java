package DynamicGraphGTU;

import java.util.HashMap;

/**
 * 
 * A general vertex class which can be weighted, and holds label, id, set of pairs.
 * @author Burak Kocausta
 * @version 1.0 27.05.2022
 */ 
public class Vertex {

	/**
	 * ID of the vertex.
	 */ 
	private int id;

	/**
	 * Label of the vertex.
	 */ 
	private String label;

	/**
	 * Weight of the vertex.
	 */ 
	private double weight;

	/**
	 * Pairs of the vertex.
	 */ 
	private HashMap<String, String> pairs = new HashMap<String, String>();

	/**
	 * Indicates unweighted vertex.
	 */ 
	private static final double UNWEIGHTED_VERTEX = 1.0;

	/**
	 * Constructs a vertex with given parameters.
	 * @param id id of the vertex
	 * @param label label of the vertex
	 * @param weight weight of the vertex
	 */ 
	public Vertex ( int id, String label, double weight ) {
		this.id = id;
		this.label = label;
		this.weight = weight;
	}

	/**
	 * Constructs unweighted vertex.
	 * @param id id of the vertex
	 * @param label label of the vertex
	 */ 
	public Vertex ( int id, String label ) {
		this.id = id;
		this.label = label;
		this.weight = UNWEIGHTED_VERTEX;
	}

	/**
	 * Getter for id of the vertex.
	 * @return id
	 */ 
	public int getID ( ) {
		return id;
	}

	/**
	 * Getter for weight of the vertex.
	 * @return weight
	 */ 
	public double getWeight ( ) {
		return weight;
	} 

	/**
	 * Getter for label of the vertex.
	 * @return label
	 */ 
	public String getLabel ( ) {
		return label;
	}

	/**
	 * Returns hash code for vertex.
	 * @return hash code
	 */ 
	@Override
	public int hashCode ( ) {
		return Integer.hashCode(id);
	}

	/**
	 * Add given pair to the vertex.
	 * @param key key
	 * @param val value
	 * @return vertex itself
	 */ 
	public Vertex addPair ( String key, String val ) {
		pairs.put(key, val);
		return this;
	}

	/**
	 * Checks if given pair is in the vertex.
	 * @param key key
	 * @param val value
	 * @return true if it is in the pair, false otherwise.
	 */
	public boolean checkPair ( String key, String val ) {
		String value = pairs.get(key);

		if ( value != null )
			return value.equals(val);
		return false;
	}

	/**
	 * Returns pairs value related to key.
	 * @param key key
	 * @return value that related to given key
	 */ 
	public String getPair( String key ) {
		return pairs.get(key);
	}
	/**
	 * Checks if id's are equal for two vertex.
	 * @param other other vertex
	 * @return true if id's are equal, false otherwise.
	 */ 
	@Override
	public boolean equals ( Object other ) {

		try {
			return (this.id == ((Vertex) other).id);
		}
		catch ( Exception e ) {
			return false;
		}
	}

	/**
	 * Returns String representation of vertex.
	 * @return String representation of vertex.
	 */ 
	public String toString ( ) {
		StringBuilder sb = new StringBuilder();
		sb.append("id = " + id + ", label = " + label + ", weight = " + weight + "\n");
		sb.append("pairs: ");
		sb.append(pairs.toString());

		return sb.toString();
	}
}