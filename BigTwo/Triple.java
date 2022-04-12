
/**
 * This class is used to model a hand of triple in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Triple  extends Hand{
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of triple with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Triple(CardGamePlayer player, CardList card) {
		
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort(); //sorts the list in ascending order  
		return this.getCard(2); //returns the strongest card 
	}
		
//====================================================Public Methods\\
		
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {
		
		if (this.size() == 3) {
			if (this.getCard(0).getRank() ==  this.getCard(1).getRank()) {
				if(this.getCard(1).getRank() ==  this.getCard(2).getRank()) {
					
					return true; //if rank of all 3 cards is the same
				}
			}
		}
		
		return false;
	}
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
		
		String s = "Triple"; 
		return s; 
	}
}
