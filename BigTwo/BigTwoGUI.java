import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;
import java.util.*; 

/**
 * This class implements the card game interface to implement a GUI 
 * of the big two game 
 * 
 * @author Aryan Agrawal (UID: 3035812373) 
 *
 */
public class BigTwoGUI implements CardGameUI {
	
//========================================Private Instance Variables\\	
	
	private BigTwo game = null; 
	private final static int MAX_CARD_NUM = 13;
	private boolean[] selected = new boolean[MAX_CARD_NUM]; 
	private int activePlayer = 0; 
	private JFrame frame; 
	private JPanel bigTwoPanel;
	private JButton playButton; 
	private JButton passButton; 
	private JTextArea msgArea; 
	private JTextArea chatArea; 
	private JTextField chatInput; 
	private Image[] playerPics; 
	private Image[][] openCards; 
	private Image closedCard; 
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	 
//=======================================================Constructor\\	
	/**
	 * A constructor that creates the bigtwogui and gets the frame and
	 *everything defined. 
	 *
	 * @param game Object of the bigtwo game 
	 */
	public BigTwoGUI(BigTwo game) {
		
		this.game = game; 
		playerList = game.getPlayerList();
		handsOnTable = game.getHandsOnTable(); 
		start(); 
		
	}

//====================================================Public Functions\\	
	
	/**
	 * This method follows the constructor call and sets the frame and panels 
	 * of the gui. 
	 */
	public void start() {
		
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Big Two Game"); 
		frame.setLayout(new BorderLayout());
		
		//storing all the images of the face up cards in an array 
		openCards = new Image[4][13]; 
		char[] suitValue = {'d', 'c', 'h', 's'}; 
		char[] rankValue = {'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'}; 
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				
				Image tempCard  = new ImageIcon("src/pics/" + rankValue[j] + suitValue[i] + ".gif").getImage(); 
				openCards[i][j] = tempCard.getScaledInstance(70, 100, Image.SCALE_SMOOTH); 
			}
		}
		
		//saving all the avatar images in another array 
		playerPics = new Image[4]; 
		
		playerPics[0] = new ImageIcon("src/pics/pikachu-removebg-preview.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH); 
		playerPics[1] = new ImageIcon("src/pics/charmander-removebg-preview.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH); 
		playerPics[2] = new ImageIcon("src/pics/squirtlle-removebg-preview.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH); 
		playerPics[3] = new ImageIcon("src/pics/snorlax-removebg-preview.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH); 
		
		closedCard = new ImageIcon("src/pics/b.gif").getImage().getScaledInstance(70, 100, Image.SCALE_SMOOTH); 
		
		bigTwoPanel = new BigTwoPanel(); 
		bigTwoPanel.setBackground(Color.black);
		
		JPanel sidePanel = new JPanel(); 
		sidePanel.setLayout( new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); 
		
		JPanel bottomPanel = new JPanel(); 
		bottomPanel.setLayout( new GridBagLayout() ); 
		bottomPanel.setBackground(Color.gray);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenuItem menuItem1 = new JMenuItem("Restart");
		JMenuItem menuItem2 = new JMenuItem("Quit");
		menuItem1.addActionListener(new RestartListener());
		menuItem2.addActionListener(new QuitListener() );
		menu.add(menuItem1);
		menu.add(menuItem2); 
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		passButton = new JButton("Pass"); 
		playButton = new JButton("Play"); 
		passButton.addActionListener(new PassListener() );
		playButton.addActionListener(new PlayListener() );
		
		JLabel label = new JLabel("Message: ");
		chatInput = new JTextField(20);
		chatInput.addActionListener(new ChatInputListener());
		
		GridBagConstraints c = new GridBagConstraints(); 
		
		//change values of c here
		c.insets = new Insets(7,7,7,7); 
		c.gridwidth = 3; 
		c.gridx = 0; 
		c.gridy = 0; 
		c.ipadx = 5; 
		bottomPanel.add(playButton,c);
		
		//change values of c again here
		c.gridx = 7; 
		bottomPanel.add(passButton,c);
		
		//change values of c again over here
		c.gridx = 10; 
		c.gridwidth = 0; 
		bottomPanel.add(label,c); 
		
		c.gridx += 14; 
		bottomPanel.add(chatInput, c);
		
		msgArea = new JTextArea(10,30); 
		msgArea.setLineWrap(true);
		
		JScrollPane msgScroller = new JScrollPane(msgArea);//add this to the side panel 
	    msgScroller.setVerticalScrollBarPolicy(
	             ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    msgScroller.setHorizontalScrollBarPolicy(
	             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
		chatArea = new JTextArea(10,20); 
		chatArea.setLineWrap(true);
		
		JScrollPane chatScroller = new JScrollPane(chatArea); //add this to the side panel 
	    chatScroller.setVerticalScrollBarPolicy(
	             ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    chatScroller.setHorizontalScrollBarPolicy(
	             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    
	    sidePanel.add(msgScroller);
	    sidePanel.add(chatScroller);
	    
	    frame.setSize(1300,800);
	    frame.add(bigTwoPanel, BorderLayout.CENTER); 
	    frame.add(sidePanel, BorderLayout.EAST); 
	    frame.add(bottomPanel,BorderLayout.SOUTH);
		//frame.pack(); 
		frame.setVisible(true); 
	}
	
	/**
	 * setter method that sets the active player 
	 * 
	 *  @param ActivePlayer integer index of the active player 
	 */
	public void setActivePlayer(int ActivePlayer) {
		
		if (activePlayer < 0 || activePlayer >= playerList.size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = ActivePlayer;
		}
		
	}

	/**
	 * repaints the gui 
	 */
	public void repaint() {
		
		resetSelected(); 
		frame.repaint(); 
	}
	
	/**
	 * Clears the messages in the text box 
	 */
	public void clearMsgArea() {
	
		msgArea.setText(null);
	}
	
	/**
	 * Adds a message to the text box 
	 */
	public void printMsg(String Msg) {
		
		msgArea.append(Msg);
		msgArea.append("\n");
	}
	
	/**
	 *This method resets the text box, de-selects all the selected cards and
	 *enables the gui  
	 */
	public void reset() {
		
		resetSelected();
		clearMsgArea(); 
		enable(); 
	}
	
	/**
	 * Deselects all the currently selected cards 
	 */
	public void resetSelected() {
		for (int j = 0; j < selected.length; j++) {
			selected[j] = false;
		}
	}
	
	/**
	 * Getter method that returns all the selected cards from the a players hand 
	 * 
	 * @return integer arrays containing the index of all the cards selected 
	 */
	public int[] getSelected() {
		
		int counter = 0 ; 
		
		for ( int i = 0 ; i < selected.length; i++ ) {
			if ( selected[i] == true) {
				counter ++ ; }
		}
		
		if (counter != 0 ) { 
			
			int tempCounter = 0; 
			int[] temparr = new int[counter]; 
			
			for ( int i = 0; i < selected.length; i ++ ) { 
				if (selected[i] == true) { 
					
					temparr[tempCounter] = i; 
					
				}
			}
			
			return temparr; 
		}
		
		else { return null; } 
	}
	
	/**
	 * Enables the user interactions in the gui 
	 */
	public void enable() {
		
		//enabling play and pass button 
		playButton.setEnabled(true);
		passButton.setEnabled(true);
		
		//enabling chat input
		chatInput.setEnabled(true);
		
		//enabling the big two panel
		bigTwoPanel.setEnabled(true);
	}	
	
	/**
	 * Disables the user interactions in the gui 
	 */
	public void disable() {
	
		//disabling play and pass button 
		playButton.setEnabled(false);
		passButton.setEnabled(false);
				
		//disabling chat input
		chatInput.setEnabled(false);
				
		//disabling the big two panel
		bigTwoPanel.setEnabled(false);
	}
	
	/**
	 * a method for prompting the active player to select cards
	 * and make his/her move
	 */
	public void promptActivePlayer() {
		
		printMsg(playerList.get(this.activePlayer).getName() + "'s turn: ");
		int[] cardIdx = getSelected();
		resetSelected();
		game.makeMove(this.activePlayer, cardIdx);
		
	}

	/**
	 * an inner class that implements the ActionListener
	 *  interface to handle button click events for the play button 
	 *  
	 * @author Aryan Agrawal 
	 *
	 */
	class PlayListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
			if (getSelected() == null ) {
				printMsg("You cannot play anything since you haven't selected anything!"); 
				repaint(); 
			}
			
			else promptActivePlayer(); 
		}
	}
	
	/**
	 * an inner class that implements the ActionListener
	 *  interface to handle button click events for the pass button 
	 *  
	 * @author Aryan Agrawal 
	 *
	 */
	class PassListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (getSelected() != null ) {
				printMsg("Please deselect your cards before pressing pass"); 
				repaint();
			}
		
			else promptActivePlayer(); 
		}
	}
	
	/**
	 * an inner class that implements the ActionListener
	 *  interface to handle events related to the chat box 
	 *  
	 * @author Aryan Agrawal 
	 *
	 */
	class ChatInputListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			chatArea.append("Player " + activePlayer +" : " + chatInput.getText());
			chatArea.append("\n");
		}
	}
	
	/**
	 * an inner class that implements the ActionListener
	 *  interface to menu item click events for the 'Quit'
	 *  menu item 
	 *  
	 * @author Aryan Agrawal 
	 *
	 */
	class QuitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			System.exit(0);
		}
	}
	
	/**
	 * an inner class that implements the ActionListener
	 *  interface to menu item click events for the 'Restart'
	 *  menu item 
	 *  
	 * @author Aryan Agrawal 
	 *
	 */
	class RestartListener implements ActionListener{	
		public void actionPerformed(ActionEvent e) { 
			
			BigTwoDeck deck = new BigTwoDeck();
			deck.shuffle();
			reset();
			game.start(deck);
			
		}	
	}
	
	/**
	 * an inner class that extends the JPanel class and implements the
	 * MouseListener interface. Overrides the paintComponent() method inherited from the
	 * JPanel class to draw the card game table. Implements the mouseClicked() method
	 * from the MouseListener interface to handle mouse click events.
	 * 
	 * @author Aryan Agrawal 
	 *
	 */
	class BigTwoPanel extends JPanel implements MouseListener {
		
		/**
		 * BigTwoPanel constructor that adds the mouselistener to the object 
		 */
		public BigTwoPanel() {
			this.addMouseListener(this); 
		}
		
		/**
		 * Overriding the paint component 
		 * 
		 * @param g parameter of the graphics class
		 */
		public void paintComponent(Graphics g) { 
			Graphics2D g2D = (Graphics2D) g;
			super.paintComponent(g);
			
			Font myfont = new Font("Times New Roman", Font.BOLD, 15);
			g.setFont(myfont); 
			
			//drawing the four players and their avatars 
			for ( int j = 0; j < 4; j ++) { 
				
				if(activePlayer == j){
				g.setColor(Color.black);
				g.drawString("Player " + j +" : YOUR TURN", 10, 15 + (130*j));}
			
				else{
				g.setColor(Color.WHITE);
				g.drawString("Player " + j, 10, 15 + (130*j));}
		
				g.drawImage(playerPics[j], 30, 25 + (130*j), this);
		
				if(activePlayer == j){ // if player is the active player 
					
					for( int i = 0; i < game.getPlayerList().get(j).getNumOfCards(); i++) {
						
						int suit = game.getPlayerList().get(j).getCardsInHand().getCard(i).getSuit(); 
						int rank = game.getPlayerList().get(j).getCardsInHand().getCard(i).getRank(); 
						
						if(selected[i] == false) {
							g.drawImage(openCards[suit][rank], 150 + (45 * i), (130 * j ) + 20, this);}	
						
						else{
							g.drawImage(openCards[suit][rank], 150 + (45 * i), (130 * j) + 15, this);}	
					}
				}
			
				else { // if the player is not the active player 
					for(int i = 0; i < game.getPlayerList().get(j).getNumOfCards(); i++){
						g.drawImage(closedCard, 150 + (45 * i), 20 + (130 * j), this);}
					}
			
				g2D.setColor(Color.white);
				g2D.drawLine(0, 130*(j+1), 1500, 130*(j+1)); }
			
				//drawing the last column that is the previous hand on the table column
				g2D.setColor(Color.black);  
				g2D.drawString("Current Hand on the Table", 10, 535);
				if (game.getHandsOnTable().isEmpty() == false) 
				{
					int size = game.getHandsOnTable().size() - 1; 
					Hand hand = game.getHandsOnTable().get(size);
		    	
		    	
					for( int i = 0; i < hand.size(); i++){
	    			
						int suit2 = hand.getCard(i).getSuit(); 
						int rank2 = hand.getCard(i).getRank(); 
		    		
						g2D.drawImage(openCards[suit2][rank2], 150 + (45 * i), 565, this);}			
				}
			
		    repaint();
			
			
		}
		
		/**
		 * This is an event that indicates that the mouse was used to select 
		 * a card from the panel 
		 */
		public void mouseClicked(MouseEvent e) {
				
			
			int number = game.getPlayerList().get(activePlayer).getNumOfCards() - 1;
			boolean test = false;
			
			if(e.getX() >= (150 + (number * 45)) && e.getX() <= (225 + (number * 45))) {
				if(selected[number] == false && e.getY() >= (35 + (130 * activePlayer)) && e.getY() <= (125 + (130 * activePlayer))) {

					selected[number] = true;
					test = true;
				} 
				
				else if (selected[number] == true && e.getY() >= (15 + (130 * activePlayer)) && e.getY() <= (105 + (130 * activePlayer))){
					
					selected[number] = false;
					test = true;}
			}
			
			number --; 
			for ( ;number >= 0; number--) {
				
				if(test == false){
					if (e.getX() >= (150 + (number * 45)) 
							&& e.getX() <= (195 + (number*45))){
						if(selected[number] == false && e.getY() >= (20 + (130 * activePlayer)) && e.getY() <= (110 + (130 * activePlayer))){
							
							selected[number] = true;
							test = true;
						} 
						
						else if(selected[number] == true && e.getY() >= (15 + (130 * activePlayer)) && 
								e.getY() <= (105 + (130 * activePlayer))){
							selected[number] = false;
							test = true;
						}
					}
					
					else if(e.getX() >= (195 + (number * 45)) && e.getX() <= (225 + (number * 45))
							&& e.getY() >= (20 + (130 * activePlayer)) && e.getY() <= (110 + (130 * activePlayer))){
						
							if(selected[number + 1] == true && selected[number] == false){
								selected[number] = true;
								test = true;}
					}
					
					else if(e.getX() >= (195 + (number * 45)) && e.getX() <= (225 + (number * 45)) && 
							e.getY() >= (15 + (130 * activePlayer)) && e.getY() <= (105 + (130 * activePlayer))){
							if(selected[number+1] == false && selected[number] == true) {
						
							selected[number] = false;
							test= true;}
						}
					}
				}
			
			this.repaint();
			
		}

		public void mousePressed(MouseEvent e) {
			
			
		}

		public void mouseReleased(MouseEvent e) {
			
			
		}

	
		public void mouseEntered(MouseEvent e) {
			
			
		}

	
		public void mouseExited(MouseEvent e) {
			
			
		}
	
		}
	
	}

