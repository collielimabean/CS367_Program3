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
     * @throws IllegalArgumentException if comparator is null or 
     * maxCapacity is non-positive.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<E> comparator, int maxCapacity)
    {       
        if(comparator == null)
            throw new IllegalArgumentException("Comparator cannot be null.");
        
        if(maxCapacity <= 0)
            throw new IllegalArgumentException("Cannot construct a queue" 
                    + " with" +  maxCapacity + "elements."); 
        
        this.comparator = comparator;
        queue = (E[]) new Object[maxCapacity + 1];
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
        return numItems == queue.length - 1;
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
        
        return queue[1];
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
        
        E data = queue[1];
        
        //Set first element to the last element
        queue[1] = queue[numItems];
        
        //Destroy last item
        queue[numItems] = null;
        
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
        
        queue[numItems + 1] = item;
        numItems++;
        
        heapify();
    }
    
    /**
     * Returns the max capacity of the priority queue.
     * @return max capacity of queue
     */
    public int capacity()
    {
        return queue.length - 1;
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
        for(int i = 1; i < (queue.length / 2) - 1; i *= 2)
        {
            int compare;
            int smaller;
            
            switch(numItems)
            {
                //Zero or one element arrays don't need to be heapified
                case 0:
                case 1:
                    return;
                
                //Two element arrays just need to compare with each other
                case 2:
                    smaller = i + 1;
                    break;
                
                //3 elements is minimum for proper binary tree    
                default:
                    compare = comparator.compare(queue[2 * i]
                                                    , queue[(2 * i) + 1]);
                    
                    smaller = (compare < 0) ? 2 * i : (2 * i) + 1;
                    break;
            }
            
            //compare parent and larger of nodes
            compare = comparator.compare(queue[i], queue[smaller]);
            
            //swap if not in right place
            //compare > 0 when smaller deadline is earlier
            if(compare > 0)
                swap(i, smaller);
        }
    }
    
    /**
     * Swaps the data at two specified locations.
     * @param index1 The first index to swap
     * @param index2 The second index to swap
     * @throws IndexOutofBoundsException if index1 or index2 are out of range
     */
    private void swap(int index1, int index2)
    {
        if(index1 < 0 || index1 > numItems 
                      || index2 < 0 || index2 > numItems)
            throw new IndexOutOfBoundsException();
        
        E holdValueIndex1 = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = holdValueIndex1;
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