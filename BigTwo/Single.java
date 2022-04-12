
/**
 * This class is used to model a hand of single in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Single extends Hand {
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of single with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Single(CardGamePlayer player, CardList card) {
		
		super(player,card); // making an explicit call
	}
	
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		return this.getCard(0); //since there is just one card
	}
	
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {

		if (this.size() == 1) { //if there is only 1 card, it must be of type single
			return true ; } 
		
		else return false; 
	}
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
		
		String s = "Single"; 
		return s; 
	}
}
