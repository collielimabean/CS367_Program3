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
            throw new IllegalArgumentException();
        
        if(maxCapacity <= 0)
            throw new IllegalArgumentException("Cannot construct a queue" 
                    + " with" +  maxCapacity + "elements."); 
        
        queue = (E[]) new Object[maxCapacity + 1];
        this.comparator = comparator;
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
        
        rootHeapify();
        
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
    	if ( item == null)
    		throw new IllegalArgumentException();
    	
		if ( size() >= capacity())
			throw new FullQueueException();
        
        queue[numItems + 1] = item;
        numItems++;
        
        leafHeapify();
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
     * Heapifies the heap from the root to the leaves. (top - bottom)
     */
    private void rootHeapify()
    {
        downHeapify(1); // 1 represents the root index, which starts at 1
    }
    
    /**
     * Down-heapifies the heap from the specified index to the heap's leaves.
     * @param index the start index to down-heapify.
     */
    private void downHeapify(int index)
    {
        //outside range implies we hit a leaf
        if(2 * index > numItems)
            return;
        
        //if not, grab appropriate indices
        int left = 2 * index;
        int right = left + 1;
        int smaller;
        
        //if right index does not exist, choose the left index ('child/ren')
        if(right > numItems)
            smaller = left;
        
        //compare left, right: if left has earlier deadline, smaller = left
        //otherwise right is smaller
        else 
            smaller = comparator.compare(queue[left], queue[right]) < 0 
                                                        ? left : right;
        
        //compare with parent, and continue down-heapify on children nodes
        if(comparator.compare(queue[smaller], queue[index]) < 0)
        {
            swap(index, smaller);
            downHeapify(smaller);
        }
        
    }
    
    /**
     * Heapifies from the last element to the root element.
     * heapifies the index of the last referenced leaf
     */
    private void leafHeapify()
    {
        upHeapify(numItems); 
    }
    
    /**
     * Up-heapifies to the root from the specified index.
     * @param index the specified index to up-heapify.
     */
    private void upHeapify(int index)
    {
        //we hit bedrock 
        if(index <= 1)
            return;
        
        //get parent index
        int parent = index / 2;
        
        //compare with parent
        if(comparator.compare(queue[index], queue[parent]) < 0)
        {
            swap(index, parent);
            
            //continue the up-heapify
            upHeapify(parent);
            
            //ensure swapped element is in the right place
            downHeapify(index);
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
        
        for(int i = 1; i < numItems + 1; i++)
            print += queue[i]  + ", ";
        
        print += "]";
        
        return print;
    }

}