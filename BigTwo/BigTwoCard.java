
/**
 * This class is used to model a card used in the BigTwo game
 * It is a subclass of the Card class and overrides the 
 * compareTo() method. 
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class BigTwoCard extends Card {
//SerialVersionUID warning can be left alone for this assignment	

	
//=======================================================Constructor\\	
	/**
	 * Creates a card with the specified suit and rank 
	 * 
	 * @param suit The specified suit. An integer between 0 and 3. Where 0 = d, 1=c, 2=h, 3=s.
	 * @param rank The specified rank. An integer between 0 and 12. Where 'A' = 0, '2' = 1, and so on....
	 */
	public BigTwoCard(int suit, int rank) {
		
		super(suit, rank) ; //since there is no no-argument constructor in 
							//Card, we have to make an explicit call
		
	}

//=====================================================Public Method\\	
	
	/**
	 * A method that compares the order of this card with the given card
	 * 
	 * @param card The given card 
	 * @return integer Returns -1,0, or +1 if this card is less than, equal, or greater than the given card 
	 */
	public int compareTo(Card card) {
		
		//We have to compare in a way such that 2 is highest 
		//then A, K, Q, J, 10, ......3
		
		/*for ranks :
		since index 1 [card: 2] is highest and 0 [card: Ace] is 2nd highest 
		after which it goes from 12 to 2, we'll give index 2 to 12 
		the same values as their index and give index 1 the value of 
		14 and index 0 the value of 13 */ 
		
		int thisValue;
		int cardValue; 
		
		if (this.rank >= 2 ) {
			
			thisValue = this.rank; 
		}
		
		else  {
			if (this.rank == 1) 
				thisValue = 14; 
			
			else thisValue = 13; 
		}
		
		
		if (card.rank >= 2 ) {
			
			cardValue = card.rank; 
		}
		
		else  {
			if (card.rank == 1) 
				cardValue = 14; 
			
			else cardValue = 13; 
		}
		
		// now we compare the values of rank[the new values] and suit 
		if (thisValue > cardValue) {
			return 1;
		} else if (thisValue < cardValue) {
			return -1;
		} else if (this.suit > card.suit) {
			return 1;
		} else if (this.suit < card.suit) {
			return -1;
		} else {
			return 0;
		}
		
	}
}
