
/**
 * This class is used to model a hand of full house in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class FullHouse extends Hand{
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of full house with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public FullHouse(CardGamePlayer player, CardList card) {
			
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort(); //arranges cards in an ascending order 
		
		//top card must be in either position 2 or 4 (after sorting)
		//depends on where the triplet lies (first three or 
		// last three)
		
		int count = 0; // counter 
		int rank = this.getCard(0).getRank(); 
		
		for (int i = 0; i < 3; i ++ ) {
			if (this.getCard(i).getRank() == rank) {
				count ++; 
			}
		}
		
		//count = 3 implies triplet is the first three cards 
		if (count == 3 ) { 		
			return this.getCard(2) ;}
		
		else { return this.getCard(4); } 
	}
		
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */	
	public boolean isValid() {
			
		this.sort(); 
		
		if (this.size() != 5) {
			return false; 
		}
		
		int count1 = 0; //counter 1 
		int count2 = 0; //counter 2 
		int rank1 = this.getCard(0).getRank();
		int rank2 = this.getCard(2).getRank();
		
		for (int i = 0; i < 3; i ++) {
			if (this.getCard(i).getRank() == rank1) {
				count1++;}
		}
		
		for (int i = 2; i < 5; i ++) {
			if (this.getCard(i).getRank() == rank2) {
				count2++;}
		}
		
		
		if (count1 == 3) { //count1 = 3 implies first three are triplet 
			if (this.getCard(3).getRank() == this.getCard(4).getRank()) {			
				
				return true; } //if last 2 cards have the same rank, then return true 
		}
		
		else if (count2 == 3) {//count2 = 3 implies last three are triplet 
			if (this.getCard(0).getRank() == this.getCard(1).getRank()) {	
			
				return true; }//if first two cards have the same rank, then return true 
		}
		
		return false; 
	}
		
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
			
		String s = "FullHouse" ; 
		return s; 
	}

}
