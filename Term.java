package a03;

import java.util.Comparator;

/**
 * Represents an autocomplete term(A string query and an associated real-valued weight)
 * Sort the term by 3 different orders: natural order, descending order by weight, and a family of alternate orderings.
 * @author Hai Le & Nikolas Jones
 * 09/30/19
 */
public final class Term implements Comparable<Term> {
	private final String query; //Make the variables final in order to make them immutable, along with making the class final
	private final double weight;
	
	/**
	 * Intialize a term with the given query string and weight passed as parameters
	 * @param query will be passed as the value for this object's query
	 * @param weight will be passed as the value for this object's weight
	 */
    public Term(String query, double weight) {
    	if(query == null) {
    		throw new NullPointerException();
    	}
    	if(weight < 0) {
    		throw new IllegalArgumentException();
    	}
    	this.query = query;
    	this.weight = weight;
    }
    
    /**
     * Compare the terms in descending order by weight
     * @return
     */
    public static Comparator<Term> byReverseWeightOrder(){
    	return new byReverseOrder();
    }
    
    /**
     * Compare and return the computation of the weight of the two terms being compared
     * @author Hai Le & Nikolas Jones
     */
    private static class byReverseOrder implements Comparator<Term>{
		@Override
		public int compare(Term o1, Term o2) {
			double answer = o2.weight - o1.weight; //Create a variable called answer of type double with the value of second term's weight subtract first term's weight
			if (answer > 0) //If answer is more than 0, return 1, this says that it's greater.
			{
			    return 1;
			} else if (answer < 0) //If answer is less than 0, return -1, this says that it's less
			{
			    return -1;
			} else //Else, return 0, this means it's equal
			    return 0;
		    }
    }
    
    /**
     * Compare the terms in lexicographic order, but using only the first r integer of each query
     * @param r which is the integer that will be used to compare
     * @return 
     */
    public static Comparator<Term> byPrefixOrder(int r){
    	if(r < 0) {
    		throw new IllegalArgumentException();
    	}
    	return new byPrefixOrder(r);
    }
    
    /**
     * Create and keep track of r, the letter that will be used in the comparator
     * @author Hai Le & Nikolas Jones
     */
    private static class byPrefixOrder implements Comparator<Term>{
    	private int r;
    	public byPrefixOrder(int r) {
    		this.r = r;
    	}
    	
    	/**
    	 * Compare two objects's query, and return the result.
    	 */
		@Override
		public int compare(Term left, Term right) {
			//Create two differerent strings with the value of it's own query, and make a substring starting from the first letter to r or the whole word, if r does not exist within that query
			String one = left.query.substring(0, Math.min(left.query.length(), this.r));
			String two = right.query.substring(0, Math.min(right.query.length(), this.r));
			return one.compareToIgnoreCase(two); //Compare the first and second term ignoring case
		}
    }
    
    /**
     * Compare the terms in lexicographic order by query
     */
	@Override
	public int compareTo(Term that) {
		return this.query.compareToIgnoreCase(that.query); //Compare this object's query to the parameter's query, ignoring case.
	}
	
	/**
	 * Return a string representation of the term in the following format:
	 * the weight, followed by a tab, followed by the query
	 */
    public String toString() {
    	return this.weight + "\t" + this.query;
    }
}
