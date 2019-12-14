package a03;

import java.util.Arrays;

/**
 * Implement the functionality to auto complete for a given set of strings and weights using the methods in classes Term, and BinarySearchDeluxe
 * Sort the term in lexicographic order, then use binary search to find the set of terms that match the start with a given prefix, 
 * and sort the matching terms in descending order by weight.
 * @author Hai Le & Nikolas Jones
 * 10/02/19
 */
public final class AutoComplete {
	//Create a private array of type term named terms in order to store the value of the terms passed as parameter in the constructor
    private final Term[] terms; //The variable has to be final because it has to be immutable. The class is also final.
	
	/**
	 * Initialize an AutoComplete term
	 * @param terms
	 */
	public AutoComplete(Term[] terms) {
		//Throw a null pointer exception if the parameter passed is null
		if(terms == null) {
			throw new NullPointerException();
		}
		
		//Create a copy in order to implement immutability
		this.terms = new Term[terms.length];
		
		//Put the values of terms into the newly created array
		for(int i = 0; i < terms.length; i++) {
			this.terms[i] = terms[i];
		}
		
		Arrays.sort(this.terms); //Sort this object's terms
	}
	
	/**
	 * Returns all terms that start with the given prefix, passed as parameter, in descending order of weight
     * @param prefix which is the words the user enter as they search
	 * @return all the terms that start with the prefix
	 */
    public Term[] allMatches(String prefix) {
		checkNull(prefix);
		Term key = new Term(prefix, 0); //Create a new term called key with the parameters of the prefix as query, and 0 as weight
		//Create variables named first and last to keep track of the first and last index value of an array consisting of terms
		int first = BinarySearchDeluxe.firstIndexOf(this.terms, key, Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(this.terms, key, Term.byPrefixOrder(prefix.length()));
		
		//If the value of first or last is -1, return a new empty array of type term
	    if (first == -1 || last == -1) {
	    	return new Term[0];
	    }
	    Term[] temp = new Term[(last - first) + 1]; //Create a new term array named temp with the value of last - first + 1
	    temp = Arrays.copyOfRange(this.terms, first, last + 1); //Initialize temp with a copy of this object's terms ranging from first to last + 1
	    Arrays.sort(temp, Term.byReverseWeightOrder()); //Sort the temp array by reverse order
	    return temp; //Return the temp array
    }
    
    /**
     * Returns the number of terms that start with the given prefix
     * @param prefix which is the words the user enter as they search
     * @return number of items that match the prefix
     */
    public int numberOfMatches(String prefix) {
		checkNull(prefix);
		Term key = new Term(prefix, 0); //Create a new term called key with the parameters of the prefix as query, and 0 as weight
		//Create variables named first and last to keep track of the first and last index value of an array consisting of terms
		int first = BinarySearchDeluxe.firstIndexOf(this.terms, key, Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(this.terms, key, Term.byPrefixOrder(prefix.length()));
		
		//If there are 0 matches, return 0
		if(this.allMatches(prefix).length <= 0) {
			return 0;
		}
		//Else, return the length of the amount of matching terms comparing to the prefix
		else {
	    	return (last - first) + 1; //return the value of last subtract first then plus 1
		}
    }
    
    /**
     * Checks if the parameter passed is null, and throw a new exception error if the value is null
     * @param prefix, which is the words the user enter while searching
     */
	private void checkNull(String prefix) {
		if(prefix == null) {
			throw new NullPointerException();
		}
	}
}
