import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] _data;
	public int _size = 0;
	public int _capacity = 1;
	
	// construct an empty randomized queue
	public RandomizedQueue()
	{
		_data = (Item[]) new Object[_capacity];
	}
	
	// is the queue empty?
	public boolean isEmpty()
	{
		return (_size == 0);
	}
	
	// return the number of items on the queue
	public int size()
	{
		return _size;
	}
	
	//function that will resize the array
	private void resize(int newSize)
	{
		Item[] newArray = (Item[]) new Object[newSize];
		int loopLength = (newSize > _size) ? _size : newSize;
		for(int i = 0; i < loopLength; i++)
		{
			newArray[i] = _data[i];
		}
		_data = newArray;
		_capacity = newSize;
	}
	
	// add the item
	public void enqueue(Item item)
	{
		if(item == null)
		{
			throw new java.lang.NullPointerException("Nulls are not allowed!");
		}
		//need to double the capacity
		if(_size == _capacity)
		{
			resize(_capacity*2);
		}
		_data[_size++] = item;
	}
	
	// remove and return a random item
	public Item dequeue()
	{
		if(isEmpty())
		{
			throw new java.util.NoSuchElementException("Empty collection!");
		}
		int indexToRemove = StdRandom.uniform(_size);
		Item data = _data[indexToRemove];
		_data[indexToRemove] = _data[_size-1];
		_data[--_size] = null;
		
		//need to half the size of the array
		if(_size <= _capacity/4 && _capacity > 1)
		{
			resize(_capacity/2);
		}
		return data;
	}
	
	// return (but do not remove) a random item
	public Item sample()
	{
		if(isEmpty())
		{
			throw new java.util.NoSuchElementException("Empty collection!");
		}
		int indexToRemove = StdRandom.uniform(_size);
		Item data = _data[indexToRemove];
		return data;
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator()
	{
		return new RandomizedQueueIterator();
	}
	
	// inner class to deal with iterators
	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private Item[] _randomized;
		private int _current = 0;
		
		public RandomizedQueueIterator()
		{
			_randomized = (Item[]) new Object[_size];
			for(int i = 0; i < _randomized.length; i++)
			{
				_randomized[i] = _data[i];
			}
			StdRandom.shuffle(_randomized);
		}
		public boolean hasNext()
		{
			return (_current < _size);
		}
		public void remove()
		{
			throw new java.lang.UnsupportedOperationException("This is not supported!");
		}
		public Item next()
		{
			if(!hasNext())
			{
				throw new java.util.NoSuchElementException("No more items to return!");
			}
			Item item = _randomized[_current++];
			return item;
		}
	}
		
	// unit testing
	public static void main(String[] args)
	{
		RandomizedQueue<Integer> r1 = new RandomizedQueue<Integer>();
		boolean test1Status = (r1.isEmpty() == true);
		
		//basic addFirst
		r1.enqueue(1);
		boolean test2Status = (r1.isEmpty() == false);
		boolean test3Status = (r1.size() == 1);
		boolean test4Status = (r1.sample() == 1);
		boolean test5Status = (r1.dequeue() == 1);
		boolean test6Status = (r1.isEmpty() == true);		
		
		//more advanced
		RandomizedQueue<Integer> r2 = new RandomizedQueue<Integer>();
		r2.enqueue(1);
		System.out.println(r2._capacity);
		System.out.println(r2._size);		
		r2.enqueue(2);
		System.out.println(r2._capacity);
		System.out.println(r2._size);				
		r2.enqueue(3);
		System.out.println(r2._capacity);
		System.out.println(r2._size);		
		r2.enqueue(4);
		System.out.println(r2._capacity);
		System.out.println(r2._size);				
		r2.enqueue(5);
		System.out.println(r2._capacity);
		System.out.println(r2._size);		
		r2.enqueue(6);
		System.out.println(r2._capacity);
		System.out.println(r2._size);		
		r2.enqueue(7);
		System.out.println(r2._capacity);
		System.out.println(r2._size);
		r2.enqueue(8);
		System.out.println(r2._capacity);
		System.out.println(r2._size);
		
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);			
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);			
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);			
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);			
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);			
		System.out.println(r2.dequeue());
		System.out.println(r2._capacity);
		System.out.println(r2._size);					
		
		//test the iterator
		/*
		Deque<Integer> d5 = new Deque<Integer>();		
		d5.addFirst(1);
		d5.addLast(2);
		d5.addFirst(3);
		d5.addLast(4);
		d5.addFirst(5);
		d5.addLast(6);
		Iterator<Integer> iter = d5.iterator();
		while(iter.hasNext())
		{
			Integer i = iter.next();
			System.out.println("We are printing out items in the iterator!");
			System.out.println(i);
		}
		*/
		
		System.out.println(test1Status);
		System.out.println(test2Status);
		System.out.println(test3Status);
		System.out.println(test4Status);
		System.out.println(test5Status);
		System.out.println(test6Status);			
	}
}