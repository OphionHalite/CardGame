package QueueCardGame;

public class Game {
	
	private Deck player1;
	private Deck player2;
	private Deck sidePile;
	private long iterations;
	
	public Game(){
		iterations = 0;
		player1 = new Deck(52);
		player2 = new Deck(52);
		sidePile = new Deck(52);
	}
	
	/**
	 * Return the number of iterations the game has gone through.
	 * @return int iterations
	 */
	public long getIt() {
		return iterations;
	}
	
	/**
	 * Returns a String representing the top card of the first player.
	 * @return String top card of deck
	 */
	public String getTop1() {
		return player1.front().toString();
	}
	
	/**
	 * Returns a String representing the top card of the second player.
	 * @return String top card of deck
	 */
	public String getTop2() {
		return player2.front().toString();
	}
	
	/**
	 * Randomizes a deck of cards and splits it between the two players.
	 */
	public void handOutCards() {
		Deck mainDeck = new Deck(52);
		mainDeck.fillAll();
		mainDeck.randomize();
		while (!mainDeck.isEmpty()) {
			player1.enqueue(mainDeck.dequeue());
			player2.enqueue(mainDeck.dequeue());
		}
	}
	
	/**
	 * Gives the top cards of each player to the receiver.
	 * If there are cards on the sidepile give them too.
	 * @param receiver
	 */
	public void giveCards(Deck receiver) {
		if(Math.random()<0.5) {						
			receiver.enqueue(player1.dequeue());		
			receiver.enqueue(player2.dequeue());	
		} else {									
			receiver.enqueue(player2.dequeue());		
			receiver.enqueue(player1.dequeue());
		}
		while (!sidePile.isEmpty()) {
			receiver.enqueue(sidePile.dequeue());
		}
	}
	
	/**
	 * Returns a string representation of the player that won the round.
	 * @return the player that won the round.
	 */
	public String turnOver(){
		iterations++;
		int p1 = player1.front().getRank().ordinal();	//Gets the values of the cards
		int p2 = player2.front().getRank().ordinal();
		if (p1 > p2){									//Player 1 has the highest card
			giveCards(player1);
			return "Player1";
		} else if (p1 < p2) {							//Player 2 has the highest card
			giveCards(player2);
			return "Player2";
		} else {
			giveCards(sidePile);
			return "SidePile";
		}
	}
	
	/**
	 * 
	 * @return true if one of the players has no cards left.
	 */
	public boolean gameOver(){
		return (player1.isEmpty()||player2.isEmpty());
	}
	
	/**
	 * 
	 * @return a printable string that says who won.
	 */
	public String whoWins(){
		if (player1.isEmpty()&&player2.isEmpty()) {
			return "There's a draw, no one wins!";
		} else if (player2.isEmpty()) {
			return "Player 1 wins!";
		} else if (player1.isEmpty()) {
			return "Player 2 wins!";
		} else {
			return "Something went very wrong";
		}
	}
}
