///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             CircularQueue.java
// Semester:         CS367 Spring 2014
//
// Author:           William Jen <wjen@wisc.edu>
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     Allen Hung
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;
import java.util.ArrayList;

/**
 * This class implements the QueueADT interface, and uses Java's implementation
 * of ArrayList to make a CircularQueue. This CircularQueue has a fixed
 * capacity, or is not expandable.
 * @see QueueADT.java
 * @param <E> The type that can be placed within the queue.
 */
public class CircularQueue<E> implements QueueADT<E>
{
    private List<E> queue;
    private int numItems;
    private int maxCapacity;
    
    private int front;
    private int rear;
    
    /**
     * Constructs a CircularQueue with the specified capacity.
     * @param capacity Size of the queue
     * @throws IllegalArgumentException if capacity is non-positive.
     */
    public CircularQueue(int capacity)
    {
        if(capacity <= 0)
            throw new IllegalArgumentException("Cannot construct a queue" 
                                                + " with" + capacity 
                                                + " elements.");
        
        queue = new ArrayList<E>(capacity);
        numItems = 0;
        maxCapacity = capacity;
        
        front = 0;
        rear = 0;
    }
    
    /**
     * Returns whether or not the queue contains items.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return numItems == 0;
    }
    
    /**
     * Returns whether or not the queue is full.
     * @return true if at max capacity, false otherwise
     */
    public boolean isFull()
    {
        return numItems == maxCapacity;
    }
    
    /**
     * Gets the first item in the queue without removing it.
     * @return the item at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public E peek() throws EmptyQueueException
    {
        if(isEmpty())
            throw new EmptyQueueException();

        return queue.get(front);
    }
    
    /**
     * Removes and returns the first item in the queue.
     * @return the item at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    public E dequeue() throws EmptyQueueException 
    {
        if(isEmpty())
            throw new EmptyQueueException();
        
        E data = queue.get(front);
        
        //destroy front element
        queue.set(front, null);
        
        numItems--;
        
        //shift front accordingly
        if(isEmpty())
        {
            front = 0;
            rear = 0;
        }
        
        else front++;
        
        return data;
    }
    
    /**
     * Adds an item to the queue.
     * @param item item to add to the queue
     * @throws FullQueueException if the queue is full
     */
    public void enqueue(E item) throws FullQueueException
    {
        if(isFull())
            throw new FullQueueException();
        
        if(item == null)
            throw new IllegalArgumentException();
        
        //Wrap-around case
        if(rear == maxCapacity)
        {
            rear = 0;
            queue.set(rear, item);
        }
        
        //cases after wrap-around
        else if(rear < queue.size())
        {
            queue.set(rear, item);
            rear++;
        }
        
        //initial case: no wrap-arounds have occurred
        else
        {
            queue.add(item);
            rear++;
        }
        
        numItems++;
    }
    
    /**
     * Gets the maximum number of items that can be placed in the queue.
     * @return the max number of items that can be placed in the queue.
     */
    public int capacity() 
    {
        return maxCapacity;
    }
    
    /**
     * Gets the number of non-null items in the queue.
     * @return the number of items in the queue
     */
    public int size()
    {
        return numItems;
    }
    
    /**
     * Returns a readable String for the queue.
     * @return A readable String for the queue.
     */
    public String toString()
    {
        String print = "[";
        
        for(E element : queue)
            print += element + ", ";
        
        print += "]";
        
        return print;
    }

}
