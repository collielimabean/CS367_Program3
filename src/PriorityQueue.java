///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             PriorityQueue.java
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

import java.util.Comparator;

/**
 * This class implements a Queue by using an array-based heap.
 * @param <E> The type that the PriorityQueue will hold.
 * @see QueueADT.java for interface documentation
 */
public class PriorityQueue<E> implements QueueADT<E> 
{
    private E[] queue;
    private Comparator<E> comparator;
    private int numItems;
    
    /**
     * Constructs a PriorityQueue object with the specified Comparator and 
     * maximum capacity.
     * @param comparator Comparator object to compare two type E objects
     * @param maxCapacity Maximum capacity of the queue
     */
    public PriorityQueue(Comparator<E> comparator, int maxCapacity)
    {       
        this.comparator = comparator;
        
        //unavoidable warning
        queue = (E[]) new Object[maxCapacity];
        
        numItems = 0;
    }
    
    /**
     * Returns true if the queue has no elements (is empty).
     * @return true if queue has no elements, false otherwise
     */
    public boolean isEmpty() 
    {
        return numItems == 0;
    }
    
    /**
     * Returns true if the queue is full.
     * @return true if queue has is at max capacity, false otherwise
     */
    public boolean isFull() 
    {
        return numItems == queue.length;
    }
    
    /**
     * Gets the highest priority element without removing it ("peeking").
     * @throws EmptyQueueException if there are no elements in the queue.
     * @return The object with the highest priority in the queue.
     */
    public E peek() throws EmptyQueueException
    {
        if(isEmpty())
            throw new EmptyQueueException();
        
        return queue[0];
    }
    
    /**
     * Removes and returns the highest priority element.
     * @throws EmptyQueueException if there are no elements in the queue.
     * @return the highest priority object.
     */
    public E dequeue() throws EmptyQueueException
    {
        if(isEmpty())
            throw new EmptyQueueException();
        
        E data = queue[0];
        
        queue[0] = queue[queue.length - 1];
        queue[queue.length - 1] = null;
        numItems--;
        
        heapify();
        
        return data;
    }

    /**
     * Adds the requested item into the queue, then sorts the queue
     * according to priority
     * @throws IllegalArgumentException if item is null
     * @throws FullQueueException when the queue is full
     */
    public void enqueue(E item) throws FullQueueException
    {
        if(item == null)
            throw new IllegalArgumentException();
        
        if(size() >= capacity())
            throw new FullQueueException();
        
        queue[numItems] = item;
        
        numItems++;
    }
    
    /**
     * Returns the max capacity of the priority queue.
     * @return max capacity of queue
     */
    public int capacity()
    {
        return queue.length;
    }
    
    /**
     * Returns the number of items in the priority queue.
     * @return number of items in the queue
     */
    public int size()
    {
        return numItems;
    }
    
    /**
     * Sorts the array into a proper binary tree.
     */
    private void heapify()
    {
        //TODO finish me
    }

}
