
/**
 * This class is used to model a hand of straight flush in a BigTwo card game. 
 * It is a subclass of the hand class. It overrides some of the methods 
 * of the hand class such as getTopCard, isValid and getType
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class StraightFlush extends Hand{
//SerialVersionUID warning can be left alone for this assignment

//=======================================================Constructor\\	
	/**
	 * A constructor for modeling a hand of straight flush with the specified 
	 * player and list of cards 
	 * 
	 * @param player models hand with this specified player 
	 * @param card models hand with this specified list of cards 
	 */
	public StraightFlush(CardGamePlayer player, CardList card) {
			
		super(player,card); // making an explicit call
	}
		
//================================================Overriding Methods\\
	/**
	 * A method that returns the top card of the hand 
	 * 
	 * @return Card The top card that is to be returned 
	 */
	public  Card getTopCard() {
		
		this.sort(); //sorts in ascending order 
		return this.getCard(4); //the last card has the highest rank, after sort
		
	}
		
//====================================================Public Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public boolean isValid() {
			
		if (this.size() != 5) {
			return false; }
		
		this.sort(); 
		
		//for valid : same suit and consecutive ranks
		 
		int countsuit = 0 ; // counter for suit 
		int countrank = 0; //counter for rank
		int suit = this.getCard(0).getSuit(); 
		int rank = this.getCard(0).getRank(); 
		int[] temprank = {0,0,0,0,0}; 
		
		//we give rank value 13 and 14 to Ace and 2 
		//and store all the rank values in temprank
		
		for (int j = 0; j < 5; j ++ ) {
			
			if (this.getCard(j).getRank() == 0) {
				temprank[j] = 13; }
			
			else if (this.getCard(j).getRank() == 1 ) {
				temprank[j] = 14; }
			
			else {
				temprank[j] = this.getCard(j).getRank();}
		}
		
		
		
		for (int i = 0; i < 5; i ++) { 
			if(this.getCard(i).getSuit() == suit) { 
				countsuit ++; }
		}
		
		for (int i = 0; i < 4 ; i ++ ) {
			if (temprank[i]  + 1 == temprank[i+1]) {
				countrank ++; }
		}
		
		if (countsuit == 5) { //countsuit = 5 implies all 5 are same suit
			if (countrank == 4) {//countrank = 4 implies all 4 are consecutive
				return true; }
		}
	
		return false ; 
	}
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public String getType() {
			
		String s = "StraightFlush"; 
		return s; 
	}
}
