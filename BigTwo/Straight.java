
/**
 * This class is used to model a hand of straight in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class Straight extends Hand {
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of straight with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card	models hand with this specified list of cards 
	 */
	public Straight(CardGamePlayer player, CardList card) {
			
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort(); 
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
			return false ; } 
			
		this.sort(); // sorting the hand in ascending order 
		
		int count = 0; 
		int[] temprank = {0,0,0,0,0}; 
		
		//since a would be 0 and 2 would be 1, 
		//we give pseudo-ranks of 13 and 14 to them
		//and store all rank values in array temprank
		for (int j = 0; j < 5 ; j ++) { 
			
			if (this.getCard(j).getRank() == 0) {
				temprank[j] = 13; 
			}
			else if (this.getCard(j).getRank() == 1 ) {
				temprank[j] = 14; 
			}
			else {
				temprank[j] = this.getCard(j).getRank();
			}
			
		}
		
		
		for ( int i = 0; i < 4; i ++ ) {
			if (temprank[i] + 1 == temprank[i+1]) {
				count ++; 
			}	
		}
		
		
		if ( count == 4) {//if all cards are consecutive
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
			
		String s = "Straight"; 
		return s; 
		}
}
