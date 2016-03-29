
public class ArrayQueue<E> implements QueueInt<E> {

	private E[] array;
	private int size;
	private int capacity;
	private int first;
	
	/**
	 * Implementation of a FIFO queue using a dynamic array.
	 * The initial size of the queue is 2 and doubles each time it reaches it's limit.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		size = 0;
		capacity = 2;
		array = (E[])new Object[2];
		first = 0;
	}
	
	/**
	 * Implementation of a FIFO queue using a dynamic array.
	 * The initial size of the queue is 2 and doubles each time it reaches it's limit.
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
		size = 0;
		this.capacity = capacity;
		array = (E[])new Object[capacity];
		first = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E front() {
		return array[first];
	}

	@Override
	public void enqueue(E element) {
		if (size == capacity) {		//Array is to small to enqueue more elements
			resize(capacity*2);
		}
		if (capacity > first+size) {
			array[first+size] = element;
		} else {
			array[first+size-capacity] = element;
		}
		size++;
	}

	@Override
	public E dequeue() {
		size --;
		E e = front();
		array[first] = null;
		first++;
		if (first >= capacity)
		first = 0;
		return e;
	}
	
	@SuppressWarnings("unchecked")
	public void resize(int newCap) {
		E[] newArray = (E[])new Object[newCap];
		for (int i = 0; i < size; i++) {
			if (capacity >= first+i) {
				newArray[i] = array[first+i];
			} else {
				newArray[i] = array[first+i-capacity];
			}
		}
		capacity = newCap;
		first = 0;
		array = newArray;
	}
}
