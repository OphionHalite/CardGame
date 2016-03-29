import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Game {

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
	
	private Deck player1 = new Deck();
	private Deck player2 = new Deck();
	private Deck sidePile = new Deck();
	private long iterations;
	private String startTime;
	
	public Game(){
		iterations = 0;
		startTime = getDate();
	}
	
	public String getStart() {
		return startTime;
	}
	
	public long getIt() {
		return iterations;
	}
	
	public String getDate(){
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	}
	
	public String decksToString(){
		return "Player 1 ("+player1.size()+"):\n"+player1.toString()+"\n"+
				"Player 2 ("+player2.size()+"):\n"+player2.toString()+"\n"+
				"Sidepile ("+sidePile.size()+"):\n"+sidePile.toString();
	}
	
	public void handOutCards() {
		Deck mainDeck = new Deck();
		mainDeck.fillAll();
		mainDeck.randomize();
		while (!mainDeck.isEmpty()) {
			player1.enqueue(mainDeck.dequeue());
			player2.enqueue(mainDeck.dequeue());
		}
	}
	
	public String turnOver(){
		iterations++;
//		StringBuilder b = new StringBuilder();
//		b.append("Turn "+iterations+": \n");
//		b.append("Player 1 ("+player1.size()+") reveals: "+player1.front().toString()+"\n");	//Reveals the top card of player 1		
//		b.append("Player 2 ("+player2.size()+") reveals: "+player2.front().toString()+"\n");	//Reveals the top card of player 2
		int p1 = player1.front().getRank().ordinal();	//Gets the values of the cards
		int p2 = player2.front().getRank().ordinal();
		if (p1 < p2){									//Player 2 has the highest card
			if(Math.random()<0.5) {						
				player2.enqueue(player1.dequeue());		
				player2.enqueue(player2.dequeue());		//See Rules of Conduit
			} else {									
				player2.enqueue(player2.dequeue());		
				player2.enqueue(player1.dequeue());		
			}
//			b.append("Player2 wins the round");	
			while (!sidePile.isEmpty()) {				//If there are cards in the sidepile
				player2.enqueue(sidePile.dequeue());	//Put them on the bottom of player 2 as well
//				b.append("and all card set aside");
			}
		} else if (p1 > p2) {							//Player 1 has the highest card
			if (Math.random()<0.5) {
				player1.enqueue(player1.dequeue());
				player1.enqueue(player2.dequeue());
			} else {
				player1.enqueue(player2.dequeue());
				player1.enqueue(player1.dequeue());
			}
//			b.append("Player1 wins the round");
			while (!sidePile.isEmpty()) {
				player1.enqueue(sidePile.dequeue());
//				b.append(" and all card set aside");
			}
		} else {
			if (Math.random()<0.5) {
				sidePile.enqueue(player1.dequeue());
				sidePile.enqueue(player2.dequeue());
			} else {
				sidePile.enqueue(player2.dequeue());
				sidePile.enqueue(player1.dequeue());
			}
//			b.append("No one wins the round, the cards are set aside");
		}
		//b.append(".\n");
		//return b.toString();
		return "";
	}
	
	public boolean gameOver(){
		return (player1.isEmpty()||player2.isEmpty());
	}
	
	public String whoWins(){
		if (player1.isEmpty()&&player2.isEmpty()) {
			return "There's a draw, no one wins!";
		} else if (player1.isEmpty()) {
			return "Player 2 wins!";
		} else if (player2.isEmpty()) {
			return "Player 1 wins!";
		} else {
			return "No one won";
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Started CardGame.jar");
		int n = 10000;
		int[] turns = new int[n];
		int max = 0;
		int min = Integer.MAX_VALUE;
		double average = 0;
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 2000000; i++) {
			Game game = new Game();
			game.handOutCards();
			while (!game.gameOver()&&game.getIt()<n-1){
				game.turnOver();
				
				//System.out.println(b.append(game.turnOver()+"\n");
				/*
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				*/
			}
			turns[(int) game.getIt()/2]++;
		}		
		for (int i = 0; i < n; i++) {
			//average = average*i/(i+1) + (double)turns[(int)i]*1/(i+1);
			//b.append(turns[i-1]+" ");
			//if (i%10==0)b.append("\n");
			//max = Math.max(max, turns[(int)i]);
			//min = Math.min(min, turns[(int)i]);
			b.append(turns[i]+"\n");
			
		}
		/*
		b.append("\nMean : "+turns[n/2]);
		b.append("\nMin: "+min);
		b.append("\nAverage: "+average);
		b.append("\nMax: "+max);
		}
		*/
		//Print to file
		
		Path path = Paths.get(System.getProperty("user.dir")+"\\LogDecks.dat");
		if (!Files.exists(path)){
			try {
				Files.write(path, new byte[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
	    b.append("Game ended: "+game.whoWins()+"\n");
	    b.append("Started @ "+game.getStart()+"\n");
	    b.append("Ended @ "+game.getDate()+"\n");
	    b.append("This game took "+game.getIt()+" iterations."+"\n\n");
	    b.append("The remaining decks contained the following cards:\n");
	    b.append(game.decksToString());
	    */
		
		
	    
	    String stringOut = b.toString();
	    byte[] bytes = stringOut.getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(game.getIt());
	}
}
