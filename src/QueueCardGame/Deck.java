package QueueCardGame;

public class Deck extends ArrayQueue<Card>{

	public Deck(){
	}
	
	public Deck(int cap) {
		super(cap);
	}
	
	/**
	 * Fills the deck with all 52 possible cards.
	 */
	public void fillAll(){
		for (Suits s : Suits.values()){
			for (Ranks r : Ranks.values()){
				enqueue(new Card(s,r));
			}
		}
	}
	
	/**
	 * Puts all cards in the deck in a new random order.
	 */
	public void randomize(){
		Deck temp1 = new Deck(52);
		Deck temp2 = new Deck(52);
		int rand = (int)(Math.random()*20+20);
		for (int i = 0; i <= rand; i++) {
			for (int j = 0; j < size(); j++) {
				if(Math.random()<0.5){
					temp1.enqueue(dequeue());
				} else {
					temp2.enqueue(dequeue());
				}
			}
			while (!temp1.isEmpty()) {
				enqueue(temp1.dequeue());
			}
			while (!temp2.isEmpty()) {
				enqueue(temp2.dequeue());
			}
		}
	}
	
	/**
	 * Generates a String representing all cards in the deck.
	 * @return String
	 */
	public String toString(){
		Deck temp = new Deck();
		StringBuilder b = new StringBuilder();
		while(!isEmpty()){
			b.append(front().toString()+"\n");
			temp.enqueue(dequeue());
		}
		while(!temp.isEmpty()){
			enqueue(temp.dequeue());
		}
		return b.toString();
	}
}
