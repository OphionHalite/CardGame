package QueueCardGame;

public class Main {

	/*
	 Rules:
	 Two players randomly select half a deck of cards. 
	 Then each player turns the top card of his deck. 
	 The card with the highest value determines who wins both cards. 
	 These cards are added to the bottom of the deck. 
	 If both cards have the same value, 
	 the currently revealed cards are set aside. 
	 The winner of the next round adds all cards set aside to the bottom of his deck.
	 
	 To do:
	 1) Implement the classes to play this game.
	 2) Simulate a game. Does this game ever end, i.e. a player loses all his cards? What is the minimal number of cards a player got?
	 3) Extra: Is there a strategy when stacking the cards won, which leads to an advantage?
	 */
	
	/**
	 * 
	 * @param game
	 * @return
	 */
	public String printTop(Game game) {
		StringBuilder b = new StringBuilder();
		b.append( "Turn "+game.getIt()+": \n");
		b.append( "Player1 has a "+game.getTop1()+" \n");
		b.append( "Player2 has a "+game.getTop2()+" \n");
		return b.toString();
	}
	
	/**
	 * 
	 * @param winner of the round
	 * @return a String to print 
	 */
	public String printEndofRound(String winner) {
		if (winner == "Sidepile") {
			return "Draw, the cards are put on the side.\n";
		} else { 
			return winner+" wins the round.\n";
		}
	}
	
	/**
	 * 
	 * @param whoWon
	 * @return 
	 */
	public String printEnd(String whoWon) {
		String out = whoWon+ "";
		return out;
	}
	
	public static void main(String[] args) {
		System.out.println("Started CardGame.jar");
		Main main = new Main();
		Game game = new Game();
		game.handOutCards();
		while (true){
			System.out.println(main.printTop(game));
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		//main.printEnd(game);
	}
}
