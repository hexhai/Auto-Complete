package a03;
import java.util.Comparator;

/**
 * Search through a sorted array using binary search, and alert the index of the first and last Term that contains the "key"
 * @author Hai Le & Nikolas Jones
 * 09/30/19
 */
public class BinarySearchDeluxe {
	/**
	 * Return the index of the first key in the array if it equals the search key, and return -1 if no such key is found
	 * @param a which is the array
	 * @param key which is the desired key to search for
	 * @param comparator which is the comparator we will use to check if key exist within the array
	 * @return
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
	    checkError(a, key, comparator);
	    int low = 0;
	    int high = a.length - 1;
	    int result = -1;
	    //While low is less than or equal to high, keep looping
	    while(low <= high) {
	    	int middle = low + (high - low) / 2; //Create an int variable called mid with the value of the index in the middle of the current array
	    	
	    	//If comparing the array at index mid and key returns a 0, then the result is at index mid, and set high to mid - 1 to exit the loop
	    	if(comparator.compare(a[middle], key) == 0) {
	    		result = middle;
	    		high = middle - 1;
	    	}
	    	
	    	//Else if comparing them returns an integer less than 0, that means the index is less than mid, so set high to mid - 1
	    	else if(comparator.compare(a[middle], key) < 0) {
	    		low = middle + 1;
	    	}
	    	
	    	//Else, the integer is bigger than 0, which means the index is more than mid, so set low to middle + 1
	    	else {
	    		high = middle - 1;
	    	}
	    }
		return result;
	}
    
    /**
     * Implement binary search that will search through the array by dividing the array up into 2, and checking the half to see if key exist. This provides faster speed
     * Return the index of the last key in the array if it equals the search key, or -1 if no such key exist
     * @param a which is the array
     * @param key which is the desired key to search for
     * @param comparator which is the comparator we will use to check if key exist within that array
     * @return
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
	    checkError(a, key, comparator);
	    int low = 0;
	    int high = a.length - 1;
	    int result = -1;
	    //While low is less than or equal to high, keep looping
	    while(low <= high) {
	    	int middle = low + (high - low) / 2; //Create an int variable called mid with the value of the index in the middle of the current array
	    	
	    	//If comparing the array at index mid and key returns a 0, then the result is at index mid, and set low to mid + 1 to exit the loop
	    	if(comparator.compare(a[middle], key) == 0) {
	    		result = middle;
	    		low = middle + 1;
	    	}
	    	
	    	//Else if comparing them returns an integer less than 0, that means the index is less than mid, so set high to mid - 1
	    	else if(comparator.compare(a[middle], key) < 0) {
	    		low = middle + 1;
	    	}
	    	
	    	//Else, the integer is bigger than 0, which means the index is more than mid, so set low to middle + 1
	    	else {
	    		high = middle - 1;
	    	}
	    }
		return result;
    }
    
    /**
     * Check if the array, key, and comparator passed through are null, if any of them are, throw a null pointer exception
     * @param a
     * @param key
     * @param comparator
     */
	private static <Key> void checkError(Key[] a, Key key, Comparator<Key> comparator) {
		if (a == null || key == null || comparator == null){
	    	throw new NullPointerException();
	    }
	}
}
