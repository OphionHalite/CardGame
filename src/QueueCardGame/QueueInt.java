package QueueCardGame;

public interface QueueInt<E> {

	/**
	 * Returns true if the queue doesn't contain any elements
	 * @return true if empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the amount of elements in the queue
	 * @return size
	 */
	int size();
	
	/**
	 * Returns the first element in the queue
	 * @return first element
	 */
	E front();
	
	/**
	 * Adds the given element at the end of the queue
	 * @param last element
	 */
	void enqueue(E element);
	
	/**
	 * Returns the first element in the queue and removes it
	 * @return first element
	 */
	E dequeue();
	
	/**
	 * resizes the queue to hold a new capacity
	 * @param the new capacity
	 */
	void resize(int newCap);
}
