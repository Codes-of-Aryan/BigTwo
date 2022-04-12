
/**
 * This class is used to model a hand of flush in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Flush extends Hand{
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of flush with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Flush(CardGamePlayer player, CardList card) {
			
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort(); //sorts in the ascending order
		return this.getCard(4);  
	}
		
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {
			
		if (this.size() != 5) {
			return false;}
		
		int count = 0 ; //counter
		
		int suit = this.getCard(0).getSuit(); 
		
		for(int i = 0; i < 5; i ++ ) {
			if (this.getCard(i).getSuit() == suit ) {
				count ++; 
			}
		}
		
		if (count == 5) { //count = 5 implies all cards are same suit 
			return true; 
		}
		
		else return false; 
		
	}
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
			
		String s = "Flush"; 
		return s; 
	}

}
