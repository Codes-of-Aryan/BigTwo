
/**
 * This class models a deck of cards used in a BigTwo game.
 * It is a subclass of the deck class and overrides the 
 * intialize() method. 
 * 
 * @author Aryan Agrawal (UID: 3035812373) 
 *
 */
public class BigTwoDeck extends Deck {
	
//======================================================Constructors\\	
	/**
	 * Creates an instance of the BigTwoDeck class by calling 
	 * the intialize() method
	 */
	public BigTwoDeck() {
			intialize(); 
	}
	
//================================================Overriding Methods\\	
	
	/**
	 * Intializes the deck of cards (called within the constructor) 
	 */
	public void intialize() {
	
		removeAllCards(); //1. remove all cards from the deck 
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard bigcard = new BigTwoCard(i, j);
				addCard(bigcard); //2. add 52 new cards to the list
				
			}
		}
		
	}
	
}
