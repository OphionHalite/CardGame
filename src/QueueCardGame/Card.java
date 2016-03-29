package QueueCardGame;

public class Card {

	private Suits suit;
	private Ranks rank;
	
	/**
	 * Constructor for a card	
	 * @param suit can be: Spades, Hearts, Diamonds or Clubs
	 * @param rank can be one of the following: Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen or King
	 */
	public Card(Suits suit, Ranks rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * Returns the suit of this card
	 * @return enum Suit
	 */
	public Suits getSuit(){return suit;}
	
	/**
	 * Returns the rank of this card
	 * @return enum Rank
	 */
	public Ranks getRank(){return rank;}
	
	/**
	 * Formats the card to a String
	 * @return String <rank> of <suit>
	 */
	public String toString(){
		return (rank + " of " + suit);
	}
}
