
/**
 * This class is used to model a hand of quad in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Quad extends Hand{
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of quad with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Quad(CardGamePlayer player, CardList card) {
		
		super(player,card); // making an explicit call
	}
	
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort();//sorts in ascending order 
		
		//either card at position 3 or position 4 is the top card 
		
		// checking if the first four cards have the same rank
		if (this.getCard(0).getRank() == this.getCard(1).getRank()) {  
				return this.getCard(3); }
		
		else return this.getCard(4); 
		
	}
	
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {
		
		this.sort();//sorts in ascending order  
		
		if (this.size() != 5) {
			return false;}
		
		//any four of the five cards must have the same rank for validity 
		
		int count = 0; // counter 
		
		for(int i = 0; i < 4; i ++ ) { 
			if (this.getCard(i).getRank() == this.getCard(i+1).getRank()) {
				count ++; }
		}
		
		if ( count == 3) { //count = 3, either first or last four have same rank
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
		
		String s = "Quad"; 
		return s; 
	}

}
