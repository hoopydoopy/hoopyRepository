/**
 * This program calculates my daily calories burned for me
 * @author Ezra
 * Main class only takes the instance of FitnessTracker
 */
public class Main {

	/** 
	 * Singleton pattern
	 * allows only one object to be created
	 * gets the instance of FitnessTracker class instead of creating a new object
	 */
	public static void main(String[] args) {
		
		FitnessTracker fitnessTracker = FitnessTracker.getInstance(); 
	}

}
