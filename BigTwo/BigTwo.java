import java.util.*;

/**
 * The BigTwo class is used to model a Big Two card game.
 * It implements the CardGame interface. 
 * 
 * @author Aryan Agrawal (UID: 3035812373)
 *
 */
public class BigTwo implements CardGame {

//========================================Private Instance Variables\\	
	private int numOfPlayers; 
	private Deck deck; 
	private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>(); 
	private ArrayList<Hand> handsOnTable = new ArrayList<Hand>(); 
	private int currentPlayerIdx;
	private BigTwoGUI gui ; 
	private int pass = 0 ; // to count the number of continuous 'passes' made by the player
	
//=======================================================Constructor\\
	/**
	 * A constructor for creating a big two card game.
	 */
	public BigTwo() {
		
		gui = new BigTwoGUI(this); // creating a bigtwoUI object 
		
		//creating 4 players and adding them to the list 
		for(int i = 0; i < 4; i++) {
			
			CardGamePlayer player = new CardGamePlayer(); 
			playerList.add(player); 
		}
		 
	}
//===========================================================Getters\\	
	
	/**
	 * A method that returns the number of players. 
	 * 
	 * @return integer the int value of the number of players. 
	 */
	public int getNumOfPlayers() {
		
		return this.numOfPlayers; 
	}
	
	/**
	 * A method that returns the deck of cards in use
	 * 
	 * @return Deck the Deck object is returned 
	 */
	public Deck getDeck() {
		return this.deck; 
	}
	
	/**
	 * A method that returns list of players 
	 * 
	 * @return ArrayList<CardGamePlayer> a list of players. 
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return this.playerList; 
	}
	
	/**
	 * A method that returns a list of hands that have been played on the table 
	 * 
	 * @return ArrayList<Hand> an array list of type hands
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return this.handsOnTable; 
	}
	
	/**
	 * A method that returns the index of the current player 
	 * 
	 * @return integer the value of the index to be returned
	 */
	public int getCurrentPlayerIdx() {
		return this.currentPlayerIdx; 
	}
	
//====================================================Public Methods\\
	
	/**
	 *  A method for starting the game with a shuffled deck of cards 
	 *  
	 *  @param Deck the shuffled deck with which we begin the game 
	 */
	public void start (Deck deck) {
		
		//Step 1: remove all cards from the players and table 
		
		//removing all cards from all the players 
		for ( int i = 0 ; i < 4; i ++) { 
			playerList.get(i).removeAllCards();
		}
		
		//removing all hands from the table 
		for ( int i = 0 ; i < handsOnTable.size(); i ++) { 
			handsOnTable.get(i).removeAllCards();
		}
		
		//Step 2: distribute all cards to players
		
		for(int i = 0; i < 4; i ++) {
			for (int j = 0; j < 13; j ++) {
				playerList.get(i).addCard(deck.getCard(j + ( 13 * i )));
			}
		}
		
		//sorting each player's cards
		for ( int i = 0 ; i < 4; i ++) { 
			playerList.get(i).getCardsInHand().sort();
		}
		
		//Step 3,4: identify which player has the 3 of diamonds 
		//and set currentPlayerIdx and ActivePlayer to that player
		
		BigTwoCard threeD = new BigTwoCard(0,2); 
		
		for ( int i = 0 ; i < 4; i ++) { 
			if(playerList.get(i).getCardsInHand().contains(threeD)) {
				
				currentPlayerIdx = i ; 
				gui.setActivePlayer(i);
			}
		}
		
		gui.repaint(); // 5.calling repaint to show cards 
		//gui.promptActivePlayer(); //6.prompting the current player to make a move
		
	}
	
	/**
	 * A method for making a move with the specified index 
	 * 
	 * @param playerIdx the index of the current player 
	 * @param cardIdx the index of the cards that the player plays 
	 */
	public void makeMove(int playerIdx, int[] cardIdx ) {
		
		checkMove(playerIdx, cardIdx); 
	}
	
	/**
	 * A method for checking the moves made by a player
	 * 
	 * @param playerIdx the index of the current player 
	 * @param cardIdx the index of the cards that the player plays 
	 */
	public void checkMove(int playerIdx, int[] cardIdx ) { 
		
		//changing current player index to the player index 
		currentPlayerIdx = playerIdx; 
		
		//storing cards from the index in playedcards 
		CardList playedcards = new CardList(); 
		
		//storing the last hand on table in a new hand
		Hand lastHandOnTable = (handsOnTable.isEmpty()) ? null : handsOnTable.get(handsOnTable.size() - 1);
		
		boolean checkForPass = false; 
		boolean noFourthPass = false; 
		BigTwoCard threeD = new BigTwoCard(0,2);
		
		if (pass == 3 ) {
			noFourthPass = true; 
		}
		
		if (cardIdx == null) { 
			pass ++ ;
			checkForPass = true; 
		}
		
		//giving played cards the cards that were being referenced in cardIdx 
		if ( checkForPass == false) {
			for ( int i = 0; i < cardIdx.length; i ++) { 
			playedcards.addCard(playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]));
			}
		}
		
		//storing the return value of composeHand method in new hand
		Hand composedHand = composeHand(playerList.get(playerIdx), playedcards); 
		
		//if the game has ended 
		if(endOfGame()) {
			 System.out.println("Game ends"); 
			 for(int i = 0; i < 4 ; i ++ ) { 
					if(playerList.get(i).getCardsInHand().size() == 0){ 
						System.out.println("Player " + i + " wins the game.");
						gui.printMsg("Player " + i + " wins the game.");
						gui.printMsg("The Game has ended. Please Restart or Quit"); 
						gui.disable();
					}
					else {
						System.out.println("Player " + i + " has " + playerList.get(i).getCardsInHand().size() +" cards in hand."); 
						
					}
			 }
		}
		
		//if the game has not ended 
		else {	
			
				if (noFourthPass) { // when there have been 3 passes
					
					if (checkForPass) {// a fourth pass would not be valid 
						
						System.out.println("Not a legal move!!!"); 
						gui.printMsg("Not a legal move!!!");
						pass = 3;
						//gui.promptActivePlayer();
						 
					}
					
					else{
					
						if (composeHand(playerList.get(playerIdx), playedcards ) != null ) { //if it is a valid hand 
					
							handsOnTable.add(composedHand);
					
				
							System.out.print("{" + composedHand.getType() + "} " );
							gui.printMsg("{" + composedHand.getType() + "} ");
							composedHand.print(true, false);
							System.out.println(); 
							pass = 0; //reset the value of pass 
					
							for ( int i = 0; i < cardIdx.length; i ++) { 
								playerList.get(playerIdx).getCardsInHand().removeCard(cardIdx[i]);
								} //remove cards from players list if they have been played 
							
							gui.setActivePlayer((playerIdx + 1) % 4);
							gui.repaint();
							//gui.promptActivePlayer();
							
							
					
							}
						
						else {  System.out.println("Not a legal move!!!");
								gui.printMsg("Not a legal move!!!");
							//gui.promptActivePlayer();}
						}
					}
				}
			
				else if (checkForPass) { //if player passed this round 
						
					System.out.println("{Pass}");
					gui.printMsg("Pass");
					System.out.println(); 
				
					gui.setActivePlayer((playerIdx + 1) % 4);
					gui.repaint();
					//gui.promptActivePlayer();
				}
			
			
				else if (composeHand(playerList.get(playerIdx), playedcards ) != null ) { //if the is a valid hand 
				
					if (lastHandOnTable != null) {
				
						if(composedHand.beats(lastHandOnTable)){ // if the hand beats the previous hand 
						
							handsOnTable.add(composedHand);
						
							System.out.print("{" + composedHand.getType() + "} " );
							gui.printMsg("{" + composedHand.getType() + "} ");
							composedHand.print(true, false);
							System.out.println(); 
							pass = 0; //need to reset pass 
							
							for ( int i = 0; i < cardIdx.length; i ++) { 
								playerList.get(playerIdx).getCardsInHand().removeCard(cardIdx[i]);
								}
						
							gui.setActivePlayer((playerIdx + 1) % 4);
							gui.repaint();
							//gui.promptActivePlayer();
						}
				
						else { System.out.println("Not a legal move!!!"); // if it does not beat the hand on the table 
							   gui.printMsg("Not a legal move!!!");
							//gui.promptActivePlayer();
						}
					}
				
					else { //if last hand on table is null, then this is the first hand to be played 
						
						if (playedcards.contains(threeD)) { // the first hand must contain the 3 of Diamonds 
						
						handsOnTable.add(composedHand); 
					
						System.out.print("{" + composedHand.getType() + "} " );
						gui.printMsg("{" + composedHand.getType() + "} ");
						composedHand.print(true, false);
						System.out.println();
						pass = 0; 
						
						for ( int i = 0; i < cardIdx.length; i ++) { 
							playerList.get(playerIdx).getCardsInHand().removeCard(cardIdx[i]);
							}
					
						gui.setActivePlayer((playerIdx + 1) % 4);
						gui.repaint();
						//gui.promptActivePlayer();
						}
						
						else {
							
							System.out.println("Not a legal move!!!"); // if it does not beat the hand on the table 
							gui.printMsg("Not a legal move!!!");
							//gui.promptActivePlayer();
							
						}
					}
				}
			
			
			else {
					System.out.println("Not a legal move!!!");
					gui.printMsg("Not a legal move!!!");
					
					//gui.promptActivePlayer();
					
			}
		}
	}
	
	/**
	 * A method that checks whether the game has ended or not 
	 * 
	 * @return boolean returns true if the game has ended, else false 
	 */
	public boolean endOfGame() { 
		
		for(int i = 0; i < 4 ; i ++ ) { 
			if(playerList.get(i).getCardsInHand().size() == 0){ //if a player is out of cards, then game is over
				return true; 
			}
		}
		
		return false; 
		
	}

//=============================================Public Static Methods\\
	
	/**
	 * The main method that starts the big two game. It creates 
	 * a new game, shuffles the deck and starts the new game 
	 * 
	 * @param args Unused 
	 */
	public static void main( String[] args) {
		
		BigTwo game = new BigTwo(); 
		
		BigTwoDeck deck = new BigTwoDeck(); 
		deck.shuffle(); //shuffles the deck 
		
		game.start(deck); //starts the game with the shuffled deck
	}
	
	/**
	 * This method returns a valid hand from the list of specified cards. 
	 * returns null if no valid hands 
	 * 
	 * @param player The current player for which we are checking the validity 
	 * @param cards The specified list of cards from which we check the validity of hand
	 * 
	 * @return Hand Returns a valid hand, if no valid hand, returns null 
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		
		//creating objects of every type of hand 
		Single single = new Single(player,cards); 
		Pair pair = new Pair(player, cards);
		Triple triple = new Triple(player, cards);
		Straight straight = new Straight(player, cards);
		Flush flush = new Flush(player, cards);
		FullHouse fullhouse = new FullHouse(player, cards);
		Quad quad = new Quad(player, cards);
		StraightFlush straightflush = new StraightFlush(player, cards);
		
		//checking validity of each 
		if(straightflush.isValid()) {
			return straightflush; }
		
		else if(quad.isValid()) {
			return quad; }
		
		else if(fullhouse.isValid()) {
			return fullhouse; }
		
		else if(flush.isValid()) {
			return flush; }
		
		else if(straight.isValid()) {
			return straight; }	
	
		else if(triple.isValid()) {
			return triple; }
	
		else if(pair.isValid()) {
			return pair; }
	
		else if(single.isValid()) {
			return single; }
		
		else return null; 
	}
}
//===============================================================END\\
 