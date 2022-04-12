
/**
 * This class is used to model a hand of pair in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Pair extends Hand {
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of pair with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Pair(CardGamePlayer player, CardList card) {
			
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		if (this.getCard(0).compareTo(this.getCard(1)) == 1) {
			return this.getCard(0); //if card at index 0 is stronger return it
		}
		
		else return this.getCard(1) ; // otherwise return the card at index 1 
	}
		
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {
			
		if (this.size() == 2) {
			if (this.getCard(0).getRank() == this.getCard(1).getRank()) {
				
				return true; 
			}
			
			else return false; //if the two cards don't have the same rank
		} 
		
		else return false; // if size is not two then it is not a pair 
		}
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
		
		String s = "Pair"; 
		return s; 
		}
}
