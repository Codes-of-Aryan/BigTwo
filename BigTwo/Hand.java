
/**
 * This class models a hand of cards. It is a subclass of the CardList class
 * and contains private instance variables, public and abstract methods. 
 * 
 * @author Aryan Agrawal (UID: 3035812373) 
 *
 */
public abstract class Hand extends CardList {
//SerialVersionUID warning can be left alone for this assignment
	
//========================================Private Instance Variables\\
	private CardGamePlayer player; 
	
//======================================================Constructors\\
	
	/**
	 *  Constructor that models a hand with the specified player and 
	 *  the specified list of cards
	 * 
	 * @param player models a hand with the specified player
	 * @param cards models a hand with the specified list of cards 
	 */
	public Hand(CardGamePlayer player, CardList card) {
		
		this.player = player; //building hand with the specified player
		
		for ( int i = 0; i < card.size(); i ++ ) {
			this.addCard(card.getCard(i)); //add specified cards to the hand 
		}
		
	}

//====================================================Public Methods\\
	
	/**
	 * A method that returns the player of this hand
	 * 
	 * @return player The player that is to be returned
	 */
	public CardGamePlayer getPlayer() {
		
		return this.player;
	}
	
	/**
	 * A method that returns the top card of this hand
	 * 
	 * @return Card The top card that is to be returned 
	 * returns null as it gets overwritten in the individual subclasses of the Hand class
	 */
	public Card getTopCard() {  
		
		return null; //this method will be overridden in all of the subclasses
	}
	
	/**
	 * This method checks if this hand is stronger than the given hand
	 * @param hand The given hand 
	 * @return boolean If this hand is stronger returns true, else false
	 */
	public boolean beats(Hand hand) {  
		
		
		int tsize = this.size(); 
		int hsize = hand.size(); 
		
		
		if ( tsize != hsize ) { //if size is not same, cannot compare
			return false ; 
		}
		

		if (this.isValid() && hand.isValid()) { //both hands must be valid 
			
			boolean valid = true ;
			}
		
		else {
		return false; }
		

		//if both hands are of type single 
		if(this.getType() == "Single" && hand.getType() == "Single") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type pair 
		if(this.getType() == "Pair" && hand.getType() == "Pair") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type triple 
		if(this.getType() == "Triple" && hand.getType() == "Triple") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type straight
		if(this.getType() == "Straight" && hand.getType() == "Straight") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type flush
		if(this.getType() == "Flush" && hand.getType() == "Flush") {
			
			if(this.getTopCard().getSuit() > hand.getTopCard().getSuit()){
				
					return true;
				}
			
			else if (this.getTopCard().getSuit() == hand.getTopCard().getSuit()) {
					if(this.getTopCard().compareTo(hand.getTopCard()) == 1) {
						
						return true; 
					}
				}
			}
		
		//if both hands are of type FullHouse 
		if(this.getType() == "FullHouse" && hand.getType() == "FullHouse") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type Quad 
		if(this.getType() == "Quad" && hand.getType() == "Quad") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		//if both hands are of type StraightFlush 
		if(this.getType() == "StraightFlush" && hand.getType() == "StraightFlush") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1){
				
					return true;}
			}
		
		if(tsize == 5) { //if both hands are of size 5 
			
			// checking if the first hand has a type stronger than the second
			
			if(this.getType() == "StraightFlush" && hand.getType() != "StraightFlush") {
				
				return true; }
			
			if(this.getType() == "Quad" && 
					(hand.getType() != "StraightFlush" && hand.getType() != "Quad" )){
				return true; }
			
			if(this.getType() == "FullHouse" && 
					(hand.getType() != "StraightFlush" && hand.getType() != "Quad" && hand.getType() != "FullHouse")) {
				return true; }
			
			if(this.getType() == "Flush" && 
					(hand.getType() != "StraightFlush" && hand.getType() != "Quad" 
						&& hand.getType() != "FullHouse" && hand.getType() !="Flush")) {
				return true; 
			}
			
			
		}
		
		
		return false; // return false in all other cases.  
	}

//==================================================Abstract Methods\\
	
	/**
	 * A method that checks the validity of the current hand
	 * 
	 * @return boolean Returns true if current hand is valid, false otherwise
	 */
	public abstract boolean isValid();
	
	/**
	 * A method that returns a string that identifies the type of the hand 
	 * 
	 * @return String returns the type of the hand 
	 */
	public abstract String getType();	
	
}
